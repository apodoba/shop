In file src/main/resources/jdbc.properties specify full name for Derby database

To create database schema run
  mvn validate liquibase:update -pl database

To populate test data run
  mvn validate sql:execute -pl database