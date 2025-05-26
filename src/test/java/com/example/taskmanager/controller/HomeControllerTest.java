package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private Task sampleTask;

    @BeforeEach
    void setUp()
    {
        sampleTask=new Task("Test Task","Description","Pending",LocalDateTime.now());
    }

    @Test
    public void testGetAllTasks() throws Exception {

        when(taskService.getAllTasks()).thenReturn(List.of(sampleTask));
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(view().name("task-list"))
                .andExpect(model().attributeExists("tasks"));
        verify(taskService).getAllTasks();

    }
    @Test
    void testShowCreateForm() throws Exception {
        mockMvc.perform(get("/tasks/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("task-form"))
                .andExpect(model().attributeExists("task"));
    }
    @Test
    void testEditTaskForm() throws Exception{
        when(taskService.getTaskById(1L)).thenReturn(Optional.of(sampleTask));
        mockMvc.perform(get("/tasks/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("task-form"))
                .andExpect(model().attributeExists("task"));
    }
    @Test
    void testSaveTask() throws Exception{
        mockMvc.perform(post("/tasks/save")
                .param("title","New Task")
                .param("description","Testing")
                .param("status","InProgress")
                .param("dueDateTime","2025-06-01T12:00"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));
        verify(taskService).createTask(any(Task.class));

    }
    @Test
    void testDeleteTask() throws Exception{
        mockMvc.perform((get("/tasks/delete/1")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));
        verify(taskService).deleteTask(1L);
    }


}