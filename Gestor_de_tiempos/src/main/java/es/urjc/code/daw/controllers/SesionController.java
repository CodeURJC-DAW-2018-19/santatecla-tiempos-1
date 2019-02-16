package es.urjc.code.daw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SesionController {

	

	@RequestMapping("/")
	public String index(Model model) {

		

		return "index";
	}
}
