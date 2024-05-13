package com.ilovegogi.VoiceFinder.domain.reviewer.repository;

import com.ilovegogi.VoiceFinder.domain.business.entity.Business;
import com.ilovegogi.VoiceFinder.domain.reviewer.entity.Reviewer;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {

    Optional<Reviewer> findByUser(User user);
}
