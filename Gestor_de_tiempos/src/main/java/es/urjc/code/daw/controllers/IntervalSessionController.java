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
    

}