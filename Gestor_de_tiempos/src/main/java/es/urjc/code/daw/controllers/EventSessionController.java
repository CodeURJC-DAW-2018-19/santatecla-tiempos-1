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


    
}