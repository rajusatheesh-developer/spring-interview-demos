package com.example.springdatajpademo.dao;

import com.example.springdatajpademo.entities.Quest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class QuestRepositoryTest {

    @Autowired
    private QuestRepository questRepository;

    @Test
    void questShouldHaveTasks() {
        Optional<Quest> questOptional = questRepository.findById(1L);
        assertThat(questOptional.isPresent()).isTrue();
        Quest quest = questOptional.get();
        assertThat(quest.getTasks()).hasSizeGreaterThan(0);
    }

    @Test
    void singleQuery() {
        Optional<Quest> questOptional = questRepository.findById(1L);
        assertThat(questOptional.isPresent()).isTrue();
        Optional<Quest> questOptional1 = questRepository.findById(1L);
        assertThat(questOptional1.isPresent()).isTrue();
    }


    @Test
    void testDBLock() throws InterruptedException {
        var quest = questRepository.findById(1L);
        assertThat(quest.isPresent()).isTrue();
        Thread.sleep(15000);
        Quest q = quest.get();
        q.setName("HIBERNETE");
        questRepository.save(q);
        var questAfterOptional = questRepository.findById(1L);
        assertThat(questAfterOptional.isPresent()).isTrue();
        assertThat(questAfterOptional.get().getName()).isEqualTo("HIBERNETE");
    }
}