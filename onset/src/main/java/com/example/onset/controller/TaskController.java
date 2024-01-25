package com.example.onset.controller;

import com.example.onset.model.Task;
import com.example.onset.repository.TaskRepository;
import com.example.onset.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

        private TaskServiceImpl taskService;

        @Autowired
        public void setTaskService(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

        @GetMapping("/hello-world")
        public String helloWorld(){
            return "Hello from bourak onset";
        }

        @PostMapping
        public ResponseEntity<String> createTask(@RequestBody List<Task> tasks){
            taskService.createTask(tasks);
            return ResponseEntity.ok("Task created successfully.");
        }

        @GetMapping
        public List<Task> showTasks(){
            return taskService.showTasks();
        }

        @PutMapping("{id}")
        public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Task task){
            taskService.updateTask(id,task);
            return ResponseEntity.ok("Task updated successfully.");
        }

        @DeleteMapping("{id}")
        public ResponseEntity<String> deleteTask(@PathVariable Long id){
            taskService.deleteTask(id);
            return ResponseEntity.ok("Task deleted successfully.");
        }

         @GetMapping("/undoneTask")
         public List<Task> undoneTask(){
            return taskService.undoneTask();
        }


}
