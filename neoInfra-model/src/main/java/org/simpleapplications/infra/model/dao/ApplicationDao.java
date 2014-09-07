package org.simpleapplications.infra.model.dao;

import org.simpleapplications.infra.model.entity.Application;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface ApplicationDao extends GraphRepository<Application> {

  Application findByCode(String code);

}
