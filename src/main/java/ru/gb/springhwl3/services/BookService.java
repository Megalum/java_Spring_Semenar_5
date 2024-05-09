package ru.gb.springhwl3.services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.springhwl3.dto.BookDto;
import ru.gb.springhwl3.entity.Book;
import ru.gb.springhwl3.repository.BookRepository;

import java.util.List;
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

    public Optional<BookDto> getBook(long id){
        return bookRepository.findById(id)
                .map(this::mapToDto);
    }

    public Optional<BookDto> createBook(String name){
        Book book = bookRepository.save(new Book(name));
        Optional<BookDto> bookDto = bookRepository.findById(book.getId())
                .map(this::mapToDto);
        return bookDto;
    }

    public Optional<BookDto> delBook(long id){
        Optional<BookDto> book = bookRepository.findById(id)
                .map(this::mapToDto);
        bookRepository.deleteById(id);
        return book;
    }

    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    private BookDto mapToDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        return bookDto;
    }
}
