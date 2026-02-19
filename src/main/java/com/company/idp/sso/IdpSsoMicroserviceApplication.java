package com.company.idp.sso;

import com.company.idp.sso.config.SsoProperties;
import com.company.idp.sso.config.HttpClientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SsoProperties.class, HttpClientProperties.class})
public class IdpSsoMicroserviceApplication {
  public static void main(String[] args) {
    SpringApplication.run(IdpSsoMicroserviceApplication.class, args);
  }
}
