package com.company.idp.sso.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class TrackingIdFilter extends OncePerRequestFilter {
  public static final String HEADER = "X-Tracking-Id";
  public static final String MDC_KEY = "trackingId";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String trackingId = request.getHeader(HEADER);
    if (trackingId == null || trackingId.isBlank()) trackingId = UUID.randomUUID().toString();
    MDC.put(MDC_KEY, trackingId);
    response.setHeader(HEADER, trackingId);
    try {
      filterChain.doFilter(request, response);
    } finally {
      MDC.remove(MDC_KEY);
    }
  }
}
