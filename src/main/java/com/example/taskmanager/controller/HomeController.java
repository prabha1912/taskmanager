package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class HomeController {

    private final TaskService taskService;

    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("message","Welcome to Task Manager!");
        return "index";
    }

    @GetMapping
    public String viewAllTasks(Model model)
    {
        model.addAttribute("tasks",taskService.getAllTasks());
        return "task-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model)
    {
        model.addAttribute("task",new Task());
        return "task-form";
    }

    @PostMapping("/save")
    public String saveTask(@Valid @ModelAttribute Task task, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "task-form";
        }
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,Model model)
    {
        Optional<Task> task= taskService.getTaskById(id);
        if(task.isPresent())
        {
            model.addAttribute("task",task.get());
            return  "task-form";
        }
        else {
            return "redirect:/tasks";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}
