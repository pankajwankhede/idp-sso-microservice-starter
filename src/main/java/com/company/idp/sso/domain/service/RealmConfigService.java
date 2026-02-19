package com.company.idp.sso.domain.service;

import com.company.idp.sso.config.SsoProperties;
import com.company.idp.sso.exception.ApiException;
import com.company.idp.sso.exception.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class RealmConfigService {
  private final SsoProperties props;
  public RealmConfigService(SsoProperties props) { this.props = props; }

  public SsoProperties.Realm requireRealm(String realm) {
    SsoProperties.Realm r = props.realm(realm);
    if (r == null) throw new ApiException(ErrorCode.INVALID_REALM, "Unsupported realm: " + realm);
    return r;
  }
}
