package com.ssilvadevevent.api.repositories;

import com.ssilvadevevent.api.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
