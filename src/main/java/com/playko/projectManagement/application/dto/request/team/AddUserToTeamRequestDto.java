package com.playko.projectManagement.application.dto.request.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserToTeamRequestDto {

    @NotNull(message = "El ID del equipo es obligatorio")
    private Long teamId;

    @NotBlank(message = "El correo del usuario es obligatorio")
    @Email(message = "Debe proporcionar un correo electrónico válido")
    private String emailUser;
}
