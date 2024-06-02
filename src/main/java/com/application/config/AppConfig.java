package com.application.config;

import com.application.dto.DatabaseInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public DatabaseInfo databaseInfo() {


        DatabaseInfo databaseInfo = new DatabaseInfo();
        databaseInfo.setUsername("elastic");
        databaseInfo.setPassword("fcgfdhf");
        databaseInfo.setHostname("localhost");
        databaseInfo.setPort(9200);
        databaseInfo.setScheme("https");
        return databaseInfo;
    }
}
