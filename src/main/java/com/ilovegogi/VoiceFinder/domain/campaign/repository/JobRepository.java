package com.ilovegogi.VoiceFinder.domain.campaign.repository;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
