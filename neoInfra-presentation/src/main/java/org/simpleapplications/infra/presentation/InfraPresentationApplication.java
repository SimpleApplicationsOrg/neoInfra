package org.simpleapplications.infra.presentation;

import org.simpleapplications.infra.model.InfraModelApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class InfraPresentationApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(InfraModelApplication.class).child(
				InfraPresentationApplication.class);
	}

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(InfraModelApplication.class).child(
				InfraPresentationApplication.class).run(args);
	}

}
