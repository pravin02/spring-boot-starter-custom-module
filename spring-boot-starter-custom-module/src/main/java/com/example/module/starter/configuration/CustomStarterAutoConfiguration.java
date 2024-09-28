package com.example.module.starter.configuration;

import com.example.module.starter.service.CustomStarterService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(CustomStarterService.class)
public class CustomStarterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CustomStarterService getCustomStarterService() {
        return new CustomStarterService();
    }

}
