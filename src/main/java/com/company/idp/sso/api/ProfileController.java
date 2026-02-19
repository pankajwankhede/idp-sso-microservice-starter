package com.company.idp.sso.api;

import com.company.idp.sso.api.model.ApiResponse;
import com.company.idp.sso.api.model.request.ForgotUsernameRequest;
import com.company.idp.sso.api.model.request.UpdateProfileRequest;
import com.company.idp.sso.api.model.response.SimpleActionResponse;
import com.company.idp.sso.domain.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Profile")
@RestController
public class ProfileController {
  private final ProfileService profileService;
  public ProfileController(ProfileService profileService) { this.profileService = profileService; }

  @Operation(summary = "Trigger forgot username (realm routed)")
  @PostMapping("/forgetusername")
  public ApiResponse<SimpleActionResponse> forgotUsername(@Valid @RequestBody ForgotUsernameRequest req) {
    return ApiResponse.ok(profileService.forgotUsername(req));
  }

  @Operation(summary = "Update profile (realm routed)")
  @PostMapping("/updateprofile")
  public ApiResponse<SimpleActionResponse> updateProfile(@Valid @RequestBody UpdateProfileRequest req) {
    return ApiResponse.ok(profileService.updateProfile(req));
  }
}
