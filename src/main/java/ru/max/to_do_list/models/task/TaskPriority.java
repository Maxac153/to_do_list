package ru.max.to_do_list.models.task;

import lombok.Getter;

@Getter
public enum TaskPriority {
        HIGH("HIGH"),
        MEDIUM("MEDIUM"),
        LOW("LOW");

        private final String priority;

        TaskPriority(String priority) {
            this.priority = priority;
        }
}
