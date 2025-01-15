package com.example.demo.service;

import com.example.demo.model.EventCategory;
import com.example.demo.repository.EventCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EventCategoryService {
    List<EventCategory> getAllEventCategories();
    EventCategory getEventById(Integer id);

    EventCategory createEvent(EventCategory eventCategory);

}
