package es.urjc.code.daw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.urjc.code.daw.interval.Interval;
import es.urjc.code.daw.interval.IntervalRepository;

@Controller
public class IntervalSessionController {
	//Repositories
    
    @Autowired
    private IntervalRepository intervalRepository;
    
    private SesionController session;
    /*
    START INTERVAL
     */

    @RequestMapping(value = "/addInterval", method = {RequestMethod.GET, RequestMethod.POST})
    public String newInterval(Model model, @RequestParam String intervalName, @RequestParam String startdate, @RequestParam String enddate, HttpServletRequest request, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage) {


        Interval newInterval = new Interval(intervalName, startdate, enddate);
        intervalRepository.save(newInterval);

        session.init(model, request, categorypage,eventpage);
        return "home";
    }

    @RequestMapping(value = "/deleteInterval{idInterval}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteInterval(Model model, HttpServletRequest request, @PathVariable long idInterval, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage) {
        intervalRepository.delete(idInterval);
        session.init(model, request, categorypage,eventpage);
        return "home";
    }

    @RequestMapping(value = "/setInterval{idInterval}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setInterval(Model model, HttpServletRequest request, @PathVariable long idInterval, @RequestParam String intervalName, @RequestParam String startdate, @RequestParam String enddate, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage) {
        //intervalRepository.findOne(idInterval).setName(intervalName);
        //intervalRepository.findOne(idInterval).setStart(startdate);
        //intervalRepository.findOne(idInterval).setEnd(enddate);
        Interval newInterval = new Interval(intervalName, startdate, enddate);
        newInterval.setIdInterval(idInterval);
        intervalRepository.save(newInterval);
        session.init(model, request, categorypage,eventpage);
        return "home";
    }

    /*
    END INTERVAL
     */


}