package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    private Task task;
    @BeforeEach
    void setup(){
        taskRepository.deleteAll();

        task=new Task();
        task.setTitle("Test Task");
        task.setDescription("Testing");
        task.setStatus("Pending");
        task.setDueDateTime(LocalDateTime.now().plusDays(1));
        task=taskRepository.save(task);
    }

    @Test
    public void testSaveAndGetTask()
    {
        Task task=new Task();
        task.setTitle("New Task");
        task.setDescription("To test the task creation");
        task.setStatus("Pending");
        task.setDueDateTime(LocalDateTime.now().plusDays(1));

        Task savedTask=taskService.createTask(task);
        Optional<Task> fetchedTask=taskService.getTaskById(savedTask.getId());

        if(fetchedTask.isPresent())
        {
            assertEquals("New Task",fetchedTask.get().getTitle());
            assertEquals("Pending",fetchedTask.get().getStatus());
        }

    }

    @Test
    public void testGetTaskById() {
        Optional<Task> found=taskService.getTaskById(task.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo(task.getTitle());

    }
    @Test
    public void testGeAllTasks(){
       List<Task> allTasks=taskService.getAllTasks();
        assertThat(allTasks).isNotEmpty();
        assertThat(allTasks.get(0).getTitle()).isEqualTo(task.getTitle());
    }
    @Test
    public void testUpdateTask()
    {
        boolean updated=taskService.updateTaskStatus(task.getId(),"Completed");

        assertThat(updated).isTrue();

        Optional<Task> updatedTask=taskRepository.findById(task.getId());
        assertThat(updatedTask).isPresent();
        assertThat(updatedTask.get().getStatus()).isEqualTo("Completed");
    }

    @Test
    public void testDeleteTask()
    {
        boolean deleted=taskService.deleteTask(task.getId());
        assertThat(deleted).isTrue();
        assertThat(taskRepository.findById(task.getId())).isEmpty();

    }
}