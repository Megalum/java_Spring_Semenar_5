package ru.gb.springhwl3.controllers.properties;

import lombok.Data;

@Data
public class BookRequest {
    private long id;

    public void bookRequest(long id){
        this.id = id;
    }
}
