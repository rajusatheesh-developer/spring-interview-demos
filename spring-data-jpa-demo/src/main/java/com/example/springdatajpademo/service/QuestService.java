package com.example.springdatajpademo.service;

import com.example.springdatajpademo.dao.QuestRepository;
import com.example.springdatajpademo.entities.Quest;
import com.example.springdatajpademo.entities.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class QuestService {

    private final QuestRepository questRepository;
    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public void init()
    {
        if (questRepository.count() == 0) {

            Quest quest = new Quest();
            quest.setName("EPAM Interviews");

            quest.addTasks(Task.builder()
                    .completed(true)
                    .priority(1)
                    .name("Learn Java")
                    .build())
                    .addTasks(Task.builder()
                            .completed(true)
                            .priority(1)
                            .name("Learn JPA")
                            .build())
                    .addTasks(Task.builder()
                            .completed(true)
                            .priority(1)
                            .name("Learn EDA")
                            .build())
                    .addTasks(Task.builder()
                            .completed(true)
                            .priority(1)
                            .name("Learn SQL")
                            .build());

            questRepository.save(quest);
            System.out.println(quest);
            System.out.println(quest.getTasks());
        }

    }
}
