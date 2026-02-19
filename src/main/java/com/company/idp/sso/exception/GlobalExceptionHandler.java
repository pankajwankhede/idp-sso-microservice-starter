package com.company.idp.sso.exception;

import com.company.idp.sso.api.model.ErrorResponse;
import com.company.idp.sso.util.TrackingIdFilter;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  private String trackingId() {
    return MDC.get(TrackingIdFilter.MDC_KEY);
  }

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ErrorResponse> handleApi(ApiException ex) {
    log.warn("API_ERROR code={} msg={}", ex.getErrorCode(), ex.getMessage());

    ErrorResponse r = new ErrorResponse();
    r.setCode(ex.getErrorCode().name());
    r.setMessage(ex.getMessage());
    r.setTrackingId(trackingId());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
    ErrorResponse r = new ErrorResponse();
    r.setCode(ErrorCode.VALIDATION_ERROR.name());
    r.setMessage("Request validation failed");
    r.setTrackingId(trackingId());

    r.setErrors(
        ex.getBindingResult().getFieldErrors().stream()
            .map(fe -> new ErrorResponse.FieldErrorDetail(fe.getField(), safe(fe.getDefaultMessage())))
            .collect(Collectors.toList())
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleConstraint(ConstraintViolationException ex) {
    ErrorResponse r = new ErrorResponse();
    r.setCode(ErrorCode.VALIDATION_ERROR.name());
    r.setMessage(safe(ex.getMessage()));
    r.setTrackingId(trackingId());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAny(Exception ex) {
    log.error("UNHANDLED_ERROR", ex);

    ErrorResponse r = new ErrorResponse();
    r.setCode("INTERNAL_ERROR");
    r.setMessage("Unexpected server error");
    r.setTrackingId(trackingId());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(r);
  }

  private static String safe(String s) {
    return s == null ? "" : s;
  }
}
