package com.example.springdatajpademo.dao;

import com.example.springdatajpademo.entities.Quest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {
    @EntityGraph(attributePaths = "tasks")
    Optional<Quest> findById(Long aLong);
}
