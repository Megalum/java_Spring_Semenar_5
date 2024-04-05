package ru.gb.springhwl3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springhwl3.entity.Issue;
import ru.gb.springhwl3.entity.Reader;
import ru.gb.springhwl3.services.IssueService;
import ru.gb.springhwl3.services.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("reader")
@RequiredArgsConstructor
public class ReaderController {

    @Autowired
    private ReaderService service;
    @Autowired
    private IssueService iService;


    /*
        GET - получение записей
        POST - создание записей
        PUT - изменение записей
        DELETE - запрос на удаление ресурса
     */

    @GetMapping
    public ResponseEntity<Reader> getReader(@RequestBody BookRequest readerRequest){
        log.info("Поступил запрос на просмотр: reader={}"
                , readerRequest.getId());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.getReader(readerRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Reader> delReader(@RequestBody BookRequest readerRequest){
        log.info("Поступил запрос на удаление: reader={}"
                , readerRequest.getId());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.delReader(readerRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Reader> postReader(@RequestBody NameRequest nameRequest){
        log.info("Поступил запрос на добавление читателя: name={}"
                , nameRequest.getName());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createReader(nameRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/issue")
    public ResponseEntity<List<Issue>> getAllIssue(@PathVariable long id){
        BookRequest request = new BookRequest();
        request.bookRequest(id);
        log.info("Поступил запрос на предоставление данных читателя: " +
                 request.getId());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(iService.getReader(request));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
