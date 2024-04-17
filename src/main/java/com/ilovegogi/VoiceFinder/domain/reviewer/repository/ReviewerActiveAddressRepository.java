package com.ilovegogi.VoiceFinder.domain.reviewer.repository;

import com.ilovegogi.VoiceFinder.domain.reviewer.entity.ReviewerActiveAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerActiveAddressRepository extends JpaRepository<ReviewerActiveAddress, Long> {

}
