package ru.gb.springhwl3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.springhwl3.controllers.BookRequest;
import ru.gb.springhwl3.controllers.IssueRequest;
import ru.gb.springhwl3.entity.Book;
import ru.gb.springhwl3.entity.Issue;
import ru.gb.springhwl3.entity.Table;
import ru.gb.springhwl3.repository.BookRepository;
import ru.gb.springhwl3.repository.IssueRepository;
import ru.gb.springhwl3.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
    private final IssueRepository issueRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void onCreateDatabaseBooks(){
        issueRepository.save(new Issue(0, 1, LocalDateTime.now()));
        issueRepository.save(new Issue(1, 2, LocalDateTime.now()));
        issueRepository.save(new Issue(1, 3, LocalDateTime.now()));
        issueRepository.save(new Issue(2, 0, LocalDateTime.now()));
        issueRepository.save(new Issue(2, 4, LocalDateTime.now()));
    }

    public Issue createIssue(IssueRequest request){
        Issue issue = new Issue(request.getReaderId(), request.getBookId(), LocalDateTime.now());
        issueRepository.save(issue);
        return issue;
    }

    public boolean searchReader(long id){
        return issueRepository.findByIdReader(id) != null;
    }

    public Issue getIssue(BookRequest request){
        return issueRepository.getReferenceById(request.getId());
    }

    public List<Issue> getReader(BookRequest request){
        return issueRepository.findByIdReader(request.getId());
    }

    public void refund(long id){
        issueRepository.getReferenceById(id).refundBook(LocalDateTime.now());
    }

    public List<Issue> issuesList(){
        return issueRepository.findAll();
    }
}
