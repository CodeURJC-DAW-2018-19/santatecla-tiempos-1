package es.urjc.code.daw.controllers;

import es.urjc.code.daw.event.Event;
import es.urjc.code.daw.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EventController
{

    @Autowired
    private EventService EventService;

    /*
    START EVENTS
     */

    @RequestMapping(value = "/addEvent", method = {RequestMethod.GET, RequestMethod.POST})
    public String newEvent(Model model, @RequestParam String eventName, HttpServletRequest request) {
        Event newEvent = new Event();
        EventService.save(newEvent);
        //init(model, request);
        return "home";
    }

    @RequestMapping(value = "/deleteEvent{idEvent}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteEvent(Model model, HttpServletRequest request, @PathVariable long idEvent) {
        EventService.delete(idEvent);
        //init(model, request);
        return "home";
    }

    @RequestMapping(value = "/setEvent{idEvent}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setEvent(Model model, HttpServletRequest request, @PathVariable long idEvent, @RequestParam String eventName) {
        Event event = new Event();
        event.setIdEvent(idEvent);
        EventService.save(event);
        //init(model, request);
        return "home";
    }

    /*
    END EVENTS
     */



}
