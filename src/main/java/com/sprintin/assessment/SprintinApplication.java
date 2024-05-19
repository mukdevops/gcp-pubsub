package com.sprintin.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SprintinApplication {


    public static void main(final String[] args) {
        SpringApplication.run(SprintinApplication.class, args);
    }

}
