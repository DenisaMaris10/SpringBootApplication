package com.example.demo.service;

import com.example.demo.model.EventCategory;
import com.example.demo.repository.EventCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventCategoryServiceImpl implements EventCategoryService{
    private final EventCategoryRepository eventCategoryRepository;

    @Override
    public List<EventCategory> getAllEventCategories() {
        return eventCategoryRepository.findAll();
    }

    public EventCategory getEventById(Integer id){
        return eventCategoryRepository.findById(id).orElse(null);
    }

    public EventCategory createEvent(EventCategory eventCategory){
        return eventCategoryRepository.save(eventCategory);
    }
}
