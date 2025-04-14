package com.playko.projectManagement.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CommentRequestDto {

    @NotBlank(message = "El contenido del comentario no puede estar vacío.")
    private String content;

    @NotNull(message = "El ID de la tarea no puede ser nulo.")
    private Long taskId;
}
