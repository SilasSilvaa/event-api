package com.ssilvadevevent.api.domain.service;

import com.ssilvadevevent.api.domain.coupon.Coupon;
import com.ssilvadevevent.api.domain.coupon.CouponRequestDTO;
import com.ssilvadevevent.api.domain.event.Event;
import com.ssilvadevevent.api.repositories.CouponRepository;
import com.ssilvadevevent.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CouponService {

    @Autowired
    private CouponRepository repository;

    @Autowired
    private EventRepository eventRepository;

    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO data) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(data.code());
        coupon.setDiscount(data.discount());
        coupon.setValid(new Date(data.valid()));
        coupon.setEvent(event);

        return repository.save(coupon);
    }

    public List<Coupon> consultCoupons(UUID eventId, Date date) {
        return repository.findByEventIdAndValidAfter(eventId, date);
    }
}
