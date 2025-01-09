package ru.max.to_do_list.models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private String message;
    private String status;
}
