package com.backend.sportC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.sportC.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
