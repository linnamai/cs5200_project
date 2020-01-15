package com.dumplings.pleyt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
  private CorsConfiguration buildConfig() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedOrigin("*"); // 1 Allow access from any domain
    corsConfiguration.addAllowedHeader("*"); // 2 Allow any header
    corsConfiguration.addAllowedMethod("*"); // 3 Allow all method(GET POST etc)
    return corsConfiguration;
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", buildConfig()); // 4
    return new CorsFilter(source);
  }
}
