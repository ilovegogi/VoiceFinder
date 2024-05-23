package com.ilovegogi.VoiceFinder.domain.apply.repository;

import com.ilovegogi.VoiceFinder.domain.apply.entity.Apply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long>, QuerydslPredicateExecutor<Apply> {

    Page<Apply> findAll(Pageable pageable);

}
