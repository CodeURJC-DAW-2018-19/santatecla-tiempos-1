package es.urjc.code.daw.controllers;

import es.urjc.code.daw.repositories.UserRepository;
import es.urjc.code.daw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

@Controller
public class MainController
{

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {

        User profesor = new User("Profesor A", "profesor", "profesor@urjc.es", "ROLE_TEACHER");
        User alumno = new User("Alumno", "12345", "alumno@alumnos.urjc.es", "ROLE_STUDENT");

        userRepository.save(profesor);
        userRepository.save(alumno);
    }

    @RequestMapping(path = "/")
    public String login(Model model, HttpSession session) {
        return "index";
    }

    @GetMapping(path = "/logout")
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(path = "/sign_in")
    public String signin(Model model) {
        return "sign_in";
    }

}
