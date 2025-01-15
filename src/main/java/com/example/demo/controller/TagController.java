package com.example.demo.controller;

import com.example.demo.model.Tag;
import com.example.demo.repository.TagRepository;
import com.example.demo.service.TagService;
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
@RequestMapping("tags/")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public String displayTags(Model model){
        model.addAttribute("title", "All Tags");
        model.addAttribute("tags", tagService.getAllTags());
        return "tags/index";
    }

    @GetMapping("create")
    public String displayCreateTagForm(Model model){
        model.addAttribute("title", "Create Tag");
        model.addAttribute(new Tag()); // this empty event will have inf about the event so it will be helpful for the create template ( asa am putut inlocui name si type attributes cu th:field...)
        model.addAttribute("tags", tagService.getAllTags());
        return "tags/create";
    }

    // also lives at /events/create - it is ok, because renderCreateEventForm is get type
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Tag newTag, Errors errors, Model model){ // in errors vor fi scrise toate mesajele de eroare de la validare

        if(errors.hasErrors()){
            model.addAttribute("title", "Create Tag");
            model.addAttribute("tags", tagService.getAllTags());
            return "tags/create";
        }

        tagService.createTag(newTag);
        return "redirect:"; // redirect response to the root path of the controller
    }


}
