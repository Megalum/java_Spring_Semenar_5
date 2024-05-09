package ru.gb.springhwl3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springhwl3.controllers.properties.BookRequest;
import ru.gb.springhwl3.controllers.properties.IssueRequest;
import ru.gb.springhwl3.controllers.properties.MyMetric;
import ru.gb.springhwl3.dto.IssueDto;
import ru.gb.springhwl3.entity.Issue;
import ru.gb.springhwl3.services.IssueService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

    @Autowired
    private IssueService service;

    @PostMapping
    public ResponseEntity<IssueDto> issueBook(@RequestBody IssueRequest issueRequest) {
        log.info("Поступил запрос на выдачу: readerId={}, bookId={}"
                , issueRequest.getReaderId(), issueRequest.getBookId());
        return service.createIssue(issueRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<IssueDto> findAllIssues() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<IssueDto> getIdIssue(@PathVariable long id) {
        log.info("Поступил запрос на выдачу записи: id={}", id);

        return service.getIssue(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
