package com.example.onset.service;

import com.example.onset.model.Task;
import com.example.onset.repository.TaskRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public ResponseEntity<String> createTask(@RequestBody List<Task> tasks){

        List<Task> createdTasks = new ArrayList<>();

        for (Task task : tasks) {
            Task createdTask = taskRepository.save(task);
            createdTasks.add(createdTask);
        }
        return ResponseEntity.ok("Task updated successfully.");
    }
    @Override
    public List<Task> showTasks(){
        return taskRepository.findAll();
    }
    @Override
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Task task){
        Optional<Task> optionalTask = taskRepository.findById(task.getId());
        if (optionalTask.isPresent()) {
            task.setId(id);
            taskRepository.save(task);
            return ResponseEntity.ok("Task updated successfully."); // Başarı durumu (HTTP 200)
        }else{return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task update is failure. ID: " + task.getId());}

    }
    @Override
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            taskRepository.delete(taskRepository.getReferenceById(id));
            return ResponseEntity.ok("Task deleted successfully."); // Başarı durumu (HTTP 200)
        }else{return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task deleted is failure. ID: " + id);}
    }
    @Override
    public List<Task> undoneTask() {
        List<Task> incompleteTasks = taskRepository.findTaskByCompletedIsFalse();
        if (!incompleteTasks.isEmpty()) {
                return taskRepository.findTaskByCompletedIsFalse();
            } else {
                System.out.println("There is no Undone Task");
            }
        return taskRepository.findTaskByCompletedIsFalse();
        }
    @Override
    public Long getTaskCount() {
        return taskRepository.count();
    }
}
