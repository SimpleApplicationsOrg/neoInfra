package org.simpleapplications.infra.model;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@Configuration
@ComponentScan
@EnableNeo4jRepositories(basePackages = "org.simpleapplications.infra.model.dao")
@EnableAutoConfiguration
public class InfraModelApplicationTest extends Neo4jConfiguration {

  public InfraModelApplicationTest() {
    setBasePackage("org.simpleapplications.infra.model.entity");
  }

  @Bean
  GraphDatabaseService graphDatabaseService() {
    return new GraphDatabaseFactory().newEmbeddedDatabase("neoInfraTest.db");
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(InfraModelApplicationTest.class, args);
  }

}
