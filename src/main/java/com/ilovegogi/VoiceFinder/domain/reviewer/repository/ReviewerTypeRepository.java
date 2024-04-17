package com.ilovegogi.VoiceFinder.domain.reviewer.repository;

import com.ilovegogi.VoiceFinder.domain.reviewer.entity.ReviewerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerTypeRepository extends JpaRepository<ReviewerType, Long> {

}
