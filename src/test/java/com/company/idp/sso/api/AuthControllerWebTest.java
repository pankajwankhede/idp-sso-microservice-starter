package com.company.idp.sso.api;

import com.company.idp.sso.domain.service.AuthService;
import com.company.idp.sso.api.model.response.AuthenticateResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerWebTest {

  @Autowired MockMvc mvc;
  @MockBean AuthService authService;

  @Test
  void authenticate_returnsWrappedOk() throws Exception {
    AuthenticateResponse r = new AuthenticateResponse();
    r.setAuthenticated(true);
    r.setUserId("u1");
    r.setDisplayName("User One");
    r.setRoles(List.of("USER"));
    r.setPasswordExpired(false);
    r.setNextAction("NONE");

    Mockito.when(authService.authenticate(Mockito.any())).thenReturn(r);

    mvc.perform(post("/authenticate")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"realm\":\"PCC\",\"clientId\":\"pcc\",\"username\":\"u\",\"password\":\"p\"}"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.status").value("OK"))
      .andExpect(jsonPath("$.data.userId").value("u1"));
  }
}
