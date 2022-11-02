package com.example.springdatajpademo.bootstrap;

import com.example.springdatajpademo.dao.QuestRepository;
import com.example.springdatajpademo.entities.Quest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final QuestRepository questRepository;


    public DataInitializer(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Quest quest = new Quest();
        quest.setName("EPAM Interviews");
        questRepository.save(quest);
    }
}
