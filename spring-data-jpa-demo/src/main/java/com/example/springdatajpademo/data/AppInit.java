package com.example.springdatajpademo.data;

import com.example.springdatajpademo.service.QuestService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements ApplicationRunner {

    private final QuestService questService;

    public AppInit(QuestService questService) {
        this.questService = questService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        questService.init();
    }
}
