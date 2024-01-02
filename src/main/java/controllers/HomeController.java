package controllers;

import models.Todoitem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import services.TodoitemService;

@Controller
public class HomeController {
    @Autowired
    private TodoitemService todoitemService;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoitems",todoitemService.getAll());
        return modelAndView;
    }



}
