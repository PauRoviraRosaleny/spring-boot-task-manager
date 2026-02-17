package com.example.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String index(Model model) {
    // Solo cargará las tareas de quien haya hecho login
    model.addAttribute("tasks", taskService.findUserTasks());
    return "index";
    }

    @PostMapping("/complete/{id}")
    public String completeTask(@PathVariable Long id) {
        taskService.checkCompleted(id);
        return "redirect:/";
    }

    @PostMapping("/new")
    public String nuevaTarea(@RequestParam String title, @RequestParam String description) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setCompleted(false);
        taskService.save(task);
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, @RequestParam String title, @RequestParam String description) {
        Task task = taskService.obtainById(id);
        task.setTitle(title);
        task.setDescription(description);
        taskService.save(task);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/";
    }
}