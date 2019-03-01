package es.urjc.code.daw.controllers;

import javax.servlet.http.HttpServletRequest;

import es.urjc.code.daw.event.Event;
import es.urjc.code.daw.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.urjc.code.daw.category.Category;
import es.urjc.code.daw.category.CategoryRepository;
import es.urjc.code.daw.interval.IntervalRepository;
import es.urjc.code.daw.user.User;
import es.urjc.code.daw.user.UserRepository;
import es.urjc.code.daw.user.UserService;

@Controller
public class SesionController {
    //Repositories
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  CategoryRepository categoryRepository;
    @Autowired
    private  IntervalRepository intervalRepository;
    @Autowired
    private  EventRepository eventRepository;

    //Services

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public String root(Model model, HttpServletRequest request, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage) {
        init(model, request, categorypage,eventpage);
        return "home";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model, HttpServletRequest request, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage) {
        init(model, request, categorypage,eventpage);

        return "home";
    }

   

    /*
    END CATEGORY
     */

    
   

    


    @RequestMapping(value = "/addUser")
    public String addUser(Model model, HttpServletRequest request, @RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage) {
        init(model, request, categorypage,eventpage);


        User NewUser = new User(name, email, password, "ROLE_STUDENT");
        userService.save(NewUser);

        return "home";
    }

    //Método que inicializa la bbdd de toda la página
    //Ir añadiendo campos
    public  void init(Model model, HttpServletRequest request,
    		@RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    Integer eventpage){
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); //get logged in username
        String name = "LOGIN";

        if (userRepository.findByEmail(email) != null) {
            name = userRepository.findByEmail(email).getName();
        }

        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("estudiante", (request.isUserInRole("STUDENT") ||
                request.isUserInRole("ADMIN")));
        model.addAttribute("username", name);

        //model.addAttribute("categorias", categoryRepository.findAll());//Vuelve a cargar las categorías
        //Page<Category> categorias = categoryRepository.findByUserAndStatus(userRepository.fin, "Recogido", new PageRequest(0,10*(categorypage+1)));
        Page<Category> categories = categoryRepository.findAll(new PageRequest(0, 10 * (categorypage + 1)));
        Page<Event> events = eventRepository.findAll(new PageRequest(0, 10 * (eventpage + 1)));

        model.addAttribute("categorias", categories);
        model.addAttribute("nextCategoryPage", categorypage + 1);
        model.addAttribute("intervalos", intervalRepository.findAll());//Load again the intervals.
        model.addAttribute("eventos",events);//Load again.
        model.addAttribute("nextEventPage", eventpage + 1);
        model.addAttribute("categoryList", categoryRepository.findAllByOrderByIdCategoryAsc()); //Load the list of categories assigned to the events.

    }

}
