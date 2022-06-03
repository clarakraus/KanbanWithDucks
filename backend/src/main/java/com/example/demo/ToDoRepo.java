package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public final class ToDoRepo {
    private List<ToDo> toDoList = new ArrayList<>();

    public List<ToDo> list() {
        return List.of(new ToDo("Staubsaugen", "Schlafzimmer und Küche saugen", Status.OPEN),
                new ToDo("FreitagsAufgabe", "das Übliche", Status.IN_PROGRESS),
                new ToDo("Kochen", "auch das Übliche", Status.DONE));
    }


    public void add(ToDo todo) {
        toDoList.add(todo);
    }
}
