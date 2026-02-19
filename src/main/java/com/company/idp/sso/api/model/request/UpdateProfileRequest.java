package com.company.idp.sso.api.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public class UpdateProfileRequest {
  @NotBlank(message = "realm is required") private String realm;
  @NotBlank(message = "username is required") private String username;
  @NotNull(message = "profile is required") private Map<String, Object> profile;

  public String getRealm() { return realm; }
  public void setRealm(String realm) { this.realm = realm; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public Map<String, Object> getProfile() { return profile; }
  public void setProfile(Map<String, Object> profile) { this.profile = profile; }
}
