package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoServiceTest {

    @Test
    void shouldAddTask(){
        //given
    ToDoRepo testRepo = Mockito.mock(ToDoRepo.class);
    ToDo newTask = new ToDo("Kaffee trinken", "gemütlich", Status.DONE);
    ToDoService testService = new ToDoService(testRepo);

    testService.addNew(newTask);


    Mockito.verify(testRepo).add(newTask);
    }

    @Test
    void shouldReturnAllTodos() {
        ToDoRepo testRepo = Mockito.mock(ToDoRepo.class);
        ToDo task1 = new ToDo("Kaffee trinken", "gemütlich", Status.DONE);
        ToDo task2 = new ToDo("Tee trinken", "hektisch", Status.IN_PROGRESS);
        ToDo task3 = new ToDo("Wasser trinken", "langsam", Status.OPEN);
        ToDoService testService = new ToDoService(testRepo);

        Mockito.when(testRepo.list()).thenReturn(List.of(task1, task2, task3));


    }


}