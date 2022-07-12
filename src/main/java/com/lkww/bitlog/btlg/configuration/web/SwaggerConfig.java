package com.lkww.bitlog.btlg.configuration.web;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.SpringDocConfiguration;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Nonnull;

@Configuration
public class SwaggerConfig {
    @Bean
    SpringDocConfiguration springDocConfiguration() {
        return new SpringDocConfiguration();
    }

    @Bean
    public SpringDocConfigProperties springDocConfigProperties() {
        return new SpringDocConfigProperties();
    }

    @Bean
    public OpenAPI openAPI(BuildProperties buildProperties) {
        return new OpenAPI().info(
            new Info().title("bitlog-btlg-cmp")
                .description("bitlog-btlg-cmp")
                .version(buildProperties.getVersion())
        );
    }

    @Bean
    public WebMvcConfigurer swaggerConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(@Nonnull ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/openapi/openapi.yaml")
                    .addResourceLocations("classpath:/openapi");
            }
        };
    }
}
