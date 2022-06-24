package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerTestIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldTestAllTheThings(){
        ToDo todo1 = new ToDo("Fische füttern", "Futter ins Wasser schmeißen", Status.OPEN );
        ToDo todo2 = new ToDo("Fische füttern", "Futter ins Wasser schmeißen", Status.OPEN );
        ToDo todo3 = new ToDo("Fische füttern", "Futter ins Wasser schmeißen", Status.OPEN );
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


}