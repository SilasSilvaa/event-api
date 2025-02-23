package com.ssilvadevevent.api.domain.service;

import com.ssilvadevevent.api.domain.address.Address;
import com.ssilvadevevent.api.domain.event.Event;
import com.ssilvadevevent.api.domain.event.EventRequestDTO;
import com.ssilvadevevent.api.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public void createAddress(EventRequestDTO data, Event event){
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.state());
        address.setEvent(event);

        repository.save(address);

        repository.save(address);
    }

    public Optional<Address> findByEventId(UUID eventId) {
        return repository.findByEventId(eventId);
    }
}
