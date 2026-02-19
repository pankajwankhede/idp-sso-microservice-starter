package com.company.idp.sso.api.model.request;

import jakarta.validation.constraints.NotBlank;

public class PasswordExpiredUpdateRequest {
  @NotBlank(message = "realm is required") private String realm;
  @NotBlank(message = "username is required") private String username;
  @NotBlank(message = "oldPassword is required") private String oldPassword;
  @NotBlank(message = "newPassword is required") private String newPassword;

  public String getRealm() { return realm; }
  public void setRealm(String realm) { this.realm = realm; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public String getOldPassword() { return oldPassword; }
  public void setOldPassword(String oldPassword) { this.oldPassword = oldPassword; }

  public String getNewPassword() { return newPassword; }
  public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}
