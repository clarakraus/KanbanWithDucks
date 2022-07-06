package com.example.demo;

import com.example.demo.MyUser.MyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ToDoService {
    private final DBRepository toDoRepo;


    public List<ToDo> getTodos(String userId) {
        return toDoRepo.findAllByUserId(userId);
    }

    public void addNew(ToDo todo, String userId) {
        todo.setUserId(userId);
        todo.setStatus(Status.OPEN);
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
        toDoRepo.save(taskToEdit);
    }
}
