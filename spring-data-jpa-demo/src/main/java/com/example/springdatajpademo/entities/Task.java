package com.example.springdatajpademo.entities;


import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;
    @Range(min = 1, max = 5)
    private int priority;
    @FutureOrPresent
    private LocalDate startDate = LocalDate.now();
    private LocalDate endDate = LocalDate.now();
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    private Quest quest;


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", completed=" + completed +
                '}';
    }
}
