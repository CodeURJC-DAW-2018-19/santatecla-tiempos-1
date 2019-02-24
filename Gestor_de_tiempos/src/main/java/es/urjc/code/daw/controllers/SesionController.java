package es.urjc.code.daw.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.code.daw.repositories.*;
import es.urjc.code.daw.services.*;
import es.urjc.code.daw.user.Category;

@Controller
public class SesionController {

	@Autowired
    private UserRepository userRepository;
	@Autowired
    private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryService CategoryService;

	@RequestMapping(value="/home", method = { RequestMethod.GET, RequestMethod.POST })
	public String root(Model model,HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		 model.addAttribute("token", token.getToken()); 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("\n\n\n"+"-"+userRepository.findByEmail("miguel@gmail.com").getName()+"-"+auth.getName());
		String email = auth.getName(); //get logged in username
	    String name="LOGIN";
	    if(userRepository.findByEmail(email)!=null)
	    	name=userRepository.findByEmail(email).getName();
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("estudiante",(request.isUserInRole("STUDENT")||
				request.isUserInRole("ADMIN")));
		model.addAttribute("username",name);
		System.out.println("\n\n\n"+email+"-"+name+auth.getName());
		//Categories
		model.addAttribute("categorias",categoryRepository.findAll());
		return "home";
	}
	
	@RequestMapping(value="/index", method = { RequestMethod.GET, RequestMethod.POST })
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
	
	
	@RequestMapping("/addCategory")
	public String nuevaCategoria(Model model, @RequestParam String categoryName) {
		Category NewCategory = new Category (categoryName);
		CategoryService.save(NewCategory);
		model.addAttribute("categorias",categoryRepository.findAll());//Vuelve a cargar las categorías
		return "home";
	}

}
