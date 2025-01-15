package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
    public Event getEventById(Integer id){
        return eventRepository.findById(id).orElse(null);
    }

    public Event createEvent(Event event){
        return eventRepository.save(event);
    }

    public void deleteEventById(Integer id){
        eventRepository.deleteById(id);
    }
}
