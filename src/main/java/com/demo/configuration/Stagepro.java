package com.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("stage")
public class Stagepro {


    @Bean
    public Envconfig getDevelopmentConfig() {


        return new stageenv();

    }


}
