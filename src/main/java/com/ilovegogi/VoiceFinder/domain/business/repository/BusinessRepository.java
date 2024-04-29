package com.ilovegogi.VoiceFinder.domain.business.repository;

import com.ilovegogi.VoiceFinder.domain.business.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

}
