package com.example.demo.dto;
import com.example.demo.model.Event;
import com.example.demo.model.Tag;
import jakarta.validation.constraints.NotNull;

// This class allows us to pass around a tag and an event together (useful when doing validation)
public class EventTagDTO {

    @NotNull
    private Event event;

    @NotNull
    private Tag tag;

    public EventTagDTO(){}

    public Event getEvent() {
        return event;
    }

    public Tag getTag() {
        return tag;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
