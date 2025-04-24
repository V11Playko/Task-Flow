package com.playko.projectManagement.shared.constants;

public class ExceptionMessages {

    private ExceptionMessages() {
        throw new IllegalStateException("Utility class");
    }
    public static final String RESPONSE_MESSAGE_KEY = "Mensaje";
    public static final String UNAUTHORIZED_MESSAGE = "No tienes permiso para acceder a este recurso";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "Este correo ya está en uso.";
    public static final String USER_NOT_FOUND_MESSAGE = "Usuario no encontrado.";
    public static final String USERS_NOT_FOUND_MESSAGE = "Usuarios no encontrados.";
    public static final String TEAM_NOT_FOUND_MESSAGE = "Equipo no encontrado.";
    public static final String USER_NOT_FOUND_IN_TEAM_MESSAGE = "Usuario no existe en el equipo.";
    public static final String PROJECT_ALREADY_EXISTS_MESSAGE = "Este proyecto ya está en uso.";
    public static final String PROJECT_NOT_FOUND_MESSAGE = "Este proyecto no existe.";
    public static final String BOARD_COLUMN_NOT_FOUND_MESSAGE = "Esta columna de la tabla no existe.";
    public static final String TASK_NOT_FOUND_MESSAGE = "Esta tarea no existe.";
    public static final String INVALID_PROJECT_STATE_MESSAGE = "Sólo se pueden archivar los proyectos finalizados.";
    public static final String SUB_TASK_NOT_FOUND_MESSAGE = "Subtarea no encontrada.";
    public static final String BOARD_NOT_FOUND_MESSAGE = "Esta tabla Kanban no existe.";
    public static final String INVALID_TASK_STATE_MESSAGE = "La tarea aún no ha sido completada.";
    public static final String USER_ALREADY_RESTRICTED_MESSAGE = "El usuario ya está restringido.";
    public static final String USER_NOT_RESTRICTED_MESSAGE = "El usuario indicado no está restringido.";
    public static final String PROJECTS_NOT_FOUND_MESSAGE = "No hay proyectos asignados para enviar el resumen.";
    public static final String INVALID_KEYWORD_MESSAGE = "La palabra clave no puede estar vacía.";
    public static final String FILE_NOT_FOUND_MESSAGE = "Archivo no encontrado.";
    public static final String ROLE_NOT_FOUND_MESSAGE = "Rol no encontrado.";
    public static final String USER_ALREADY_IN_TEAM_MESSAGE = "El usuario ya está en el equipo.";
    public static final String EMPTY_TEAM_MESSAGE = "El equipo no tiene miembros.";
    public static final String INVALID_RESTRICTION_MESSAGE = "No se puede restringir al propietario del proyecto.";
    public static final String INVALID_BOARD_OPERATION_MESSAGE = "La columna no pertenece al mismo tablero que la tarea.";
    public static final String DATA_NOT_FOUND_MESSAGE = "No se encuentran datos existentes.";
}
