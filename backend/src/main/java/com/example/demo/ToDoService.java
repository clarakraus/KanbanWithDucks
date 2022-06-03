package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ToDoService {
    private final ToDoRepo toDoRepo;

    public List<ToDo> getTodo() {
        return toDoRepo.list();
    }

    public void addNew(ToDo todo) {
            toDoRepo.add(todo);
    }
}
