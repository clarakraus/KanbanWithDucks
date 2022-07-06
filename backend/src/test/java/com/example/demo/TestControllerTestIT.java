package com.example.demo;

import com.example.demo.MyUser.LoginResponse;
import com.example.demo.MyUser.MyUser;
import org.apache.juli.logging.Log;
import org.apache.tomcat.util.http.parser.Authorization;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerTestIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void IntegrationTest() {

        //register user

        MyUser testUser = new MyUser("testname", "testpassword");

        ResponseEntity<Void> registerResponse = restTemplate.postForEntity("/api/user", testUser, Void.class);

        Assertions.assertThat(registerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        //login user

        ResponseEntity<LoginResponse> loginResponse = restTemplate.postForEntity("/api/login",testUser, LoginResponse.class );

        String token = loginResponse.getBody().getToken();

        ResponseEntity<ToDo[]> getResponse = restTemplate.exchange("/api/kanban", HttpMethod.GET, new HttpEntity<> (createHeader(token)), ToDo[].class);

        Assertions.assertThat(getResponse.getBody()).hasSize(0);

        //create a task

        ResponseEntity<Void> createTaskResponse = restTemplate.exchange(
                "/api/kanban",
                HttpMethod.POST,
                new HttpEntity<>(new ToDo("testtask", "testdescription"), createHeader(token)),
                Void.class);

        Assertions.assertThat(createTaskResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<ToDo[]> getResponse2 = restTemplate.exchange("/api/kanban", HttpMethod.GET, new HttpEntity<> (createHeader(token)), ToDo[].class);

        Assertions.assertThat(getResponse2.getBody()).hasSize(1);

        ToDo testTask = getResponse2.getBody()[0];

        ResponseEntity<Void> putResponse = restTemplate.exchange(
                "/api/kanban/next",
                HttpMethod.PUT,
                new HttpEntity<>(testTask, createHeader(token)), Void.class);

        Assertions.assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<ToDo[]> getResponse3 = restTemplate.exchange("/api/kanban", HttpMethod.GET, new HttpEntity<> (createHeader(token)), ToDo[].class);

        ToDo sameTestTask = getResponse3.getBody()[0];
        Assertions.assertThat(sameTestTask.getStatus()).isEqualTo(Status.IN_PROGRESS);













    }

    private HttpHeaders createHeader (String token) {
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", "Bearer " + token);
        return header;
    }

    /*    ToDo todo1 = new ToDo("Fische füttern", "Futter ins Wasser schmeißen", Status.OPEN, "defaultId");
        ToDo todo2 = new ToDo("Fische füttern", "Futter ins Wasser schmeißen", Status.OPEN, "defaultId");
        ToDo todo3 = new ToDo("Fische füttern", "Futter ins Wasser schmeißen", Status.OPEN, "defaultId");
        restTemplate.postForEntity("/api/kanban",todo1, Void.class);
        restTemplate.postForEntity("/api/kanban",todo2, Void.class);
        restTemplate.postForEntity("/api/kanban",todo3, Void.class);

        ResponseEntity<ToDo[]> getResponse1 = restTemplate.getForEntity("/api/kanban", ToDo[].class);

        Assertions.assertThat(getResponse1.getBody().length).isEqualTo(3);
        ToDo responseToDo = getResponse1.getBody()[0];

        restTemplate.put("/api/kanban/next", responseToDo, Void.class);
        ResponseEntity<ToDo> getResponse2 = restTemplate.getForEntity("/api/kanban/" + responseToDo.getId(), ToDo.class);
        Assertions.assertThat(getResponse2.getBody().getStatus()).isEqualTo((Status.IN_PROGRESS));
    }

     */


}