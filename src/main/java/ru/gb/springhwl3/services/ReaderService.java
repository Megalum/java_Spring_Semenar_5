package ru.gb.springhwl3.services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.springhwl3.controllers.BookRequest;
import ru.gb.springhwl3.controllers.NameRequest;
import ru.gb.springhwl3.entity.Reader;
import ru.gb.springhwl3.repository.ReaderRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReaderService {
    private final ReaderRepository readerRepository;

    @EventListener(ContextRefreshedEvent.class)
    private void onCreateDatabaseReader(){

        Reader reader1 = new Reader("Костя");
        reader1.setLogin("root");
        reader1.setPassword("admin");
        reader1.setRole("admin");

        Reader reader2 = new Reader("Василий");
        reader2.setLogin("user");
        reader2.setPassword("user");
        reader2.setRole("user");

        Reader reader3 = new Reader("Семен");
        reader3.setLogin("sema");
        reader3.setPassword("sema");
        reader3.setRole("user");

        Reader reader4 = new Reader("Сергей");
        reader4.setLogin("ser");
        reader4.setPassword("ser");
        reader4.setRole("user");

        Reader reader5 = new Reader("Николай");
        reader5.setLogin("nik");
        reader5.setPassword("nik");
        reader5.setRole("user");

        createReader(reader1);
        createReader(reader2);
        createReader(reader3);
        createReader(reader4);
        createReader(reader5);
    }


    public Reader getReader(BookRequest request){
        return readerRepository.getReferenceById(request.getId());
    }

    public void createReader(Reader reader){
        readerRepository.save(reader);
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
