package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //Create
    public Task createTask(Task task)
    {
        return taskRepository.save(task);
    }

    //Get a task by ID
    public Optional<Task> getTaskById(Long id)
    {
        return taskRepository.findById(id);
    }

    //Get all tasks
    public List<Task> getAllTasks()
    {
        return taskRepository.findAll();
    }

    //update task status
    public boolean updateTaskStatus(Long id, String status)
    {
        Optional<Task> optionalTask=taskRepository.findById(id);
        if(optionalTask.isPresent())
        {
            Task task=optionalTask.get();
            task.setStatus(status);
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    //Delete a task
    public boolean deleteTask(Long id)
    {
        Optional<Task> optionalTask=taskRepository.findById(id);
        if(optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
