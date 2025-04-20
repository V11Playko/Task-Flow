package com.playko.projectManagement.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class BoardRequestDto {

    @NotBlank(message = "El nombre del tablero no puede estar vacío.")
    @Size(max = 50, message = "El nombre del tablero no puede superar los 50 caracteres.")
    private String name;

    @NotNull(message = "El ID del proyecto es obligatorio.")
    private Long projectId;
}

