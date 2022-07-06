package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
id : string,
task : string,
description : string,
status : string;

statusEnum = ["OPEN","IN_PROGRESS","DONE"]
 */
@Data
@Document(collection = "tasks")
@NoArgsConstructor
@AllArgsConstructor

public class ToDo {
    @Id
    private String id;
    private String task;
    private String description;
    private Status status;

    private String userId;

    public ToDo(String task, String description, Status status, String userId) {
        this.task = task;
        this.description = description;
        this.status = status;
        this.userId = userId;

    }

    public ToDo(String id, String task, String description, Status status) {
        this.id = id;
        this.task = task;
        this.description = description;
        this.status = status;
    }

    public ToDo(String task, String description) {
        this.task = task;
        this.description = description;
    }
}
