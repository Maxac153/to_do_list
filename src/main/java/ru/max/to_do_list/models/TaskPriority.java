package ru.max.to_do_list.models;

import lombok.Getter;

@Getter
public enum TaskPriority {
        HIGH("HIGH"),
        MEDIUM("MEDIUM"),
        LOW("LOW");

        private final String description;

        TaskPriority(String description) {
            this.description = description;
        }
}
