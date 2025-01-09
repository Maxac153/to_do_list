package ru.max.to_do_list.models.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    private TaskPriority priority;
    private LocalDateTime deadline;
    private Boolean completed;
}
