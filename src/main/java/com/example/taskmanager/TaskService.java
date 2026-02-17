package com.example.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired private TaskRepository taskRepository; 
    @Autowired private UserRepository userRepository;

    public List<Task> findUserTasks() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return taskRepository.findByUserUsername(username);
    }

    public Task save(Task task) {
       String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task obtainById(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Task task = taskRepository.findById(id).orElseThrow();
        
        if (!task.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You don't have permission to access this task");
        }
        return task;
    }

    public void delete(Long id) {
        Task task = obtainById(id); 
        taskRepository.delete(task);
    }
    
    public Task update(Long id, Task taskDetails) {
        Task task = obtainById(id); 

        if (taskDetails.getTitle() != null && !taskDetails.getTitle().isBlank()) {
            task.setTitle(taskDetails.getTitle());
        }

        if (taskDetails.getDescription() != null) {
            task.setDescription(taskDetails.getDescription());
        }

        task.setCompleted(taskDetails.isCompleted());

        return taskRepository.save(task);
    }

    public void checkCompleted(Long id) {
        Task task = obtainById(id);
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}