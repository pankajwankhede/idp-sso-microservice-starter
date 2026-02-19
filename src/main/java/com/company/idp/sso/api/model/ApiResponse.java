package com.company.idp.sso.api.model;

import java.time.Instant;

public class ApiResponse<T> {
  private String status; // OK|FAIL
  private String message;
  private String errorCode;
  private T data;
  private Instant timestamp = Instant.now();

  public static <T> ApiResponse<T> ok(T data) {
    ApiResponse<T> r = new ApiResponse<>();
    r.status = "OK";
    r.data = data;
    return r;
  }

  public static <T> ApiResponse<T> fail(String errorCode, String message) {
    ApiResponse<T> r = new ApiResponse<>();
    r.status = "FAIL";
    r.errorCode = errorCode;
    r.message = message;
    return r;
  }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }

  public String getErrorCode() { return errorCode; }
  public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

  public T getData() { return data; }
  public void setData(T data) { this.data = data; }

  public Instant getTimestamp() { return timestamp; }
  public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
