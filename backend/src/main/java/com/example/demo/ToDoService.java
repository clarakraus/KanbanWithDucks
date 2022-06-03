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
    public void changeState(ToDo todo) {
        ToDo wantedToDo = toDoRepo.findToDoById(todo.getId()).orElseThrow();
        switch (wantedToDo.getStatus()) {
            case OPEN -> wantedToDo.setStatus(Status.IN_PROGRESS);
            case IN_PROGRESS -> wantedToDo.setStatus(Status.DONE);
        }
    }

    public void setBackState(ToDo todo) {
        ToDo wantedToDo = toDoRepo.findToDoById(todo.getId()).orElseThrow();
        switch (wantedToDo.getStatus()) {
            case IN_PROGRESS -> wantedToDo.setStatus(Status.OPEN);
            case DONE -> wantedToDo.setStatus(Status.IN_PROGRESS);
        }
    }

    public void deleteTask(String todoid) {
        toDoRepo.list().removeIf(toDo -> toDo.getId().equals(todoid));
    }

    public ToDo getTaskTodo(String todoid) {
       return toDoRepo.findToDoById(todoid).orElseThrow();
    }

    public void editTaskAndDesc(ToDo todo) {
        ToDo taskToEdit = toDoRepo.findToDoById(todo.getId()).orElseThrow();
        taskToEdit.setTask(todo.getTask());
        taskToEdit.setDescription(todo.getDescription());
    }
}
