package ru.max.to_do_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.max.to_do_list.models.task.Task;

import java.util.List;

public interface TasksRepository extends JpaRepository<Task, Long> {
    Task getTaskById(Long id);

    @Query(value = "SELECT * FROM tasks WHERE completed = true", nativeQuery = true)
    List<Task> getAllCompletedTasks();
}
