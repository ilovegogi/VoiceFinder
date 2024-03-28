package com.ilovegogi.VoiceFinder.domain.market.repository;

import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {


}
