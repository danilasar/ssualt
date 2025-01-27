package ru.danilasar.ssualt;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Cache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan
public class SpringWebConfig implements WebMvcConfigurer, ApplicationContextAware {
	private ApplicationContext applicationContext;
	private final TemplateHandler templateHandler;
	private final SpringTemplateEngine springTemplateEngine;

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(false) // Учитываем заголовок Accept
				.defaultContentType(MediaType.TEXT_PLAIN) // Устанавливаем HTML по умолчанию
				.mediaType("html", MediaType.TEXT_HTML)
				.mediaType("txt", MediaType.TEXT_PLAIN);
	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
		handlers.add(0, templateHandler);
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		return springTemplateEngine;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**") // url
			.addResourceLocations("/static", "classpath:/static/") // file path
			.setCacheControl(CacheControl.maxAge(Duration.ofDays(0))); // todo replace 0 to 365
	}

	private ClassLoaderTemplateResolver textTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/templates/");
		// templateResolver.setSuffix(".txt");
		templateResolver.setTemplateMode("TEXT");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCheckExistence(true);
		return templateResolver;
	}

	private ClassLoaderTemplateResolver htmlTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/templates/"); // Папка с текстовыми шаблонами
		// templateResolver.setSuffix(".html"); // Расширение файлов
		templateResolver.setTemplateMode("HTML"); // Режим текста
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCheckExistence(true);
		return templateResolver;
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public SpringWebConfig() {
		super();
		springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.addTemplateResolver(htmlTemplateResolver());
		springTemplateEngine.addTemplateResolver(textTemplateResolver());
		templateHandler = new TemplateHandler();
	}
}
