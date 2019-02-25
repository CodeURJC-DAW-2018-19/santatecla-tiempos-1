package es.urjc.code.daw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.code.daw.category.Category;
import es.urjc.code.daw.category.CategoryRepository;
import es.urjc.code.daw.category.CategoryService;
import es.urjc.code.daw.interval.IntervalRepository;
import es.urjc.code.daw.user.User;
import es.urjc.code.daw.user.UserRepository;
import es.urjc.code.daw.user.UserService;

@Controller
public class SesionController {
	//Repositories
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private CategoryRepository categoryRepository;
	@Autowired
    private IntervalRepository intervalRepository;
	
	//Services
	@Autowired
	private CategoryService CategoryService;
	@Autowired
	private UserService UserService;

	@RequestMapping(value="/home", method = { RequestMethod.GET, RequestMethod.POST })
	public String root(Model model,HttpServletRequest request) {
		init(model,request);
		
		
		return "home";
	}
	
	@RequestMapping(value="/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model,HttpServletRequest request) {
		init(model,request);

		return "index";
	}
	
	
	@RequestMapping(value ="/addCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public String nuevaCategoria(Model model, @RequestParam String categoryName,HttpServletRequest request) {
		Category NewCategory = new Category (categoryName);
		CategoryService.save(NewCategory);
		init(model,request);
		return "home";
	}
	
	@RequestMapping(value ="/deleteCategory{idCategory}", method =  { RequestMethod.GET, RequestMethod.POST })
	public String deleteCategory(Model model,HttpServletRequest request, @PathVariable long idCategory) {
		System.out.println ("ENTRAMOS");
		System.out.println (idCategory);
		CategoryService.delete(idCategory);
		init(model,request);
		return "home";
	}
	
	@RequestMapping(value="/addUser")
	public String addUser(Model model,HttpServletRequest request,@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		init(model,request);
		System.out.println (name);
		System.out.println (email);
		System.out.println (password);

        User NewUser =  new User(name, email,password , "ROLE_STUDENT");
		UserService.save(NewUser);
		
		return "home";
	}
	
	//Método que inicializa la bbdd de toda la página
	//Ir añadiendo campos
	public void init(Model model,HttpServletRequest request) {
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
		model.addAttribute("categorias",categoryRepository.findAll());//Vuelve a cargar las categorías
		model.addAttribute("intervalos",intervalRepository.findAll());//Vuelve a cargar los intervalos

	}

}
