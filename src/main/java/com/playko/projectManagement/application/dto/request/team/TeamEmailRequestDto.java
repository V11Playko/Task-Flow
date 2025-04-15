package com.playko.projectManagement.application.dto.request.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class TeamEmailRequestDto {

    @NotNull(message = "El ID del equipo es obligatorio")
    private Long teamId;

    @NotBlank(message = "El asunto es obligatorio")
    @Size(max = 100, message = "El asunto no puede tener más de 100 caracteres")
    private String subject;

    @NotBlank(message = "El mensaje es obligatorio")
    @Size(max = 1000, message = "El mensaje no puede tener más de 1000 caracteres")
    private String message;

    @NotBlank(message = "El remitente es obligatorio")
    @Size(max = 100, message = "El remitente no puede tener más de 100 caracteres")
    private String remitente;
}
