package com.ilovegogi.VoiceFinder.domain.campaign.repository;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}
