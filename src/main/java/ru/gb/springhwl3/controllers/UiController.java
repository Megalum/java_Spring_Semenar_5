package ru.gb.springhwl3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springhwl3.services.BookService;
import ru.gb.springhwl3.services.IssueService;
import ru.gb.springhwl3.services.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;


//@Slf4j
@Controller
//@RequestMapping("ui")
//@RequiredArgsConstructor
public class UiController {

    /*@Autowired
    private IssueService issueService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private BookService bookService;*/

    //@GetMapping("books")
    @GetMapping("ui/home")
    public String home(/*Model model*/){
        //model.addAttribute("list", bookService.booksList());
        return "home";
    }

}
