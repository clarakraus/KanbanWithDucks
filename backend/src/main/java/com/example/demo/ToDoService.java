package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ToDoService {
    private final DBRepository toDoRepo;


    public List<ToDo> getTodos() {
        return toDoRepo.findAll();
    }

    public void addNew(ToDo todo) {
            toDoRepo.save(todo);
    }
    public void changeState(ToDo todo) {
        ToDo wantedToDo = toDoRepo.findById(todo.getId()).orElseThrow();
        switch (wantedToDo.getStatus()) {
            case OPEN -> wantedToDo.setStatus(Status.IN_PROGRESS);
            case IN_PROGRESS -> wantedToDo.setStatus(Status.DONE);

        }
        toDoRepo.save(wantedToDo);
    }

    public void setBackState(ToDo todo) {
        ToDo wantedToDo = toDoRepo.findById(todo.getId()).orElseThrow();
        switch (wantedToDo.getStatus()) {
            case IN_PROGRESS -> wantedToDo.setStatus(Status.OPEN);
            case DONE -> wantedToDo.setStatus(Status.IN_PROGRESS);
        }
        toDoRepo.save(wantedToDo);
    }

    public void deleteTask(String todoid) {
        toDoRepo.deleteById(todoid);
    }

    public ToDo getTaskTodo(String todoid) {
       return toDoRepo.findById(todoid).orElseThrow();
    }

    public void editTaskAndDesc(ToDo todo) {
        ToDo taskToEdit = toDoRepo.findById(todo.getId()).orElseThrow();
        taskToEdit.setTask(todo.getTask());
        taskToEdit.setDescription(todo.getDescription());
    }
}
