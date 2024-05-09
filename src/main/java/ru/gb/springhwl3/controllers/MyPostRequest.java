/*package ru.gb.springhwl3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gb.springhwl3.controllers.properties.IssueRequest;
import ru.gb.springhwl3.services.IssueService;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class MyPostRequest {

    private final IssueService service;
    private long count = 2;
    private IssueRequest request;

    @Scheduled(fixedDelay = 2000, initialDelay = 0)
    public void myPostRequest() {
        if (Math.random() < 0.5) {
            service.readerRefundBook(count++);
        } else {
            long idBook = (long) (Math.random()*5);
            long idReader = (long) (Math.random()*5);
            request.setBookId(idBook);
            request.setReaderId(idReader);
            service.createIssue(request);
        }
    }
}*/
