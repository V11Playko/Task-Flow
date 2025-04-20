package com.playko.projectManagement.application.dto.request.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class TaskReassignmentRequestDto {

    @NotNull(message = "El ID de la tarea no puede ser nulo")
    private Long taskId;

    @NotNull(message = "El ID del nuevo usuario no puede ser nulo")
    private Long newUserId;
}
