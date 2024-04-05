package ru.gb.springhwl3.repository;

import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springhwl3.entity.Issue;
import ru.gb.springhwl3.entity.Reader;

import java.util.ArrayList;
import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Long> {

}
