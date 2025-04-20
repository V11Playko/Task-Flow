package com.playko.projectManagement.application.dto.request.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class ProjectDeadlineRequestDto {

    @NotNull(message = "El ID del proyecto no puede ser nulo")
    private Long projectId;

    @NotNull(message = "La fecha límite es obligatoria")
    @FutureOrPresent(message = "La fecha límite debe ser hoy o en el futuro")
    private LocalDate deadline;
}
