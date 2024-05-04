package ru.gb.springhwl3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springhwl3.dto.BookDto;
import ru.gb.springhwl3.entity.Book;
import ru.gb.springhwl3.services.BookService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookService service;

    @GetMapping
    public List<BookDto> getBookAll(){
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable long id){
        log.info("Поступил запрос на просмотр: book={}", id);
        return service.getBook(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BookDto> delBook(@PathVariable long id){
        log.info("Поступил запрос на удаление: book={}", id);
        return service.delBook(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("{title}")
    public ResponseEntity<BookDto> postBook(@PathVariable String title){
        log.info("Поступил запрос на добавление книги: name={}", title);
        return service.createBook(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
