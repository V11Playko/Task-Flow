package com.playko.projectManagement.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardColumnRequestDto {

    @NotBlank(message = "El nombre de la columna no puede estar vacío.")
    @Size(max = 50, message = "El nombre de la columna no puede superar los 50 caracteres.")
    private String name;
}
