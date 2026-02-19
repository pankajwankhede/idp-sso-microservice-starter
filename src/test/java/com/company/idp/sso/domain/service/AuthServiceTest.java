package com.company.idp.sso.domain.service;

import com.company.idp.sso.api.model.request.AuthenticateRequest;
import com.company.idp.sso.api.model.response.AuthenticateResponse;
import com.company.idp.sso.config.SsoProperties;
import com.company.idp.sso.integration.RealmDownstreamClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;

import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

  private MockWebServer server;

  @BeforeEach
  void setup() throws IOException {
    server = new MockWebServer();
    server.start();
  }

  @AfterEach
  void tearDown() throws IOException {
    server.shutdown();
  }

  @Test
  void authenticate_routesByRealm_andCallsLogin() throws Exception {
    server.enqueue(new MockResponse()
        .setResponseCode(200)
        .addHeader("Content-Type", "application/json")
        .setBody("{\"authenticated\":true,\"userId\":\"u1\",\"displayName\":\"User One\",\"roles\":[\"USER\"],\"passwordExpired\":false,\"nextAction\":\"NONE\"}"));

    String baseUrl = server.url("/").toString();
    baseUrl = baseUrl.substring(0, baseUrl.length()-1);

    SsoProperties props = new SsoProperties();
    SsoProperties.Realm realmCfg = new SsoProperties.Realm();
    realmCfg.setAuthBaseUrl(baseUrl);
    props.setRealms(Map.of("PCC", realmCfg));

    RealmConfigService cfgService = new RealmConfigService(props);
    RealmDownstreamClient downstream = new RealmDownstreamClient(WebClient.builder().build());
    AuthService authService = new AuthService(cfgService, downstream);

    AuthenticateRequest req = new AuthenticateRequest();
    req.setRealm("PCC");
    req.setClientId("pcc-client");
    req.setUsername("john");
    req.setPassword("secret");

    AuthenticateResponse resp = authService.authenticate(req);
    assertTrue(resp.isAuthenticated());
    assertEquals("u1", resp.getUserId());

    var recorded = server.takeRequest();
    assertEquals("/login", recorded.getPath());
    assertEquals("POST", recorded.getMethod());
    String body = recorded.getBody().readUtf8();
    assertTrue(body.contains("\"clientId\":\"pcc-client\""));
    assertTrue(body.contains("\"realm\":\"PCC\""));
  }
}
