package com.example.onset.service;


import com.example.onset.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TaskService {
    ResponseEntity<String> createTask(@RequestBody List<Task> tasks);

    List<Task> showTasks();

    ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Task task);

    ResponseEntity<String> deleteTask(@PathVariable Long id);
}
