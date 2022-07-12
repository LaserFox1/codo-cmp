package com.lkww.bitlog.btlg.configuration.web;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.actuate.metrics.export.prometheus.PrometheusScrapeEndpoint;
import org.springframework.boot.actuate.web.mappings.MappingsEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // enable basic auth
     /*   http.httpBasic();

        // allow openapi/swagger
        http.authorizeRequests()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/swagger-config", "/openapi/openapi.yaml")
                .authenticated();

        // allow actuator
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.to(
                        HealthEndpoint.class,
                        InfoEndpoint.class,
                        MappingsEndpoint.class,
                        MetricsEndpoint.class,
                        PrometheusScrapeEndpoint.class
                )).anonymous();

        // allow /api endpoints
        http.authorizeRequests()
                .antMatchers("/api/**")
                .authenticated();*/

        // deny everything else
        http.authorizeRequests()
                .anyRequest()
                .permitAll();
    }
}
