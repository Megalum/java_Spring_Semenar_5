package ru.gb.springhwl3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springhwl3.entity.Issue;
import ru.gb.springhwl3.entity.Table;
import ru.gb.springhwl3.services.BookService;
import ru.gb.springhwl3.services.IssueService;
import ru.gb.springhwl3.services.ReaderService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private BookService booksService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private IssueService issueService;

    @GetMapping("ui/books")
    public String booksList(Model model){
        model.addAttribute("list", booksService.getAll());
        return "list";
    }

    @GetMapping("ui/reader")
    public String readersList(Model model){
        model.addAttribute("list", readerService.readersList());
        return "list";
    }

    @GetMapping("ui/issue")
    public String issuesList(Model model){
        List<Table> tables = new ArrayList<>();
        issueService.refund(2);
        issueService.refund(4);
        for (Issue out: issueService.issuesList()){
            tables.add(new Table(readerService.readersList().get((int) out.getIdReader()).getName(),
                    booksService.getAll().get((int) out.getIdBook()).getName(),
                    out.getReceiving(), out.getRefund()));
        }
        model.addAttribute("table", tables);
        return "table";
    }

}
