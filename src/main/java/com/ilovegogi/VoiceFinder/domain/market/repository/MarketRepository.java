package com.ilovegogi.VoiceFinder.domain.market.repository;

import com.ilovegogi.VoiceFinder.domain.business.entity.Business;
import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long>, QuerydslPredicateExecutor<Market> {


    Optional<Market> findByName(String name);

    Page<Market> findAll(Pageable pageable);

    Optional<Market> findByBusiness(Business business);

}
