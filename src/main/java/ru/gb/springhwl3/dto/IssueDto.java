package ru.gb.springhwl3.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssueDto {
    private long id;
    private long idReader;
    private long idBook;
    private LocalDateTime receiving;
    private LocalDateTime refund;
}
