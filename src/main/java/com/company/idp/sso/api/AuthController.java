package com.company.idp.sso.api;

import com.company.idp.sso.api.model.ApiResponse;
import com.company.idp.sso.api.model.request.AuthenticateRequest;
import com.company.idp.sso.api.model.response.AuthenticateResponse;
import com.company.idp.sso.domain.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
  private final AuthService authService;
  public AuthController(AuthService authService) { this.authService = authService; }

  @PostMapping("/authenticate")
  public ApiResponse<AuthenticateResponse> authenticate(@Valid @RequestBody AuthenticateRequest req) {
    return ApiResponse.ok(authService.authenticate(req));
  }
}
