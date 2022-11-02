package com.example.springdatajpademo.dao;


import com.example.springdatajpademo.entities.Quest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = {"com.example.springdatajpademo.bootstrap"})
public class SpringDataJpaTest {
    @Autowired
    private QuestRepository questRepository;

    @Rollback(value = false)
    @Order(1)
    @Test
    void testSave() {
        var countBefore = questRepository.count();
        assertThat(countBefore).isEqualTo(1);
        questRepository.save(Quest.builder()
                .name("Kafka")
                .build());
        var countAfter = questRepository.count();
        assertThat(countAfter).isEqualTo(2);
    }

    @Order(2)
    @Test
    void testCount() {
        var countBefore = questRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }

}
