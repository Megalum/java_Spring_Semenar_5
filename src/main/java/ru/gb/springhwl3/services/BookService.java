package ru.gb.springhwl3.services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.springhwl3.controllers.BookRequest;
import ru.gb.springhwl3.controllers.NameRequest;
import ru.gb.springhwl3.entity.Book;
import ru.gb.springhwl3.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void onCreateDatabaseBooks(){
        bookRepository.save(new Book("Война и мир"));
        bookRepository.save(new Book("Мастер и Маргарита"));
        bookRepository.save(new Book("Приключения Буратино"));
        bookRepository.save(new Book("Красная шапочка"));
        bookRepository.save(new Book("Семеро козлят"));
    }

    public Book getBook(BookRequest request){
        return bookRepository.getReferenceById(request.getId());
    }

    public Book createBook(NameRequest request){
        Book book = new Book(request.getName());
        bookRepository.save(new Book(request.getName()));
        return book;
    }

    public Book delBook(BookRequest request){
        bookRepository.deleteById(request.getId());
        return bookRepository.getReferenceById(request.getId());
    }

    public List<Book> booksList(){
        return bookRepository.findAll();
    }
}
