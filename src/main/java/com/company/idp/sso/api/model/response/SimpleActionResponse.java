package com.company.idp.sso.api.model.response;

public class SimpleActionResponse {
  private boolean accepted;
  private String referenceId;
  private String message;

  public boolean isAccepted() { return accepted; }
  public void setAccepted(boolean accepted) { this.accepted = accepted; }

  public String getReferenceId() { return referenceId; }
  public void setReferenceId(String referenceId) { this.referenceId = referenceId; }

  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }

  public static SimpleActionResponse accepted(String message) {
    SimpleActionResponse r = new SimpleActionResponse();
    r.accepted = true;
    r.message = message;
    return r;
  }
}
