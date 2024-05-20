package com.ilovegogi.VoiceFinder.domain.campaign.repository;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>, QuerydslPredicateExecutor<Campaign> {

    @Query("SELECT c FROM Campaign c JOIN FETCH c.campaignAges ca JOIN FETCH ca.age")
    List<Campaign> findAllWithAges();

    Page<Campaign> findAll(Pageable pageable);

    Optional<Campaign> findById(Long id);

}
