package ru.gb.springhwl3.repository;

import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.gb.springhwl3.entity.Book;
import ru.gb.springhwl3.entity.Issue;

import java.util.ArrayList;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

}
