package com.company.idp.sso.integration;

import com.company.idp.sso.exception.ApiException;
import com.company.idp.sso.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class RealmDownstreamClient {

  private static final Logger log = LoggerFactory.getLogger(RealmDownstreamClient.class);
  private final WebClient webClient;

  public RealmDownstreamClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public <TReq, TResp> TResp postJson(String baseUrl, String path, TReq body, Class<TResp> respType) {
    try {
      return webClient.post()
          .uri(baseUrl + path)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
          .bodyValue(body)
          .retrieve()
          .onStatus(status -> status.isError(), r ->
              r.bodyToMono(String.class)
                  .defaultIfEmpty("Downstream error")
                  .flatMap(msg -> Mono.error(new ApiException(
                      ErrorCode.DOWNSTREAM_ERROR,
                      "Downstream call failed (" + r.statusCode().value() + "): " + msg
                  )))
          )
          .bodyToMono(respType)
          .timeout(Duration.ofSeconds(10))
          .block();
    } catch (ApiException e) {
      throw e;
    } catch (Exception e) {
      log.warn("Downstream call failed baseUrl={} path={} err={}", baseUrl, path, e.toString());
      throw new ApiException(ErrorCode.DOWNSTREAM_ERROR, "Downstream call failed: " + path, e);
    }
  }
}
