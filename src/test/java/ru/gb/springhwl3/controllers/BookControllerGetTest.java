package ru.gb.springhwl3.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springhwl3.dto.BookDto;
import ru.gb.springhwl3.entity.Book;
import ru.gb.springhwl3.repository.BookRepository;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class BookControllerGetTest {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    BookRepository repository;

    @Test
    void getBookAll() {
        List<Book> books = repository.findAll();

        List<BookDto> responseBody = webTestClient.get()
                .uri("book")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<BookDto>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(books.size(), responseBody.size());
        for (BookDto bookDto : responseBody) {
            boolean found = books.stream()
                    .filter(it -> Objects.equals(bookDto.getId(), it.getId()))
                    .anyMatch(it -> Objects.equals(bookDto.getName(), it.getName()));
            Assertions.assertTrue(found);
        }


    }

    @Test
    void  getById() {
        Book savedBook = repository.save(new Book("add"));

        BookDto responseBody = webTestClient.get()
                .uri("book/" + savedBook.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
        assertEquals(responseBody.getId(), savedBook.getId());
        assertEquals(responseBody.getName(), savedBook.getName());
    }
}