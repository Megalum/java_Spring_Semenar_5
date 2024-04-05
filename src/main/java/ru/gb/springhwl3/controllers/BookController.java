package ru.gb.springhwl3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springhwl3.entity.Book;
import ru.gb.springhwl3.services.BookService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private BookService service;

    /*
        GET - получение записей
        POST - создание записей
        PUT - изменение записей
        DELETE - запрос на удаление ресурса
     */

    @GetMapping
    public ResponseEntity<Book> getBook(@RequestBody BookRequest bookRequest){
        log.info("Поступил запрос на просмотр: book={}"
                , bookRequest.getId());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.getBook(bookRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Book> delBook(@RequestBody BookRequest bookRequest){
        log.info("Поступил запрос на удаление: book={}"
                , bookRequest.getId());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.delBook(bookRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Book> postBook(@RequestBody NameRequest nameRequest){
        log.info("Поступил запрос на добавление книги: name={}"
                , nameRequest.getName());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createBook(nameRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

}
