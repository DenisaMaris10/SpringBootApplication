package com.example.demo.controller;

import com.example.demo.model.EventCategory;
import com.example.demo.service.EventCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("categories/")
public class EventCategoryController {

    @Autowired
    private EventCategoryService eventCategoryService;

    @GetMapping
    public String displayAllCategories(Model model){
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", eventCategoryService.getAllEventCategories());
        return "categories/index";
    }

    // lives at /categories/create
    @GetMapping("create")
    public String displayCreateCategoryForm(Model model){
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory()); // this empty event will have inf about the event so it will be helpful for the create template ( asa am putut inlocui name si type attributes cu th:field...)
        return "categories/create";
    }

    // also lives at /events/create - it is ok, because renderCreateEventForm is get type
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid EventCategory newEventCategory, Errors errors, Model model){ // in errors vor fi scrise toate mesajele de eroare de la validare

        if(errors.hasErrors()){
            model.addAttribute("title", "Create Category");
            return "categories/create";
        }

        eventCategoryService.createEvent(newEventCategory);
        return "redirect:"; // redirect response to the root path of the controller
    }

}
