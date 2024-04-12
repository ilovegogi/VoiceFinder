package com.ilovegogi.VoiceFinder.domain.campaign.repository;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.CampaignJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignJobRepository extends JpaRepository<CampaignJob, Long> {

}
