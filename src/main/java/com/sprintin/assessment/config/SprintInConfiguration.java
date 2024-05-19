package com.sprintin.assessment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "sprintin")
public class SprintInConfiguration {

  private String bucketName;

  private String subscriptionName;
}
