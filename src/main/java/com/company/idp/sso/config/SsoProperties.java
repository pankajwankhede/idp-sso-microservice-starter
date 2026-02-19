package com.company.idp.sso.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "sso")
public class SsoProperties {

  private Map<String, Realm> realms = new HashMap<>();

  public Map<String, Realm> getRealms() { return realms; }
  public void setRealms(Map<String, Realm> realms) { this.realms = realms; }

  public Realm realm(String realm) {
    if (realm == null) return null;
    return realms.get(realm.toUpperCase());
  }

  public static class Realm {
    private String authBaseUrl;
    private String passwordBaseUrl;
    private String profileBaseUrl;

    public String getAuthBaseUrl() { return authBaseUrl; }
    public void setAuthBaseUrl(String authBaseUrl) { this.authBaseUrl = authBaseUrl; }

    public String getPasswordBaseUrl() { return passwordBaseUrl; }
    public void setPasswordBaseUrl(String passwordBaseUrl) { this.passwordBaseUrl = passwordBaseUrl; }

    public String getProfileBaseUrl() { return profileBaseUrl; }
    public void setProfileBaseUrl(String profileBaseUrl) { this.profileBaseUrl = profileBaseUrl; }
  }
}
