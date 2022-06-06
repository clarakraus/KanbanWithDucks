package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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


        org.assertj.core.api.Assertions.assertThat(testService.getTodos()).isEqualTo(List.of(task1, task2, task3));

    }
    @Test
    void shouldDeleteTask(){
        //given
        ToDoRepo testRepo = Mockito.mock(ToDoRepo.class);
        ToDo task1 = new ToDo("Kaffee trinken", "gemütlich", Status.DONE);
        ToDo task2 = new ToDo("Tee trinken", "hektisch", Status.OPEN);
        ToDoService testService = new ToDoService(testRepo);


        testService.deleteTask(task1.getId());


        Mockito.verify(testRepo).delete(task1.getId());
    }

   /* @Test
    void shouldEditTask(){
        ToDoRepo testRepo = Mockito.mock(ToDoRepo.class);
        ToDo task1 = new ToDo("Kaffee trinken", "gemütlich", Status.DONE);
        ToDoService testService = new ToDoService(testRepo);

        Mockito.when(testRepo.findToDoById("testid1234")).thenReturn(Optional.of(task1));

        testService.editTaskAndDesc(task1);

        Assertions.assertThat()
//😎
*/

    @Test

    void shouldDeleteToDo(){
        ToDoRepo testRepo = new ToDoRepo();
        ToDo task1 = new ToDo("Kaffee trinken", "gemütlich", Status.DONE);
        ToDoService testService = new ToDoService(testRepo);

        testService.addNew(task1);

        testService.deleteTask(task1.getId());

        org.junit.jupiter.api.Assertions.assertTrue(testRepo.list().size() == 0);
    }

    @Test

    void shouldDeleteToDo2(){

        ToDoRepo testRepo = Mockito.mock(ToDoRepo.class);
        ToDoService testService = new ToDoService(testRepo);
        ToDo task1 = new ToDo("Kaffee trinken", "gemütlich", Status.DONE);
        ToDo task2 = new ToDo("Tee trinken", "hektisch", Status.IN_PROGRESS);
        List<ToDo> testList = new ArrayList<>();
        testList.add(task1);
        testList.add(task2);
        Mockito.when(testRepo.list()).thenReturn(testList);
        testService.deleteTask(task1.getId());
        Assertions.assertThat(testRepo.list()).containsOnly(task2);

    }


}