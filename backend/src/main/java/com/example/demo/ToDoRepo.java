package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ToDoRepo {
    private List<ToDo> toDoList = new ArrayList<>();

    public List<ToDo> list() {
        return toDoList;
    }


    public void add(ToDo todo) {
        toDoList.add(todo);
    }

    public Optional<ToDo> findToDoById(String id){
        return toDoList.stream()
                .filter(toDo -> toDo.getId().equals(id))
                .findFirst();
    }
}
