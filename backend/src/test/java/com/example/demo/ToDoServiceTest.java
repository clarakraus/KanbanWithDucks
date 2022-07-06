package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

class ToDoServiceTest {

    @Test
    void shouldAddTask(){
        //given
    DBRepository testRepo = Mockito.mock(DBRepository.class);
    ToDo newTask = new ToDo(null, "Kaffee trinken", "gemütlich", Status.DONE);
    String testUserId = "defaultId";
    ToDoService testService = new ToDoService(testRepo);

    testService.addNew(newTask, testUserId);

    Mockito.verify(testRepo).save(newTask);
    Assertions.assertThat(newTask.getUserId()).isEqualTo(testUserId);
    }

    @Test
    void shouldReturnAllTodos() {
        DBRepository testRepo = Mockito.mock(DBRepository.class);
        ToDo task1 = new ToDo("Kaffee trinken", "gemütlich", Status.DONE, "defaultId");
        ToDo task2 = new ToDo("Tee trinken", "hektisch", Status.IN_PROGRESS, "defaultId");
        ToDo task3 = new ToDo("Wasser trinken", "langsam", Status.OPEN, "defaultId");
        ToDoService testService = new ToDoService(testRepo);

        Mockito.when(testRepo.findAllByUserId("defaultId")).thenReturn(List.of(task1, task2, task3));

        org.assertj.core.api.Assertions.assertThat(testService.getTodos("defaultId")).isEqualTo(List.of(task1, task2, task3));
    }
    @Test
    void shouldDeleteTask(){
        //given
        DBRepository testRepo = Mockito.mock(DBRepository.class);
        ToDo task1 = new ToDo("Kaffee trinken", "gemütlich", Status.DONE, "defaultId");
        ToDo task2 = new ToDo("Tee trinken", "hektisch", Status.OPEN, "defaultId");
        ToDoService testService = new ToDoService(testRepo);


        testService.deleteTask(task1.getId());


        Mockito.verify(testRepo).deleteById(task1.getId());
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
        DBRepository testRepo = Mockito.mock(DBRepository.class);
        ToDo task1 = new ToDo("Kaffee trinken", "gemütlich", Status.DONE, "defaultId");
        ToDoService testService = new ToDoService(testRepo);
        String testUserId = "newid";

        testService.addNew(task1, testUserId);

        testService.deleteTask(task1.getId());

        org.junit.jupiter.api.Assertions.assertTrue(testRepo.findAll().size() == 0);
    }

    @Test

    void shouldDeleteToDo2(){

        DBRepository testRepo = Mockito.mock(DBRepository.class);
        ToDoService testService = new ToDoService(testRepo);
        ToDo task1 = new ToDo("Kaffee trinken", "gemütlich", Status.DONE, "defaultId");
        ToDo task2 = new ToDo("Tee trinken", "hektisch", Status.IN_PROGRESS, "defaultId");
        String testUserId1= "user1";
        String testUserId2= "user2";
        testService.addNew(task1, testUserId1);
        testService.addNew(task2, testUserId2);
        Mockito.when(testRepo.findAll()).thenReturn(List.of(task2));
        testService.deleteTask(task1.getId());
        Assertions.assertThat(testRepo.findAll()).containsOnly(task2);
        Mockito.verify(testRepo).deleteById(task1.getId());

    }

    @Test
    void shouldPromoteTask(){
        DBRepository testRepo = Mockito.mock(DBRepository.class);
        ToDoService testService = new ToDoService(testRepo);
        ToDo task = new ToDo("taskId", "Enten streicheln", "Mit rechter Hand", Status.OPEN, "defaultId");

        Mockito.when(testRepo.findById("taskId")).thenReturn(Optional.of(task));

        testService.changeState(task);

        ToDo expected = new ToDo("taskId", "Enten streicheln", "Mit rechter Hand", Status.IN_PROGRESS, "defaultId");

        Mockito.verify(testRepo).save(expected);

        //test noch nicht fertig





    }


}