package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
id : string,
task : string,
description : string,
status : string;

statusEnum = ["OPEN","IN_PROGRESS","DONE"]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ToDo {
    private String id;
    private String task;
    private String description;
    private Enum status;

}
