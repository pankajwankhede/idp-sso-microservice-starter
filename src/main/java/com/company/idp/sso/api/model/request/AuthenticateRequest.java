package com.company.idp.sso.api.model.request;

import jakarta.validation.constraints.NotBlank;

public class AuthenticateRequest {
  @NotBlank(message = "realm is required") private String realm;
  @NotBlank(message = "clientId is required") private String clientId;
  @NotBlank(message = "username is required") private String username;
  @NotBlank(message = "password is required") private String password;

  public String getRealm() { return realm; }
  public void setRealm(String realm) { this.realm = realm; }

  public String getClientId() { return clientId; }
  public void setClientId(String clientId) { this.clientId = clientId; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
}
