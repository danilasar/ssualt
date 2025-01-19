package ru.danilasar.ssualt;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan
public class SpringWebConfig implements WebMvcConfigurer, ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// configurer.mediaType("json", MediaType.APPLICATION_JSON);
		// configurer.mediaType("")
	}

	public void setApplicationContext(final ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public SpringWebConfig() {
		super();
	}
}
