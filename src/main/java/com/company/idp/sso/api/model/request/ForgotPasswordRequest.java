package com.company.idp.sso.api.model.request;

import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordRequest {
  @NotBlank(message = "realm is required") private String realm;
  @NotBlank(message = "usernameOrEmail is required") private String usernameOrEmail;

  public String getRealm() { return realm; }
  public void setRealm(String realm) { this.realm = realm; }

  public String getUsernameOrEmail() { return usernameOrEmail; }
  public void setUsernameOrEmail(String usernameOrEmail) { this.usernameOrEmail = usernameOrEmail; }
}
