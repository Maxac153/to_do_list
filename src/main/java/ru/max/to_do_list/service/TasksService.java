package ru.max.to_do_list.service;

import ru.max.to_do_list.models.response.Response;
import ru.max.to_do_list.models.task.Task;

import java.util.List;

public interface TasksService {
    List<Task> getAllTasks();
    List<Task> getAllCompleteTasks();
    Task createTask(Task task);
    Task getTaskById(Long id);
    Response completeTask(Long id);
    Response updateTask(Long id, Task task);
    Response deleteTask(Long id);
}
