package com.playko.projectManagement.application.dto.request;

import com.playko.projectManagement.shared.enums.SubTaskState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class SubTaskRequestDto {

    @NotBlank(message = "El título no puede estar vacío.")
    @Size(max = 100, message = "El título no puede superar los 100 caracteres.")
    private String title;

    @NotBlank(message = "La descripción no puede estar vacía.")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres.")
    private String description;

    @NotNull(message = "El estado no puede ser nulo.")
    private SubTaskState state;

    @NotNull(message = "La fecha límite no puede ser nula.")
    private LocalDate limitDate;

    @NotBlank(message = "El ID de la tarea no puede estar vacío.")
    private String task;
}

