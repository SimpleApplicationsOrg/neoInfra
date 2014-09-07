package org.simpleapplications.infra.model.service;

import org.simpleapplications.infra.model.entity.Application;
import org.simpleapplications.infra.model.entity.Label;

public interface ApplicationService {

	Application findApplication(String string);

	void save(Application application);

	void delete(Application application);
	
	Label loadLabel(Label label);
	
	void addLabel(Application application, Label label);
	
	void removeLabel(Application application, Label label);
	
}
