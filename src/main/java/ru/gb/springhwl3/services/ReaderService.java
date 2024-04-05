package ru.gb.springhwl3.services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.springhwl3.controllers.BookRequest;
import ru.gb.springhwl3.controllers.NameRequest;
import ru.gb.springhwl3.entity.Book;
import ru.gb.springhwl3.entity.Reader;
import ru.gb.springhwl3.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReaderService {
    private final ReaderRepository readerRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void onCreateDatabaseBooks(){
        readerRepository.save(new Reader("Костя"));
        readerRepository.save(new Reader("Василий"));
        readerRepository.save(new Reader("Семен"));
        readerRepository.save(new Reader("Сергей"));
        readerRepository.save(new Reader("Николай"));
    }

    public Reader getReader(BookRequest request){
        return readerRepository.getReferenceById(request.getId());
    }

    public Reader createReader(NameRequest request){
        Reader reader = new Reader(request.getName());
        readerRepository.save(reader);
        return reader;
    }

    public Reader delReader(BookRequest request){
        readerRepository.deleteById(request.getId());
        return readerRepository.getReferenceById(request.getId());
    }

    public List<Reader> readersList(){
        return readerRepository.findAll();
    }

}
