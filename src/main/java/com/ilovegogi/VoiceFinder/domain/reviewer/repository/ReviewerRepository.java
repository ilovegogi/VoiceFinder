package com.ilovegogi.VoiceFinder.domain.reviewer.repository;

import com.ilovegogi.VoiceFinder.domain.reviewer.entity.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {

}
