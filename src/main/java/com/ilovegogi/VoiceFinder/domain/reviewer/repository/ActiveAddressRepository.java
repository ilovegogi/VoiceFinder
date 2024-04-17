package com.ilovegogi.VoiceFinder.domain.reviewer.repository;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Type;
import com.ilovegogi.VoiceFinder.domain.reviewer.entity.ActiveAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActiveAddressRepository extends JpaRepository<ActiveAddress, Long> {

    Optional<ActiveAddress> findByActiveAddress(String activeAddress);

}
