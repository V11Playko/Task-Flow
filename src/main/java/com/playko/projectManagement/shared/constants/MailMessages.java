package com.playko.projectManagement.shared.constants;

public class MailMessages {

    private MailMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String AFFAIR_NEW_TASK_ASSIGNMENT = "Se te ha asignado una nueva tarea";
    public static final String EMAIL_NOT_SEND_MESSAGE = "El correo no se envió correctamente.";
}