package com.example.demo.controller;

import com.example.demo.dto.EventTagDTO;
import com.example.demo.model.Event;
import com.example.demo.model.EventCategory;
import com.example.demo.model.Tag;
import com.example.demo.service.EventCategoryService;
import com.example.demo.service.EventService;
import com.example.demo.service.EventServiceImpl;
import com.example.demo.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("events/")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventCategoryService eventCategoryService;
    private final TagService tagService;
    @GetMapping
    public String displayAllEvents(@RequestParam(required=false) Integer categoryId, Model model){


        if(categoryId == null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventService.getAllEvents());
        }
        else {
            EventCategory result = eventCategoryService.getEventById(categoryId);
            if (result == null){
                model.addAttribute("title", "Invalid Category Id " + categoryId);
            }
            else {
                model.addAttribute("title", "Events in category: " + result.getName());
                model.addAttribute("events", result.getEvents());
            }
        }
        return "events/index";
    }

    // lives at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event()); // this empty event will have inf about the event so it will be helpful for the create template ( asa am putut inlocui name si type attributes cu th:field...)
        model.addAttribute("categories", eventCategoryService.getAllEventCategories());
        return "events/create";
    }

    // also lives at /events/create - it is ok, because renderCreateEventForm is get type
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model){ // in errors vor fi scrise toate mesajele de eroare de la validare

        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            model.addAttribute("categories", eventCategoryService.getAllEventCategories());
            return "events/create";
        }

        eventService.createEvent(newEvent);
        return "redirect:"; // redirect response to the root path of the controller
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventService.getAllEvents());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){ // pentru a nu primi eroare cand nu avem selectat niciun eveniment

        if (eventIds != null) {
            for (int id : eventIds) {
                eventService.deleteEventById(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model){
        Event event = eventService.getEventById(eventId);

        if (event == null){
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        }
        else {
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags", event.getTags());
        }

        return "events/detail";
    }
    // responds to /events/add-tag?eventId=13
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model){
        Event event = eventService.getEventById(eventId);
        model.addAttribute("title", "Add tag to: " + event.getName());
        model.addAttribute("tags", tagService.getAllTags());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag); // we use model binding with this DTO
        return "events/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag, Errors errors, Model model){
        if (!errors.hasErrors()){
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if (!event.getTags().contains(tag)){
                event.addTag(tag);
                eventService.createEvent(event); // hibernate nu creeaza un nou object, it only updates the row
            }
            return "redirect:detail?eventId=" + event.getId();
        }

        return "redirect:add-tag.html";
    }
}
