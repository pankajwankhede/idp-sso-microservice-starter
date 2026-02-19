package com.company.idp.sso.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Schema(
    name = "ErrorResponse",
    description = "Standard error response returned by the service when a request fails."
)
public class ErrorResponse {

  @Schema(description = "Machine-readable error code.", example = "VALIDATION_ERROR")
  private String code;

  @Schema(description = "Human-readable error message.", example = "Request validation failed")
  private String message;

  @Schema(description = "End-to-end request tracking id (from X-Tracking-Id header).", example = "b7b2b6f0-7a2f-4f0c-9d0f-2d6bb8b7c7a1")
  private String trackingId;

  @Schema(description = "Optional list of detailed field-level errors (e.g., validation errors).")
  private List<FieldErrorDetail> errors = new ArrayList<>();

  @Schema(description = "Server timestamp when the error response was created.")
  private Instant timestamp = Instant.now();

  public String getCode() { return code; }
  public void setCode(String code) { this.code = code; }

  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }

  public String getTrackingId() { return trackingId; }
  public void setTrackingId(String trackingId) { this.trackingId = trackingId; }

  public List<FieldErrorDetail> getErrors() { return errors; }
  public void setErrors(List<FieldErrorDetail> errors) { this.errors = errors; }

  public Instant getTimestamp() { return timestamp; }
  public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

  @Schema(
      name = "FieldErrorDetail",
      description = "Field-level error details."
  )
  public static class FieldErrorDetail {

    @Schema(description = "Request field name.", example = "username")
    private String field;

    @Schema(description = "Validation or business error message.", example = "username is required")
    private String message;

    public FieldErrorDetail() {}

    public FieldErrorDetail(String field, String message) {
      this.field = field;
      this.message = message;
    }

    public String getField() { return field; }
    public void setField(String field) { this.field = field; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
  }
}
