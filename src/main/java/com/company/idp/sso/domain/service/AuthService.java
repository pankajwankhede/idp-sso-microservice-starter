package com.company.idp.sso.domain.service;

import com.company.idp.sso.api.model.request.AuthenticateRequest;
import com.company.idp.sso.api.model.response.AuthenticateResponse;
import com.company.idp.sso.config.SsoProperties;
import com.company.idp.sso.integration.RealmDownstreamClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private static final Logger log = LoggerFactory.getLogger(AuthService.class);

  private final RealmConfigService realmConfigService;
  private final RealmDownstreamClient downstream;

  public AuthService(RealmConfigService realmConfigService, RealmDownstreamClient downstream) {
    this.realmConfigService = realmConfigService;
    this.downstream = downstream;
  }

  public AuthenticateResponse authenticate(AuthenticateRequest req) {
    String realm = req.getRealm().toUpperCase();
    SsoProperties.Realm cfg = realmConfigService.requireRealm(realm);

    log.info("authenticate realm={} clientId={} username={}", realm, req.getClientId(), mask(req.getUsername()));

    DownstreamAuthRequest body = new DownstreamAuthRequest(req.getUsername(), req.getPassword(), req.getClientId(), realm);
    return downstream.postJson(cfg.getAuthBaseUrl(), "/login", body, AuthenticateResponse.class);
  }

  private String mask(String u) {
    if (u == null || u.isBlank()) return "-";
    if (u.length() <= 2) return "**";
    return u.charAt(0) + "***" + u.charAt(u.length() - 1);
  }

  public record DownstreamAuthRequest(String username, String password, String clientId, String realm) {}
}
