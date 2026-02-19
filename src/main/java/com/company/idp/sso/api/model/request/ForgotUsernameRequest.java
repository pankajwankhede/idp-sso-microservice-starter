package com.company.idp.sso.api.model.request;

import jakarta.validation.constraints.NotBlank;

public class ForgotUsernameRequest {
  @NotBlank(message = "realm is required") private String realm;
  @NotBlank(message = "emailOrPhone is required") private String emailOrPhone;

  public String getRealm() { return realm; }
  public void setRealm(String realm) { this.realm = realm; }

  public String getEmailOrPhone() { return emailOrPhone; }
  public void setEmailOrPhone(String emailOrPhone) { this.emailOrPhone = emailOrPhone; }
}
