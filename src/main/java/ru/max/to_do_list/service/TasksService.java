package ru.max.to_do_list.service;

import ru.max.to_do_list.models.Response;
import ru.max.to_do_list.models.Task;

import java.util.List;

public interface TasksService {
    List<Task> getAllTasks();
    List<Task> getAllCompleteTasks();
    Task getTaskById(Long id);
    Task createTask(Task task);
    Response completeTask(Long id);
    Response updateTask(Long id, Task task);
    Response deleteTask(Long id);
}
