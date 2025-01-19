package ru.danilasar.ssualt;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
	@GetMapping(value = "/", produces = { MediaType.TEXT_HTML_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public Multitemplate index(@RequestParam(name = "name", required = false, defaultValue = "World") String name) {
		return new Multitemplate("index");
	}

}
