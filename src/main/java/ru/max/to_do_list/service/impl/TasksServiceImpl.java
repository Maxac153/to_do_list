package ru.max.to_do_list.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.max.to_do_list.models.response.Response;
import ru.max.to_do_list.models.task.Task;
import ru.max.to_do_list.repository.TasksRepository;
import ru.max.to_do_list.service.TasksService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TasksServiceImpl implements TasksService {
    @Autowired
    private final TasksRepository repository;

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public List<Task> getAllCompleteTasks() {
        return repository.getAllCompletedTasks();
    }

    @Override
    public Task getTaskById(Long id) {
        return repository.getTaskById(id);
    }

    @Override
    public Task createTask(Task task) {
        return repository.save(task);
    }

    @Override
    @Transactional
    public Response completeTask(Long id) {
        Optional<Task> existingTaskOptional = repository.findById(id);

        if (existingTaskOptional.isEmpty()) {
            return Response.builder()
                    .message("Task not found!")
                    .status("ERROR")
                    .build();
        }

        Task existingTask = existingTaskOptional.get();
        if (existingTask.getCompleted() == null || !existingTask.getCompleted()) {
            existingTask.setCompleted(true);
            repository.save(existingTask);

            return Response.builder()
                    .message("Task marked as completed successfully.")
                    .status("SUCCESS")
                    .build();
        } else {
            return Response.builder()
                    .message("Task is already completed!")
                    .status("INFO")
                    .build();
        }
    }

    @Override
    @Transactional
    public Response updateTask(Long id, Task task) {
        Optional<Task> existingTaskOptional = repository.findById(Long.valueOf(id));

        if (existingTaskOptional.isEmpty()) {
            return Response.builder()
                    .message("Task not found!")
                    .status("ERROR")
                    .build();
        }

        Task existingTask = existingTaskOptional.get();
        if (task.getTitle() != null) {
            existingTask.setTitle(task.getTitle());
        }
        if (task.getDescription() != null) {
            existingTask.setDescription(task.getDescription());
        }
        if (task.getPriority() != null) {
            existingTask.setPriority(task.getPriority());
        }
        if (task.getDeadline() != null) {
            existingTask.setDeadline(task.getDeadline());
        }
        if (task.getCompleted() != null) {
            existingTask.setCompleted(task.getCompleted());
        }

        repository.save(existingTask);

        return Response.builder()
                .message("Task updated successfully.")
                .status("SUCCESS")
                .build();
    }

    @Override
    @Transactional
    public Response deleteTask(Long id) {
        Optional<Task> existingTaskOptional = repository.findById(id);
        if (existingTaskOptional.isEmpty()) {
            return Response.builder()
                    .message("Task not found!")
                    .status("ERROR")
                    .build();
        }

        repository.deleteById(id);
        return Response.builder()
                .message("Task deleted successfully.")
                .status("SUCCESS")
                .build();
    }
}
