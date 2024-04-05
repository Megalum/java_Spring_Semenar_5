package ru.gb.springhwl3.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table
@NoArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "idReader")
    private long idReader;
    @Column(name = "idBook")
    private long idBook;
    @Column(name = "receiving")
    private LocalDateTime receiving;
    @Column(name = "refund")
    private LocalDateTime refund;

    public Issue(long idReader, long idBook, LocalDateTime receiving){
        this.idBook = idBook;
        this.idReader = idReader;
        this.receiving = receiving;
    }

    public void refundBook(LocalDateTime refund){
        this.refund = refund;
    }
}
