package com.playko.projectManagement.application.dto.request.task;

import com.playko.projectManagement.shared.enums.TaskState;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskStateUpdateRequestDto {

    @NotNull(message = "El nuevo estado de la tarea es obligatorio")
    @Enumerated(EnumType.STRING)
    private TaskState newState;
}
