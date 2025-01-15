package com.example.demo.service;

import com.example.demo.model.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    
    Event getEventById(Integer id);

    Event createEvent(Event event);

    void deleteEventById(Integer id);
}
