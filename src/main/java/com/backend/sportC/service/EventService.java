package com.backend.sportC.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.backend.sportC.communication.Request.EventRequest;
import com.backend.sportC.communication.Response.EventResponse;
import com.backend.sportC.entity.Event;
import com.backend.sportC.entity.Facility;
import com.backend.sportC.repository.EventRepository;
import com.backend.sportC.repository.FacilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final FacilityRepository facilityRepository;

    public void postEvent(EventRequest request) {
        Facility facility = facilityRepository.findById(request.getFacilityId()).orElseThrow();

        Event event = Event.builder()
                .title(request.getTitle())
                .location(request.getLocation())
                .dateTime(request.getDateTime())
                .facility(facility)
                .build();
        eventRepository.save(event);
    }

    public EventResponse updateEvent(Integer id, String title, String location, LocalDateTime dateTime,
            Integer facilityId) {
        Event event = eventRepository.findById(id).orElseThrow();

        if (title != null) {
            event.setTitle(title);
        }

        if (location != null) {
            event.setLocation(location);
        }

        if (dateTime != null) {
            event.setDateTime(dateTime);
        }

        if (facilityId != null) {
            event.setFacility(facilityRepository.findById(facilityId).orElseThrow());
        }

        eventRepository.save(event);

        return EventResponse.builder()
                .title(event.getTitle())
                .location(event.getLocation())
                .dateTime(event.getDateTime())
                .facilityName(event.getFacility().getName())
                .build();

    }

    public void deleteEvent(Integer id) {
        Event event = eventRepository.findById(id).orElseThrow();

        eventRepository.delete(event);
    }
}
