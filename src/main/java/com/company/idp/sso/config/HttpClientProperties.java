package com.company.idp.sso.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "http.client")
public class HttpClientProperties {
  private int connectTimeoutMillis = 3000;
  private int readTimeoutMillis = 8000;

  public int getConnectTimeoutMillis() { return connectTimeoutMillis; }
  public void setConnectTimeoutMillis(int connectTimeoutMillis) { this.connectTimeoutMillis = connectTimeoutMillis; }

  public int getReadTimeoutMillis() { return readTimeoutMillis; }
  public void setReadTimeoutMillis(int readTimeoutMillis) { this.readTimeoutMillis = readTimeoutMillis; }
}
