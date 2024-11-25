package com.ssilvadevevent.api.domain.service;

import com.ssilvadevevent.api.domain.address.Address;
import com.ssilvadevevent.api.domain.coupon.Coupon;
import com.ssilvadevevent.api.domain.event.Event;
import com.ssilvadevevent.api.domain.event.EventDetailsDTO;
import com.ssilvadevevent.api.domain.event.EventRequestDTO;
import com.ssilvadevevent.api.domain.event.EventResponseDTO;
import com.ssilvadevevent.api.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CouponService couponService;

    public Event createEvent(EventRequestDTO data){
        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImageUrl(data.imageUrl());
        newEvent.setRemote(data.remote());

        repository.save(newEvent);

        if(!data.remote()){
            this.addressService.createAddress(data, newEvent);
        }

        return newEvent;
    }

    public List<EventResponseDTO> getUpcomingEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = this.repository.findUpcomingEvents(new Date(), pageable);

        return eventsPage.map(EventResponseDTO::new).stream().toList();
    }

    public List<EventResponseDTO> getFilteredEvents(int page, int size, String title, String city, String uf, Date startDate, Date endDate) {
        title = (title != null) ? title : "";
        city = (city != null) ? city : "";
        uf = (uf != null) ? uf : "";
        startDate = (startDate != null) ? startDate : new Date();
        endDate = (endDate != null) ? endDate : new Date(2030, Calendar.DECEMBER, 30);

        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = this.repository.findFilteredEvents(title, city, uf, startDate, endDate, pageable);

        return eventsPage.map(EventResponseDTO::new).stream().toList();
    }

    public EventDetailsDTO getEventById(UUID eventId){
        Event event = repository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        Optional<Address> address = addressService.findByEventId(eventId);

        List<Coupon> coupons = couponService.consultCoupons(eventId, new Date());

        List<EventDetailsDTO.CouponDTO> couponDTOs = coupons.stream()
                .map(coupon -> new EventDetailsDTO.CouponDTO(
                        coupon.getCode(),
                        coupon.getDiscount(),
                        coupon.getValid()))
                .collect(Collectors.toList());

        return new EventDetailsDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                address.isPresent() ? address.get().getCity() : "",
                address.isPresent() ? address.get().getUf() : "",
                event.getImageUrl(),
                event.getEventUrl(),
                couponDTOs);
    }

    public ResponseEntity<Event> deleteEvent(UUID id) {
        Event event = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        repository.delete(event);

        return ResponseEntity.noContent().build();
    }
}
