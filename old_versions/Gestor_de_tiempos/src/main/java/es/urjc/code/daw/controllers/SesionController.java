package es.urjc.code.daw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.urjc.code.daw.repositories.UserRepository;
@Controller
public class SesionController {

	@Autowired
    private UserRepository userRepository;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    
		String email = auth.getName(); //get logged in username
	    String name="LOGIN";
	    if(userRepository.findByEmail(email)!=null)
	    	name=userRepository.findByEmail(email).getName();
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("estudiante",(request.isUserInRole("STUDENT")||
				request.isUserInRole("ADMIN")));
		model.addAttribute("username",name);
		System.out.println("\n\n\n"+email+"-"+name+auth.getName());

		return "index";
	}
}
