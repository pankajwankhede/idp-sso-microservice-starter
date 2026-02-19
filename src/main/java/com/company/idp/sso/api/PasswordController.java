package com.company.idp.sso.api;

import com.company.idp.sso.api.model.ApiResponse;
import com.company.idp.sso.api.model.request.ForgotPasswordRequest;
import com.company.idp.sso.api.model.request.UpdatePasswordRequest;
import com.company.idp.sso.api.model.request.PasswordExpiredUpdateRequest;
import com.company.idp.sso.api.model.response.SimpleActionResponse;
import com.company.idp.sso.domain.service.PasswordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordController {
  private final PasswordService passwordService;
  public PasswordController(PasswordService passwordService) { this.passwordService = passwordService; }

  @PostMapping("/forgotpassword")
  public ApiResponse<SimpleActionResponse> forgotPassword(@Valid @RequestBody ForgotPasswordRequest req) {
    return ApiResponse.ok(passwordService.forgotPassword(req));
  }

  @PostMapping("/updatepassword")
  public ApiResponse<SimpleActionResponse> updatePassword(@Valid @RequestBody UpdatePasswordRequest req) {
    return ApiResponse.ok(passwordService.updatePassword(req));
  }

  @PostMapping("/passwordexpireupdate")
  public ApiResponse<SimpleActionResponse> passwordExpireUpdate(@Valid @RequestBody PasswordExpiredUpdateRequest req) {
    return ApiResponse.ok(passwordService.passwordExpiredUpdate(req));
  }
}
