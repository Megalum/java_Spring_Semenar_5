package ru.gb.springhwl3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springhwl3.entity.Issue;
import ru.gb.springhwl3.services.IssueService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

    @Autowired
    private IssueService service;

    /*
        GET - получение записей
        POST - создание записей
        PUT - изменение записей
        DELETE - запрос на удаление ресурса
     */

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest issueRequest) {
        log.info("Поступил запрос на выдачу: readerId={}, bookId={}"
                , issueRequest.getReaderId(), issueRequest.getBookId());

        try {
            if (service.searchReader(issueRequest.getReaderId())){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createIssue(issueRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Issue> getIssue(@RequestBody BookRequest issueRequest) {
        log.info("Поступил запрос на выдачу записи: id={}"
                , issueRequest.getId());

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.getIssue(issueRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

}
