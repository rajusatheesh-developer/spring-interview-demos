package com.example.beanscopesdemo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestScopeClass
{
    private String name;
    private LocalDateTime dateTime;

    public RequestScopeClass(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }
}
