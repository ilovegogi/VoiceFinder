package com.ilovegogi.VoiceFinder.domain.apply.repository;

import com.ilovegogi.VoiceFinder.domain.apply.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {

}
