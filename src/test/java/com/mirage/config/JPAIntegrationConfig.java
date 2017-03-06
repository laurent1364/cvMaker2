package com.mirage.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Mirage on 03/03/2017.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.mirage")
public class JPAIntegrationConfig {
}
