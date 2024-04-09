package com.ilovegogi.VoiceFinder.domain.campaign.repository;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Age;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeRepository extends JpaRepository<Age, Long> {

}
