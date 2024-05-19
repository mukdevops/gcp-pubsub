package com.sprintin.assessment.config;

import lombok.Data;

@Data
public class CloudSqlDatabaseConfiguration {

  private final String dbInstanceName;

  private final String dbUserName;

  private final String dbPassword;

  private final String dbName;
}
