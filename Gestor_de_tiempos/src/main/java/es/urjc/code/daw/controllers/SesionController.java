package es.urjc.code.daw.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.urjc.code.daw.EncoderDecoderBase64;
import es.urjc.code.daw.category.Category;
import es.urjc.code.daw.category.CategoryRepository;
import es.urjc.code.daw.interval.Interval;
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

    private List<Interval> intervaltabs=new ArrayList<Interval>();
    private Set<Interval> hijos = new HashSet<Interval>();
    //Services

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public String root(Model model, HttpServletRequest request, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
	@RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
    	
    	init(model, request, categorypage,eventpage,intervalpage);
        return "home";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model, HttpServletRequest request, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
	@RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        
    	
    	init(model, request, categorypage,eventpage,intervalpage);

        return "home";
    }

   
    /*
    START CATEGORY
     */

    @RequestMapping(value = "/addCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public String newCategory(Model model, @RequestParam String categoryName, HttpServletRequest request, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
    @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        Category newCategory = new Category(categoryName);
        categoryRepository.save(newCategory);
        init(model, request, categorypage,eventpage,intervalpage);
        return "home";
    }

    @RequestMapping(value = "/deleteCategory{idCategory}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteCategory(Model model, HttpServletRequest request, @PathVariable long idCategory, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
    @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        categoryRepository.delete(idCategory);
        init(model, request, categorypage,eventpage,intervalpage);
        return "home";
    }

    @RequestMapping(value = "/setCategory{idCategory}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setCategory(Model model, HttpServletRequest request, @PathVariable long idCategory, @RequestParam String categoryName, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
    @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        Category category = new Category(categoryName);
        category.setIdCategory(idCategory);
        categoryRepository.save(category);
        init(model, request, categorypage,eventpage,intervalpage);
        return "home";
    }

    /*
    END CATEGORY
     */

    @RequestMapping(value = "/addEvent", method = {RequestMethod.GET})
    public String newEvent(Model model, Event event, HttpServletRequest request, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
                           @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
                           @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        init(model, request, categorypage, eventpage, intervalpage);
        return "redirect:/home";
    }

    @RequestMapping(value = "/addEvent", method = {RequestMethod.POST})
    public String newEvent(Model model, Event event, @RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes, HttpServletRequest request, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
                           @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
                           @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage
                           ) {

        //Set the photo for the event.
        if (!multipartFile.isEmpty()) {
            //String rootPath = "C://Temp//uploads";
            try {
                byte[] bytesPhoto = multipartFile.getBytes();
                //Path fullPath = Paths.get(rootPath + "//" + multipartFile.getOriginalFilename());
                //Files.write(fullPath, bytes);
                redirectAttributes.addFlashAttribute("info", "Imagen subida correctamente ' " + multipartFile.getOriginalFilename() + "'");
                event.setEventPhoto(EncoderDecoderBase64.Encode(bytesPhoto));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        eventRepository.save(event);
        init(model, request, categorypage, eventpage, intervalpage);
        return "redirect:/home";
    }


    @RequestMapping(value = "/deleteEvent{idEvent}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteEvent(Model model, HttpServletRequest request, @PathVariable long idEvent, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
                              @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
                              @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        eventRepository.delete(idEvent);
        init(model, request, categorypage, eventpage, intervalpage);
        return "home";
    }

    @RequestMapping(value = "/setEvent{idEvent}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setEvent(Model model, HttpServletRequest request, @PathVariable long idEvent, @RequestParam String eventName, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
                           @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
                           @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        Event event = new Event(eventName);
        event.setIdEvent(idEvent);
        eventRepository.save(event);
        init(model, request, categorypage, eventpage, intervalpage);
        return "home";
    }

    /*
    START INTERVAL
     */

    @RequestMapping(value = "/addInterval", method = {RequestMethod.GET, RequestMethod.POST})
    public String newInterval(Model model, @RequestParam String intervalName, @RequestParam String startdate, @RequestParam String enddate, @RequestParam Long parentId, HttpServletRequest request, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
    @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {


        Interval newInterval = new Interval(intervalName, startdate, enddate);
        if (parentId!=-1) {
    	Interval padre = intervalRepository.findByIdInterval(parentId);
    	newInterval.setParent(padre);
    	}
        intervalRepository.save(newInterval);

        init(model, request, categorypage,eventpage,intervalpage);
        return "home";
    }

    @RequestMapping(value = "/deleteInterval{idInterval}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteInterval(Model model, HttpServletRequest request, @PathVariable long idInterval, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
    @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        intervalRepository.delete(idInterval);
        init(model, request, categorypage,eventpage,intervalpage);
        return "home";
    }

    @RequestMapping(value = "/setInterval{idInterval}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setInterval(Model model, HttpServletRequest request, @PathVariable long idInterval, @RequestParam String intervalName, @RequestParam String startdate, @RequestParam String enddate, @RequestParam Long parentId, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
    @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        //intervalRepository.findOne(idInterval).setName(intervalName);
        //intervalRepository.findOne(idInterval).setStart(startdate);
        //intervalRepository.findOne(idInterval).setEnd(enddate);
        Interval newInterval = new Interval(intervalName, startdate, enddate);
        newInterval.setIdInterval(idInterval);
        if (parentId!=-1) {
        	Interval padre = intervalRepository.findByIdInterval(parentId);
        	newInterval.setParent(padre);
        	}
        intervalRepository.save(newInterval);
        init(model, request, categorypage,eventpage,intervalpage);
        return "home";
    }

   /* @RequestMapping(value = "/openintervaltab{idInterval}", method = {RequestMethod.GET, RequestMethod.POST})
    public String openintervaltab(Model model, HttpServletRequest request, @PathVariable long idInterval, @RequestParam String intervalName, @RequestParam String startdate, @RequestParam String enddate, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
    @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {

    	System.out.println("\nLLEGA\n");
        intervaltabs.add(intervalRepository.findOne(idInterval));
    	System.out.println("\nLLEGA\n");

        init(model, request, categorypage,eventpage,intervalpage);
    	System.out.println("\nLLEGA\n");

        return "home";
    }*/
    @RequestMapping(value = "/openintervaltab{idInterval}", method = {RequestMethod.GET, RequestMethod.POST})
    public String openintervaltab(Model model, HttpServletRequest request, @PathVariable long idInterval, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
    @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
    	System.out.println("\nLLEGA\n");
    	hijos.clear();
        intervaltabs.add(intervalRepository.findOne(idInterval));
        hijos.addAll(intervalRepository.findByIdInterval(idInterval).getChildrens());
    	System.out.println("\nLLEGA\n");

        init(model, request, categorypage,eventpage,intervalpage);
    	System.out.println("\nLLEGA\n");

        return "home";
    }
    @RequestMapping(value = "/closeintervaltab{idInterval}", method = {RequestMethod.GET, RequestMethod.POST})
    public String closeintervaltab(Model model, HttpServletRequest request, @PathVariable long idInterval, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
    @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
    	System.out.println("\nLLEGA\n");
    	for (Iterator<Interval> iter = intervaltabs.listIterator(); iter.hasNext(); ) {
    	    Interval a = iter.next();
    	    if (a.getIdInterval()==idInterval) {
    	        iter.remove();
    	    }
    	}
    	hijos.clear();
       // intervaltabs.add(intervalRepository.findOne(idInterval));
    	System.out.println("\nLLEGA\n");

        init(model, request, categorypage,eventpage,intervalpage);
    	System.out.println("\nLLEGA\n");

        return "home";
    }
    /*
    END INTERVAL
     */


    @RequestMapping(value = "/addUser")
    public String addUser(Model model, HttpServletRequest request, @RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
	@RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        init(model, request, categorypage,eventpage,intervalpage);


        User NewUser = new User(name, email, password, "ROLE_STUDENT");
        userService.save(NewUser);

        return "home";
    }

    //Método que inicializa la bbdd de toda la página
    //Ir añadiendo campos
    public  void init(Model model, HttpServletRequest request,Integer categorypage,
    Integer eventpage, Integer intervalpage){
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
        Page<Interval> intervals = intervalRepository.findAll(new PageRequest(0, 10 * (intervalpage + 1)));

        model.addAttribute("categorias", categories);
        model.addAttribute("nextCategoryPage", categorypage + 1);
        model.addAttribute("intervalos", intervals);//Load again the intervals.
        model.addAttribute("nextIntervalPage", intervalpage + 1);
        model.addAttribute("eventos",events);//Load again.
        model.addAttribute("nextEventPage", eventpage + 1);
        model.addAttribute("categoryList", categoryRepository.findAllByOrderByIdCategoryAsc()); //Load the list of categories assigned to the events.
        
        Boolean showmorecategory=categorypage<categories.getTotalPages();
        model.addAttribute("showmorecategory", showmorecategory);

        Boolean showmoreevent=eventpage<events.getTotalPages();
        model.addAttribute("showmoreevent", showmoreevent);
        Boolean showmoreinterval=intervalpage<intervals.getTotalPages();
        model.addAttribute("showmoreinterval", showmoreinterval);
       // intervaltabs=intervalRepository.findAll();
        model.addAttribute("intervaltabs", intervaltabs);
        model.addAttribute("hijos", hijos);


    }

}
