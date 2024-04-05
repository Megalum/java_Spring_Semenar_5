package ru.gb.springhwl3.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Table {
    private static long genId;

    private final long id;
    private final String reader;
    private final String book;
    private final LocalDateTime receiving;
    private LocalDateTime refund;

    public Table(String reader, String book, LocalDateTime receiving, LocalDateTime refund){
        id = genId++;
        this.book = book;
        this.reader = reader;
        this.receiving = receiving;
        this.refund = refund;
    }

}
