package org.simpleapplications.infra.model.service;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.simpleapplications.infra.model.dao.ApplicationDao;
import org.simpleapplications.infra.model.dao.LabelDao;
import org.simpleapplications.infra.model.entity.Application;
import org.simpleapplications.infra.model.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

  @Autowired
  ApplicationDao applicationDao;

  @Autowired
  LabelDao labelDao;

  @Autowired
  private GraphDatabaseService graphDb;

  @Override
  public Application findApplication(String code) {
    return applicationDao.findByCode(code);
  }

  @Override
  public void save(Application application) {
    applicationDao.save(application);
  }

  @Override
  public void delete(Application application) {
    applicationDao.delete(application);
  }

  @Override
  public Label loadLabel(Label label) {
    return labelDao.findOne(label.getId());   
  }

  @Override
  public void addLabel(Application application, Label label) {
    try (Transaction tx = graphDb.beginTx()) {
      application.getLabels().add(label);
      tx.success();
    }
  }

  @Override
  public void removeLabel(Application application, Label label) {
    try (Transaction tx = graphDb.beginTx()) {
      application.getLabels().remove(label);
      tx.success();
    }
  }

}
