package com.company.idp.sso.api.model.request;

import jakarta.validation.constraints.NotBlank;

public class UpdatePasswordRequest {
  @NotBlank(message = "realm is required") private String realm;
  @NotBlank(message = "username is required") private String username;
  @NotBlank(message = "currentPassword is required") private String currentPassword;
  @NotBlank(message = "newPassword is required") private String newPassword;

  public String getRealm() { return realm; }
  public void setRealm(String realm) { this.realm = realm; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public String getCurrentPassword() { return currentPassword; }
  public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }

  public String getNewPassword() { return newPassword; }
  public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}
