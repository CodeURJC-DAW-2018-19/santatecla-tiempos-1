package es.urjc.code.daw.controllers;

import javax.servlet.http.HttpServletRequest;

import es.urjc.code.daw.event.Event;
import es.urjc.code.daw.event.EventService;
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
import es.urjc.code.daw.interval.Interval;
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

    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public String root(Model model, HttpServletRequest request) {
        init(model, request);
        return "home";
    }

    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model, HttpServletRequest request) {
        init(model, request);

        return "index";
    }

    /*
    START CATEGORY
     */

    @RequestMapping(value = "/addCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public String newCategory(Model model, @RequestParam String categoryName, HttpServletRequest request) {
        Category newCategory = new Category(categoryName);
        CategoryService.save(newCategory);
        init(model, request);
        return "home";
    }

    @RequestMapping(value = "/deleteCategory{idCategory}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteCategory(Model model, HttpServletRequest request, @PathVariable long idCategory) {
        CategoryService.delete(idCategory);
        init(model, request);
        return "home";
    }

    @RequestMapping(value = "/setCategory{idCategory}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setCategory(Model model, HttpServletRequest request, @PathVariable long idCategory, @RequestParam String categoryName) {
        Category category = new Category(categoryName);
        category.setIdCategory(idCategory);
        CategoryService.save(category);
        init(model, request);
        return "home";
    }

    /*
    END CATEGORY
     */

    /*
    START INTERVAL
     */

    @RequestMapping(value = "/addInterval", method = {RequestMethod.GET, RequestMethod.POST})
    public String newInterval(Model model, @RequestParam String intervalName, @RequestParam String startdate, @RequestParam String enddate, HttpServletRequest request) {
        System.out.println("\n0:\n");

        Interval newInterval = new Interval(intervalName, startdate, enddate);
        System.out.println("1:" + newInterval.toString());
        intervalRepository.save(newInterval);
        System.out.println("2:" + newInterval.toString());

        init(model, request);
        return "home";
    }

    @RequestMapping(value = "/deleteInterval{idInterval}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteInterval(Model model, HttpServletRequest request, @PathVariable long idInterval) {
        intervalRepository.delete(idInterval);
        init(model, request);
        return "home";
    }

    @RequestMapping(value = "/setInterval{idInterval}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setInterval(Model model, HttpServletRequest request, @PathVariable long idInterval, @RequestParam String intervalName, @RequestParam String startdate, @RequestParam String enddate) {
        //intervalRepository.findOne(idInterval).setName(intervalName);
        //intervalRepository.findOne(idInterval).setStart(startdate);
        //intervalRepository.findOne(idInterval).setEnd(enddate);
        Interval newInterval = new Interval(intervalName, startdate, enddate);
        newInterval.setIdInterval(idInterval);
        intervalRepository.save(newInterval);
        init(model, request);
        return "home";
    }

    /*
    END INTERVAL
     */

    @RequestMapping(value = "/addUser")
    public String addUser(Model model, HttpServletRequest request, @RequestParam String name, @RequestParam String email, @RequestParam String password) {
        init(model, request);
        System.out.println(name);
        System.out.println(email);
        System.out.println(password);

        User NewUser = new User(name, email, password, "ROLE_STUDENT");
        UserService.save(NewUser);

        return "home";
    }

    //Método que inicializa la bbdd de toda la página
    //Ir añadiendo campos
    public void init(Model model, HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("\n\n\n" + "-" + userRepository.findByEmail("miguel@gmail.com").getName() + "-" + auth.getName());
        String email = auth.getName(); //get logged in username
        String name = "LOGIN";
        if (userRepository.findByEmail(email) != null)
            name = userRepository.findByEmail(email).getName();
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("estudiante", (request.isUserInRole("STUDENT") ||
                request.isUserInRole("ADMIN")));
        model.addAttribute("username", name);
        System.out.println("\n\n\n" + email + "-" + name + auth.getName());
        model.addAttribute("categorias", categoryRepository.findAll());//Vuelve a cargar las categorías
        model.addAttribute("intervalos", intervalRepository.findAll());//Vuelve a cargar los intervalos

    }

}
