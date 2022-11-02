package com.example.springdatajpademo.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestTest {

    @Autowired
    Validator validator;


    @Test
    void validQuest() {
        var quest = new Quest();
        quest.setName("one");
        var validations = validator.validate(quest);
        assertThat(validations).hasSize(0);
    }

    @Test
    void inValidQuest() {
        var quest = new Quest();
        quest.setName(" ");
        var validations = validator.validate(quest);
        var validation = validations.iterator().next();
        assertThat(validation.getMessage()).contains("Quest should have name");
    }
}