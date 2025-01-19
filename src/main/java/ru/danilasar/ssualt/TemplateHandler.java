package ru.danilasar.ssualt;

import io.micrometer.common.lang.Nullable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class TemplateHandler implements HandlerMethodReturnValueHandler {

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return returnType.getParameterType().isAssignableFrom(Multitemplate.class);
	}

	@Override
	public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		String acceptHeader = webRequest.getHeader("Accept");

		String viewName = ((Multitemplate) returnValue).template;

		System.out.printf(acceptHeader);

		if (acceptHeader != null && acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
			mavContainer.setViewName(viewName + ".html");
		} else {
			mavContainer.setViewName(viewName + ".txt");
		}
	}
}
