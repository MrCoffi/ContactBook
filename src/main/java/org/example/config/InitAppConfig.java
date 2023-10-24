package org.example.config;

import org.example.ContactImpl;
import org.example.ContactWorkerInit;
import org.springframework.context.annotation.*;


@Configuration
@Profile("init")
public class InitAppConfig {

    @Bean
    public ContactImpl contact() {
        return new ContactWorkerInit();
    }

}
