package org.simpleapplications.infra.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
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
public class InfraModelApplication extends Neo4jConfiguration implements CommandLineRunner {

  private static Logger logger = LoggerFactory
      .getLogger(InfraModelApplication.class);

  public InfraModelApplication() {
    setBasePackage("org.simpleapplications.infra.model.entity");
  }

  @Bean
  GraphDatabaseService graphDatabaseService() {
    return new GraphDatabaseFactory().newEmbeddedDatabase("neoInfra.db");
  }

  public static void main(String[] args) {
    SpringApplication.run(InfraModelApplication.class, args);
  }
  
  @Override
  public void run(String... args) throws Exception {
    ExecutionEngine engine = new ExecutionEngine(getGraphDatabaseService());
    List<String> queries = getQueries("import.cql");
    for (String query : queries) {
      try (Transaction tx = getGraphDatabaseService().beginTx()) {
        logger.info(engine.execute(query).dumpToString());
        tx.success();
      } catch(Exception e) {
        logger.debug(e.getMessage());
      }
    }
  }

  private List<String> getQueries(String filename) {
    List<String> lines = null;
    try {
      InputStream is = getClass().getResourceAsStream("/" + filename);
      Reader fileReader = new InputStreamReader(is);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      lines = new ArrayList<String>();
      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        lines.add(line);
      }
      bufferedReader.close();
    } catch (IOException io) {
      logger.error("getQueries() IOException", io);
    }
    return lines;
  }
  
}
