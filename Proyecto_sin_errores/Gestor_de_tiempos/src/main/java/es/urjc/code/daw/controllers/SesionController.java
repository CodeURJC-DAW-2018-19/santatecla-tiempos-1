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

	@RequestMapping(value="/", method = { RequestMethod.GET, RequestMethod.POST })
	public String root(Model model,HttpServletRequest request) {
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
	
	@RequestMapping(value="/error",method = RequestMethod.GET)
	public String errors(Model model,HttpServletRequest request) {
		String errorMsg = "";
        int httpErrorCode = getErrorCode(request);
		
		switch (httpErrorCode) {
            case 400: {
                errorMsg = "400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "500. Internal Server Error";
                break;
            }
        }
		
		model.addAttribute("errorMsg",errorMsg);
		
		return "error";
	}
	
     
    private int getErrorCode(HttpServletRequest request) {
        return (Integer) request
          .getAttribute("javax.servlet.error.status_code");
    }
}
