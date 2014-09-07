package org.simpleapplications.infra.model.dao;

import org.simpleapplications.infra.model.entity.Label;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface LabelDao extends GraphRepository<Label> {

}
