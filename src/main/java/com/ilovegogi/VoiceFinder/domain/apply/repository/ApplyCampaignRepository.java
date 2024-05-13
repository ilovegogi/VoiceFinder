package com.ilovegogi.VoiceFinder.domain.apply.repository;

import com.ilovegogi.VoiceFinder.domain.apply.entity.ApplyCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyCampaignRepository extends JpaRepository<ApplyCampaign, Long> {

}
