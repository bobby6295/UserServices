package com.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("dev")
public class DevelopmentPro {
    @Bean
    public Envconfig getDevelopmentConfig() {
        return new DevelopmentEnv();
    }

}
