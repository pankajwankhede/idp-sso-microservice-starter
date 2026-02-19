package com.company.idp.sso.domain.service;

import com.company.idp.sso.api.model.request.ForgotUsernameRequest;
import com.company.idp.sso.api.model.request.UpdateProfileRequest;
import com.company.idp.sso.api.model.response.SimpleActionResponse;
import com.company.idp.sso.config.SsoProperties;
import com.company.idp.sso.integration.RealmDownstreamClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
  private static final Logger log = LoggerFactory.getLogger(ProfileService.class);

  private final RealmConfigService realmConfigService;
  private final RealmDownstreamClient downstream;

  public ProfileService(RealmConfigService realmConfigService, RealmDownstreamClient downstream) {
    this.realmConfigService = realmConfigService;
    this.downstream = downstream;
  }

  public SimpleActionResponse forgotUsername(ForgotUsernameRequest req) {
    String realm = req.getRealm().toUpperCase();
    SsoProperties.Realm cfg = realmConfigService.requireRealm(realm);

    log.info("forgotUsername realm={} contact={}", realm, mask(req.getEmailOrPhone()));
    return downstream.postJson(cfg.getProfileBaseUrl(), "/forgot-username", req, SimpleActionResponse.class);
  }

  public SimpleActionResponse updateProfile(UpdateProfileRequest req) {
    String realm = req.getRealm().toUpperCase();
    SsoProperties.Realm cfg = realmConfigService.requireRealm(realm);

    log.info("updateProfile realm={} user={}", realm, mask(req.getUsername()));
    return downstream.postJson(cfg.getProfileBaseUrl(), "/update-profile", req, SimpleActionResponse.class);
  }

  private String mask(String v) {
    if (v == null || v.isBlank()) return "-";
    if (v.length() <= 3) return "***";
    return v.substring(0, 1) + "***" + v.substring(v.length() - 1);
  }
}
