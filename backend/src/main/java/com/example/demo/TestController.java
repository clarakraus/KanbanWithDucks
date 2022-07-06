package com.example.demo;

import java.security.Principal;
import java.util.List;

import com.example.demo.MyUser.MyUser;
import com.example.demo.MyUser.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/kanban")
@RequiredArgsConstructor

public class TestController {
    private final ToDoService toDoService;

    private final MyUserService myUserService;


    @GetMapping()
    public List<ToDo> findAllTodos(Principal principal){
        MyUser myUser = myUserService.findByUserName(principal.getName());
        return toDoService.getTodos(myUser.getUserId());
    }

    @PostMapping()
    public void addToDo(@RequestBody ToDo todo, Principal principal){
        MyUser myUser = myUserService.findByUserName(principal.getName());
        toDoService.addNew(todo, myUser.getUserId());
    }

    @PutMapping("/next")
    public void moveToNextState(@RequestBody ToDo todo){
        toDoService.changeState(todo);
    }
    @PutMapping("/prev")
    public void setToPrevState(@RequestBody ToDo todo){
        toDoService.setBackState(todo);
    }

    @DeleteMapping(path ="{todoid}")
    public void removeToDoTask(@PathVariable String todoid){
        toDoService.deleteTask(todoid);
    }
    @GetMapping(path = "/{todoid}")
    public ToDo getTodoTask(@PathVariable String todoid){
        return toDoService.getTaskTodo(todoid);
    }
    @PutMapping()
    public void editTodoTask(@RequestBody ToDo todo){
        toDoService.editTaskAndDesc(todo);
    }


}