package es.urjc.code.daw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PeriodController
{

    @RequestMapping(path = "/period")
    public String signin(Model model) {
        return "periods/period";
    }


}
