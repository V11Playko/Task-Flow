package com.playko.projectManagement.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailRequestDto {

    @NotBlank(message = "El destinatario no puede estar vacío.")
    @Email(message = "El formato del correo del destinatario no es válido.")
    private String destinatario;

    @NotBlank(message = "El asunto no puede estar vacío.")
    @Size(max = 150, message = "El asunto no puede superar los 150 caracteres.")
    private String asunto;

    @NotBlank(message = "El mensaje no puede estar vacío.")
    @Size(max = 1000, message = "El mensaje no puede superar los 1000 caracteres.")
    private String mensaje;

    @Email(message = "El formato del correo del remitente no es válido.")
    private String remitente;
}
