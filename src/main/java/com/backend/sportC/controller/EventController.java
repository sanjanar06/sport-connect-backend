package com.backend.sportC.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.sportC.communication.Request.EventRequest;
import com.backend.sportC.communication.Response.EventResponse;
import com.backend.sportC.service.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
@PreAuthorize("hasAuthority('ADMIN')")
public class EventController {

    private final EventService eventService;

    @PostMapping("/")
    public ResponseEntity<String> createEvent(@RequestBody EventRequest request) {
        eventService.postEvent(request);
        return ResponseEntity.ok("Event created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable Integer id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) LocalDateTime dateTime,
            @RequestParam(required = false) Integer facilityId) {
        return ResponseEntity.ok(eventService.updateEvent(id, title, location, dateTime, facilityId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully");
    }

}
