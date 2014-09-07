package org.simpleapplications.infra.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.simpleapplications.infra.model.InfraModelApplicationTest;
import org.simpleapplications.infra.model.entity.Application;
import org.simpleapplications.infra.model.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InfraModelApplicationTest.class)
@ActiveProfiles("test")
public class ApplicationServiceTest {

  @Autowired
  ApplicationService service;

  @Test
  public void testFindApplication() {
    Application application = service.findApplication("test");
    assertNotNull(application);
  }

  @Test
  public void testSaveApplication() {
    Application application = new Application();
    application.setCode("test2");
    application.setName("Second Test Application");
    service.save(application);
    assertNotNull(service.findApplication("test2"));
  }

  @Test
  public void testDeleteApplication() {
    Application application = service.findApplication("test");
    service.delete(application);
    assertNull(service.findApplication("test"));
  }

  @Test
  public void testAddLabel() {
    Application application = service.findApplication("test1");
    Label label = new Label();
    label.setCode("test1.label");
    label.setLanguage("fr");
    label.setText("Label for test1");
    service.addLabel(application, label);
    service.save(application);
    assertEquals(2, application.getLabels().size());
  }

  @Test
  public void testRemoveLabel() {
    Application application = service.findApplication("test3");
    Label label = application.getLabels().iterator().next();
    service.removeLabel(application, label);
    service.save(application);
    assertEquals(0, application.getLabels().size());
  }

  @Test
  public void testModifyLabel() {
    Application application = service.findApplication("test1");
    Label label = application.getLabels().iterator().next();
    label.setText("MODIFIED TEXT");
    service.save(application);
    assertEquals("MODIFIED TEXT", application.getLabels().iterator().next().getText());
  }
}
