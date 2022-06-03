package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.naming.OperationNotSupportedException;

import static com.example.demo.Status.OPEN;

@RestController
@RequestMapping("/api/kanban")
@RequiredArgsConstructor
public class TestController {
    private final ToDoService toDoService;


    @GetMapping()
    public List<ToDo> findAllTodos(){
        return toDoService.getTodo();
    }

    @PostMapping()
    public void addToDo(@RequestBody ToDo todo){
        toDoService.addNew(todo);


    }

}