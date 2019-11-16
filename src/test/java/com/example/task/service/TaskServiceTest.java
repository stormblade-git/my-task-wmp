package com.example.task.service;

import com.example.task.model.TaskResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskServiceTest {

    @Test
    public void testGetResultModel() {

        String target = "b2Ba1Az";
        int count = 2;

        TaskResult result = new TaskService().getResult(target, count);
        assertEquals("A1a2Bb", result.getShare());
        assertEquals("z", result.getRest());
    }
}