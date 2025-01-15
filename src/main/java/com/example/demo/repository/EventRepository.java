package com.example.demo.repository;

import com.example.demo.model.Event;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//Data Layer (Repository or DAO=data access object in the name)
public interface EventRepository extends JpaRepository<Event, Integer> { // Event = type of object which we will store and Integer = type of the primary key
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"eventCategory"})
    Optional<Event> findById(Integer id);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"eventCategory"})
    List<Event> findAll();




}
