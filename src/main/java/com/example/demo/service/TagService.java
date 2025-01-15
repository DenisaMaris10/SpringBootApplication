package com.example.demo.service;

import com.example.demo.model.EventCategory;
import com.example.demo.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();

    Tag createTag(Tag tag);
}
