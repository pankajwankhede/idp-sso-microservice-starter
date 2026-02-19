package com.company.idp.sso.domain.service;

import com.company.idp.sso.api.model.request.ForgotPasswordRequest;
import com.company.idp.sso.api.model.request.UpdatePasswordRequest;
import com.company.idp.sso.api.model.request.PasswordExpiredUpdateRequest;
import com.company.idp.sso.api.model.response.SimpleActionResponse;
import com.company.idp.sso.config.SsoProperties;
import com.company.idp.sso.integration.RealmDownstreamClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
  private static final Logger log = LoggerFactory.getLogger(PasswordService.class);

  private final RealmConfigService realmConfigService;
  private final RealmDownstreamClient downstream;

  public PasswordService(RealmConfigService realmConfigService, RealmDownstreamClient downstream) {
    this.realmConfigService = realmConfigService;
    this.downstream = downstream;
  }

  public SimpleActionResponse forgotPassword(ForgotPasswordRequest req) {
    String realm = req.getRealm().toUpperCase();
    SsoProperties.Realm cfg = realmConfigService.requireRealm(realm);

    log.info("forgotPassword realm={} user={}", realm, mask(req.getUsernameOrEmail()));
    return downstream.postJson(cfg.getPasswordBaseUrl(), "/forgot-password", req, SimpleActionResponse.class);
  }

  public SimpleActionResponse updatePassword(UpdatePasswordRequest req) {
    String realm = req.getRealm().toUpperCase();
    SsoProperties.Realm cfg = realmConfigService.requireRealm(realm);

    log.info("updatePassword realm={} user={}", realm, mask(req.getUsername()));
    return downstream.postJson(cfg.getPasswordBaseUrl(), "/update-password", req, SimpleActionResponse.class);
  }

  public SimpleActionResponse passwordExpiredUpdate(PasswordExpiredUpdateRequest req) {
    String realm = req.getRealm().toUpperCase();
    SsoProperties.Realm cfg = realmConfigService.requireRealm(realm);

    log.info("passwordExpireUpdate realm={} user={}", realm, mask(req.getUsername()));
    return downstream.postJson(cfg.getPasswordBaseUrl(), "/password-expired-update", req, SimpleActionResponse.class);
  }

  private String mask(String v) {
    if (v == null || v.isBlank()) return "-";
    if (v.length() <= 3) return "***";
    return v.substring(0, 1) + "***" + v.substring(v.length() - 1);
  }
}
