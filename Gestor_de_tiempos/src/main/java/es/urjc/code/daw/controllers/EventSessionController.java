package es.urjc.code.daw.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.urjc.code.daw.EncoderDecoderBase64;
import es.urjc.code.daw.category.Category;
import es.urjc.code.daw.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.urjc.code.daw.event.Event;
import es.urjc.code.daw.event.EventService;

@Controller
public class EventSessionController {
    //Repositories

    @Autowired
    private EventService eventService;
    @Autowired
    CategoryRepository categoryRepository;
    private SesionController session;


    @RequestMapping(value = "/addEvent", method = {RequestMethod.GET})
    public String newEvent(Model model, Event event, HttpServletRequest request, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
                           @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
                           @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        session.init(model, request, categorypage, eventpage, intervalpage);
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

        eventService.save(event);
        session.init(model, request, categorypage, eventpage, intervalpage);
        return "redirect:/home";
    }


    @RequestMapping(value = "/deleteEvent{idEvent}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteEvent(Model model, HttpServletRequest request, @PathVariable long idEvent, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
                              @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
                              @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        eventService.delete(idEvent);
        session.init(model, request, categorypage, eventpage, intervalpage);
        return "home";
    }

    @RequestMapping(value = "/setEvent{idEvent}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setEvent(Model model, HttpServletRequest request, @PathVariable long idEvent, @RequestParam String eventName, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
                           @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage,
                           @RequestParam(name = "intervalpage", required = false, defaultValue = "0") Integer intervalpage) {
        Event event = new Event(eventName);
        event.setIdEvent(idEvent);
        eventService.save(event);
        session.init(model, request, categorypage, eventpage, intervalpage);
        return "home";
    }

}