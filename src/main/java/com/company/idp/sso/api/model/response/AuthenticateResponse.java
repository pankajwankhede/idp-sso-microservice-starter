package com.company.idp.sso.api.model.response;

import java.util.List;

public class AuthenticateResponse {
  private boolean authenticated;
  private String userId;
  private String displayName;
  private List<String> roles;
  private boolean passwordExpired;
  private String nextAction;

  public boolean isAuthenticated() { return authenticated; }
  public void setAuthenticated(boolean authenticated) { this.authenticated = authenticated; }

  public String getUserId() { return userId; }
  public void setUserId(String userId) { this.userId = userId; }

  public String getDisplayName() { return displayName; }
  public void setDisplayName(String displayName) { this.displayName = displayName; }

  public List<String> getRoles() { return roles; }
  public void setRoles(List<String> roles) { this.roles = roles; }

  public boolean isPasswordExpired() { return passwordExpired; }
  public void setPasswordExpired(boolean passwordExpired) { this.passwordExpired = passwordExpired; }

  public String getNextAction() { return nextAction; }
  public void setNextAction(String nextAction) { this.nextAction = nextAction; }
}
