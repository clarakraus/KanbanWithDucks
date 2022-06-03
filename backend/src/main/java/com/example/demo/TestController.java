package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.naming.OperationNotSupportedException;

import static com.example.demo.Status.OPEN;

@RestController
@RequestMapping("/api/kanban")
@RequiredArgsConstructor
public class TestController {


    @GetMapping()
    public List<ToDo> getTodo(){
        return List.of(new ToDo("178263", "Staubsasugen", "Schlafzimmer saugen", Status.OPEN),
                        new ToDo("1546367", "FreitagsAufgabe", "das Übliche", Status.IN_PROGRESS),
                        new ToDo("09876", "Kochen", "auch das Übliche", Status.DONE));



    }

}