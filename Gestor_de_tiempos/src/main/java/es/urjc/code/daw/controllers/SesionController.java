package es.urjc.code.daw.controllers;

import javax.servlet.http.HttpServletRequest;

import es.urjc.code.daw.event.Event;
import es.urjc.code.daw.event.EventRepository;
import es.urjc.code.daw.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.urjc.code.daw.category.Category;
import es.urjc.code.daw.category.CategoryRepository;
import es.urjc.code.daw.category.CategoryService;
import es.urjc.code.daw.interval.Interval;
import es.urjc.code.daw.interval.IntervalRepository;
import es.urjc.code.daw.user.User;
import es.urjc.code.daw.user.UserRepository;
import es.urjc.code.daw.user.UserService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class SesionController {
    //Repositories
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private IntervalRepository intervalRepository;
    @Autowired
    private EventRepository eventRepository;

    //Services
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public String root(Model model, Pageable page, HttpServletRequest request, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {
        init(model, page, request, oldpage);
        return "home";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model, Pageable page, HttpServletRequest request, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {
        init(model, page, request, oldpage);

        return "home";
    }

    /*
    START CATEGORY
     */

    @RequestMapping(value = "/addCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public String newCategory(Model model, Pageable page, @RequestParam String categoryName, HttpServletRequest request, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {
        Category newCategory = new Category(categoryName);
        categoryService.save(newCategory);
        init(model, page, request, oldpage);
        return "home";
    }

    @RequestMapping(value = "/deleteCategory{idCategory}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteCategory(Model model, Pageable page, HttpServletRequest request, @PathVariable long idCategory, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {
        categoryService.delete(idCategory);
        init(model, page, request, oldpage);
        return "home";
    }

    @RequestMapping(value = "/setCategory{idCategory}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setCategory(Model model, Pageable page, HttpServletRequest request, @PathVariable long idCategory, @RequestParam String categoryName, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {
        Category category = new Category(categoryName);
        category.setIdCategory(idCategory);
        categoryService.save(category);
        init(model, page, request, oldpage);
        return "home";
    }

    /*
    END CATEGORY
     */

    /*
    START INTERVAL
     */

    @RequestMapping(value = "/addInterval", method = {RequestMethod.GET, RequestMethod.POST})
    public String newInterval(Model model, Pageable page, @RequestParam String intervalName, @RequestParam String startdate, @RequestParam String enddate, HttpServletRequest request, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {


        Interval newInterval = new Interval(intervalName, startdate, enddate);
        intervalRepository.save(newInterval);

        init(model, page, request, oldpage);
        return "home";
    }

    @RequestMapping(value = "/deleteInterval{idInterval}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteInterval(Model model, Pageable page, HttpServletRequest request, @PathVariable long idInterval, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {
        intervalRepository.delete(idInterval);
        init(model, page, request, oldpage);
        return "home";
    }

    @RequestMapping(value = "/setInterval{idInterval}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setInterval(Model model, Pageable page, HttpServletRequest request, @PathVariable long idInterval, @RequestParam String intervalName, @RequestParam String startdate, @RequestParam String enddate, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {
        //intervalRepository.findOne(idInterval).setName(intervalName);
        //intervalRepository.findOne(idInterval).setStart(startdate);
        //intervalRepository.findOne(idInterval).setEnd(enddate);
        Interval newInterval = new Interval(intervalName, startdate, enddate);
        newInterval.setIdInterval(idInterval);
        intervalRepository.save(newInterval);
        init(model, page, request, oldpage);
        return "home";
    }

    /*
    END INTERVAL
     */


    @Autowired
    private EventService eventService;

    /*
    START EVENTS
     */

    @RequestMapping(value = "/addEvent", method = {RequestMethod.GET})
    public String newEvent(Model model, Pageable page, Event event, HttpServletRequest request, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage)
    {
        init(model, page, request, oldpage);
        return "redirect:/home";
    }

    @RequestMapping(value = "/addEvent", method = {RequestMethod.POST})
    public String newEvent(Model model, Pageable page, Event event, @RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes, HttpServletRequest request, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {

        //Set the photo for the event.
        if (!multipartFile.isEmpty()) {
            String rootPath = "C://Temp//uploads";
            try {
                byte[] bytes = multipartFile.getBytes();
                Path fullPath = Paths.get(rootPath + "//" + multipartFile.getOriginalFilename());
                Files.write(fullPath, bytes);
                redirectAttributes.addFlashAttribute("info", "Imagen subida correctamente ' " + multipartFile.getOriginalFilename() + "'");
                event.setEventPhoto(multipartFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        eventService.save(event);
        init(model, page, request, oldpage);
        return "redirect:/home";
    }


    @RequestMapping(value = "/deleteEvent{idEvent}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteEvent(Model model, Pageable page, HttpServletRequest request, @PathVariable long idEvent, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {
        eventService.delete(idEvent);
        init(model, page, request, oldpage);
        return "home";
    }

    @RequestMapping(value = "/setEvent{idEvent}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setEvent(Model model, Pageable page, HttpServletRequest request, @PathVariable long idEvent, @RequestParam String eventName, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {
        Event event = new Event(eventName);
        event.setIdEvent(idEvent);
        eventService.save(event);
        init(model, page, request, oldpage);
        return "home";
    }

    /*
    END EVENTS
     */


    @RequestMapping(value = "/addUser")
    public String addUser(Model model, Pageable page, HttpServletRequest request, @RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {
        init(model, page, request, oldpage);


        User NewUser = new User(name, email, password, "ROLE_STUDENT");
        userService.save(NewUser);

        return "home";
    }

    //Método que inicializa la bbdd de toda la página
    //Ir añadiendo campos
    public void init(Model model, Pageable page, HttpServletRequest request,
                     @RequestParam(name = "oldpage", required = false, defaultValue = "0") Integer oldpage) {
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
        //Page<Category> categorias = categoryRepository.findByUserAndStatus(userRepository.fin, "Recogido", new PageRequest(0,10*(oldpage+1)));
        Page<Category> categorias = categoryRepository.findAll(new PageRequest(0, 10 * (oldpage + 1)));

        model.addAttribute("categorias", categorias);
        model.addAttribute("nextOldPage", oldpage + 1);
        model.addAttribute("intervalos", intervalRepository.findAll());//Load again the intervals.
        model.addAttribute("eventos", eventRepository.findAll());//Load again.
        model.addAttribute("categoryList", categoryRepository.findAllByOrderByIdCategoryAsc()); //Load the list of categories assigned to the events.

    }

}
