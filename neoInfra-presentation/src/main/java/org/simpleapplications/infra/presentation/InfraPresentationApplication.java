package org.simpleapplications.infra.presentation;

import org.simpleapplications.infra.model.InfraModelApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.internal.VaadinSessionScope;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableVaadin
public class InfraPresentationApplication extends SpringBootServletInitializer {

  @Bean
  static VaadinSessionScope vaadinSessionScope() {
      return new VaadinSessionScope();
  }  
  
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
