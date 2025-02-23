package com.ssilvadevevent.api.controller;

import com.ssilvadevevent.api.domain.event.Event;
import com.ssilvadevevent.api.domain.event.EventDetailsDTO;
import com.ssilvadevevent.api.domain.event.EventRequestDTO;
import com.ssilvadevevent.api.domain.event.EventResponseDTO;
import com.ssilvadevevent.api.domain.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody @Valid EventRequestDTO data){
            Event newEvent = this.eventService.createEvent(data);

            return ResponseEntity.ok(newEvent);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<EventResponseDTO> allEvents = this.eventService.getUpcomingEvents(page, size);

        return  ResponseEntity.ok(allEvents);
    }

    @GetMapping("filter")
    public ResponseEntity<List<EventResponseDTO>> filterEvents(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size,
                                                               @RequestParam(required = false) String title,
                                                               @RequestParam(required = false) String city,
                                                               @RequestParam(required = false) String uf,
                                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){

        List<EventResponseDTO> events = eventService.getFilteredEvents(page, size, title, city, uf, startDate, endDate);
        return ResponseEntity.ok(events);
    }

    @GetMapping("{id}")
    public ResponseEntity<EventDetailsDTO> getEventById(@PathVariable UUID id){
        EventDetailsDTO event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable UUID id){
        return eventService.deleteEvent(id);
    }
}
