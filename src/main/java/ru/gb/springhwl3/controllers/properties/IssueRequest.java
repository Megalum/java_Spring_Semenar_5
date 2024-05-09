package ru.gb.springhwl3.controllers.properties;

import lombok.Data;

@Data
public class IssueRequest {
    private long readerId;
    private long bookId;
}
