package org.simpleapplications.infra.presentation;

import java.util.Set;

import org.simpleapplications.infra.model.entity.Application;
import org.simpleapplications.infra.model.entity.Label;
import org.simpleapplications.infra.model.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "/ui")
@Title("Test")
@Theme("valo")
public class PresentationUI extends UI {

	private static final long serialVersionUID = -1992560966538224736L;

	@Autowired
	ApplicationService service;

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout layout = new VerticalLayout();
		setContent(layout);

		Application application = service.findApplication("Test");
		Set<Label> labels = application.getLabels();

		Table labelTable = new Table(application.getName());
		labelTable.addContainerProperty("Code", String.class, null);
		labelTable.addContainerProperty("Language", String.class, null);
		labelTable.addContainerProperty("Text", String.class, null);
		
		for (Label label : labels) {
			label = service.loadLabel(label);
		  labelTable.addItem(new Object[] { label.getCode(),
					label.getLanguage(), label.getText() }, null);
		}
		
		layout.addComponent(labelTable);

	}

}
