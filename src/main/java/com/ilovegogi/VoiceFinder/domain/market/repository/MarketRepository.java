package com.ilovegogi.VoiceFinder.domain.market.repository;

import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {

    Optional<Market> findByOwnerId(Long ownerId);

    Optional<Market> findByName(String name);

    Optional<Market> findByNameAndOwnerId(String name, Long ownerId);

}
