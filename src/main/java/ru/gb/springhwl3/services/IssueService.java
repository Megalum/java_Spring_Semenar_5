package ru.gb.springhwl3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.springhwl3.controllers.properties.BookRequest;
import ru.gb.springhwl3.controllers.properties.IssueRequest;
import ru.gb.springhwl3.controllers.properties.MyMetric;
import ru.gb.springhwl3.dto.IssueDto;
import ru.gb.springhwl3.entity.Issue;
import ru.gb.springhwl3.repository.IssueRepository;
import ru.gb.springhwl3.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;
    private final MyMetric bookCounter;

    @EventListener(ContextRefreshedEvent.class)
    public void onCreateDatabaseBooks(){
        Issue issue = issueRepository.save(new Issue(0, 1, LocalDateTime.now()));
        bookCounter.setBookCounter();
        issue.refundBook(LocalDateTime.now());
        issueRepository.save(issue);

        issue = issueRepository.save(new Issue(1, 2, LocalDateTime.now()));
        bookCounter.setBookCounter();
        issue.refundBook(LocalDateTime.now());
        issueRepository.save(issue);

        issueRepository.save(new Issue(1, 3, LocalDateTime.now()));
        bookCounter.setBookCounter();

        issueRepository.save(new Issue(0, 0, LocalDateTime.now()));
        bookCounter.setBookCounter();

        issueRepository.save(new Issue(2, 4, LocalDateTime.now()));
        bookCounter.setBookCounter();

    }

    public void readerRefundBook(long id){
        Issue issue = issueRepository.findById(id).orElse(null);
        assert issue != null;
        issue.refundBook(LocalDateTime.now());
        issueRepository.save(issue);
    }

    public Optional<IssueDto> createIssue(IssueRequest request){

        if (searchReader(request.getReaderId())) {
            bookCounter.setRefusalCounter();
            return Optional.empty();
        }

        if (readerRepository.findById(request.getReaderId()).isEmpty()){
            bookCounter.setRefusalCounter();
            return Optional.empty();
        }

        Issue issue = issueRepository.save(new Issue(request.getReaderId(), request.getBookId(), LocalDateTime.now()));
        bookCounter.setBookCounter();
        Optional<IssueDto> IssueDto = issueRepository.findById(issue.getId())
                .map(this::mapToDto);
        return IssueDto;
    }

    public boolean searchReader(long id){
        return issueRepository.findByIdReader(id) != null;
    }

    public List<IssueDto> getAll(){
        return issueRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public Optional<IssueDto> getIssue(long id){
        return issueRepository.findById(id)
                .map(this::mapToDto);
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

    private IssueDto mapToDto(Issue issue){
        IssueDto issueDto = new IssueDto();
        issueDto.setId(issue.getId());
        issueDto.setIdBook(issue.getIdBook());
        issueDto.setIdReader(issue.getIdReader());
        issueDto.setReceiving(issue.getReceiving());
        issueDto.setRefund(issue.getRefund());
        return issueDto;
    }
}
