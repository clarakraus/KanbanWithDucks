package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/*
id : string,
task : string,
description : string,
status : string;

statusEnum = ["OPEN","IN_PROGRESS","DONE"]
 */
@Data
@Document(collection = "tasks")

public class ToDo {
    @Id
    private String id;
    private String task;
    private String description;
    private Status status;

    public ToDo(String task, String description, Status status) {
        this.task = task;
        this.description = description;
        this.status = status;
    }
}
