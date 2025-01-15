package com.example.demo.service;

import com.example.demo.model.EventCategory;
import com.example.demo.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();
    Tag getTagById(Integer id);

    Tag createTag(Tag tag);
}
