package es.urjc.code.daw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.urjc.code.daw.category.Category;
import es.urjc.code.daw.category.CategoryService;

@Controller
public class CategorySessionController {
    
    //Services
    @Autowired
    private CategoryService categoryService;

    private SesionController session;
    /*
    START CATEGORY
     */

    @RequestMapping(value = "/addCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public String newCategory(Model model, @RequestParam String categoryName, HttpServletRequest request, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage) {
        Category newCategory = new Category(categoryName);
        categoryService.save(newCategory);
        session.init(model, request, categorypage,eventpage);
        return "home";
    }

    @RequestMapping(value = "/deleteCategory{idCategory}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteCategory(Model model, HttpServletRequest request, @PathVariable long idCategory, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage) {
        categoryService.delete(idCategory);
        session.init(model, request, categorypage,eventpage);
        return "home";
    }

    @RequestMapping(value = "/setCategory{idCategory}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setCategory(Model model, HttpServletRequest request, @PathVariable long idCategory, @RequestParam String categoryName, @RequestParam(name = "categorypage", required = false, defaultValue = "0") Integer categorypage,
    @RequestParam(name = "eventpage", required = false, defaultValue = "0") Integer eventpage) {
        Category category = new Category(categoryName);
        category.setIdCategory(idCategory);
        categoryService.save(category);
        session.init(model, request, categorypage,eventpage);
        return "home";
    }
}
