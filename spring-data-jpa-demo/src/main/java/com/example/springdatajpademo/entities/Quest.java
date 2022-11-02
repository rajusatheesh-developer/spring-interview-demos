package com.example.springdatajpademo.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Quest {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Quest should have name")
    private String name;

    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();

    public Quest addTasks(Task task) {
        this.tasks.add(task);
        task.setQuest(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return id.equals(quest.id) && name.equals(quest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


}
