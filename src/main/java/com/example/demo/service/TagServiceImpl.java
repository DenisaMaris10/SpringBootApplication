package com.example.demo.service;

import com.example.demo.model.Tag;
import com.example.demo.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;
    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }
}
