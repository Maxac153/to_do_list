package ru.max.to_do_list.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.max.to_do_list.models.Response;
import ru.max.to_do_list.models.Task;
import ru.max.to_do_list.service.TasksService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TasksController {
    private final TasksService service;

    @GetMapping("/allTasks")
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/allCompletedTasks")
    public List<Task> getAllCompletedTasks() {
        return service.getAllCompleteTasks();
    }

    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @PostMapping("/createTask")
    public Task createTask(@RequestBody Task task) {
        return service.createTask(task);
    }

    @DeleteMapping("/deleteTask/{id}")
    public Response deleteTask(@PathVariable Long id) {
        return service.deleteTask(id);
    }

    @PutMapping("/updateTask/{id}")
    public Response updateTask(@PathVariable Long id, @RequestBody Task task) {
        return service.updateTask(id, task);
    }

    @PatchMapping("/completeTask/{id}")
    public Response completeTask(@PathVariable Long id) {
        return service.completeTask(id);
    }
}
