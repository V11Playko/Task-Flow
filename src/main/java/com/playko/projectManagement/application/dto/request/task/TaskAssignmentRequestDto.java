package com.playko.projectManagement.application.dto.request.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class TaskAssignmentRequestDto {

    @NotNull(message = "El ID de la tarea no puede ser nulo")
    private Long taskId;

    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Long userId;
}
