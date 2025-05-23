package com.playko.projectManagement.infrastructure.output.jpa.adapter;

import com.playko.projectManagement.application.dto.request.EmailRequestDto;
import com.playko.projectManagement.application.handler.IEmailHandler;
import com.playko.projectManagement.domain.model.TaskModel;
import com.playko.projectManagement.domain.spi.ITaskPersistencePort;
import com.playko.projectManagement.infrastructure.exception.BoardColumnNotFoundException;
import com.playko.projectManagement.infrastructure.exception.BoardNotFoundException;
import com.playko.projectManagement.infrastructure.exception.DataNotFoundException;
import com.playko.projectManagement.infrastructure.exception.InvalidKeywordException;
import com.playko.projectManagement.infrastructure.exception.InvalidTaskStateException;
import com.playko.projectManagement.infrastructure.exception.ProjectNotFoundException;
import com.playko.projectManagement.infrastructure.exception.TaskNotFoundException;
import com.playko.projectManagement.infrastructure.exception.UnauthorizedException;
import com.playko.projectManagement.infrastructure.exception.UserNotFoundException;
import com.playko.projectManagement.infrastructure.output.jpa.entity.BoardColumnEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.BoardEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.ProjectEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.TaskEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.UserEntity;
import com.playko.projectManagement.infrastructure.output.jpa.mapper.ITaskEntityMapper;
import com.playko.projectManagement.infrastructure.output.jpa.repository.IBoardColumnRepository;
import com.playko.projectManagement.infrastructure.output.jpa.repository.IBoardRepository;
import com.playko.projectManagement.infrastructure.output.jpa.repository.IProjectRepository;
import com.playko.projectManagement.infrastructure.output.jpa.repository.ITaskRepository;
import com.playko.projectManagement.infrastructure.output.jpa.repository.IUserRepository;
import com.playko.projectManagement.shared.SecurityUtils;
import com.playko.projectManagement.shared.constants.MailMessages;
import com.playko.projectManagement.shared.enums.TaskPriority;
import com.playko.projectManagement.shared.enums.TaskState;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TaskJpaAdapter implements ITaskPersistencePort {
    private final ITaskRepository taskRepository;
    private final IProjectRepository projectRepository;
    private final IBoardColumnRepository boardColumnRepository;
    private final IUserRepository userRepository;
    private final ITaskEntityMapper taskEntityMapper;
    private final IEmailHandler emailHandler;
    private final SecurityUtils securityUtils;
    private final IBoardRepository boardRepository;

    @Override
    public List<TaskModel> getTasksByUserEmail() {
        String correoDelToken = securityUtils.obtenerCorreoDelToken();

        UserEntity userEntity = userRepository.findByEmail(correoDelToken)
                .orElseThrow(UserNotFoundException::new);

        List<TaskEntity> taskEntities = taskRepository.findByAssignedUser(userEntity);

        List<TaskEntity> tareasPermitidas = taskEntities.stream()
                .filter(task -> {
                    try {
                        securityUtils.validarAccesoProyecto(task.getProject().getId(), correoDelToken);
                        return true;
                    } catch (UnauthorizedException e) {
                        return false;
                    }
                })
                .toList();

        if (tareasPermitidas.isEmpty()) {
            throw new DataNotFoundException();
        }

        return taskEntityMapper.toDtoList(tareasPermitidas);
    }

    @Override
    public void saveTask(TaskModel taskModel) {
        ProjectEntity project = projectRepository.findById(taskModel.getProject().getId())
                .orElseThrow(ProjectNotFoundException::new);
        BoardColumnEntity boardColumn = boardColumnRepository.findById(taskModel.getBoardColumn().getId())
                .orElseThrow(BoardColumnNotFoundException::new);
        UserEntity user = userRepository.findById(taskModel.getAssignedUserId().getId())
                .orElseThrow(UserNotFoundException::new);

        String correoAutenticado = securityUtils.obtenerCorreoDelToken();
        securityUtils.validarAccesoProyecto(project.getId(), correoAutenticado);


        TaskEntity taskEntity = taskEntityMapper.toEntity(taskModel);
        taskEntity.setProject(project);
        taskEntity.setBoardColumn(boardColumn);
        taskEntity.setAssignedUser(user);

        taskRepository.save(taskEntity);
    }

    @Override
    public void assignTaskToUser(Long taskId, Long userId) {
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        String correoAutenticado = securityUtils.obtenerCorreoDelToken();
        securityUtils.validarAccesoProyecto(taskEntity.getProject().getId(), correoAutenticado);


        EmailRequestDto emailRequestDto = new EmailRequestDto();
        emailRequestDto.setDestinatario(userEntity.getEmail());
        emailRequestDto.setRemitente(correoAutenticado);
        emailRequestDto.setAsunto(MailMessages.AFFAIR_NEW_TASK_ASSIGNMENT);
        String message = String.format(
                "Hola %s,\n\nSe te ha asignado la tarea número %d. Para más información, comunícate con el manager de equipo.\n\nSaludos.",
                userEntity.getName(), taskId
        );
        emailRequestDto.setMensaje(message);
        emailHandler.sendEmail(emailRequestDto);

        taskEntity.setAssignedUser(userEntity);
        taskRepository.save(taskEntity);
    }

    @Override
    public void reassignTask(Long taskId, Long newUserId) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
        UserEntity newUser = userRepository.findById(newUserId)
                .orElseThrow(UserNotFoundException::new);

        String correoAutenticado = securityUtils.obtenerCorreoDelToken();
        securityUtils.validarAccesoProyecto(task.getProject().getId(), correoAutenticado);


        task.setAssignedUser(newUser);
        taskRepository.save(task);

        EmailRequestDto emailRequestDto = new EmailRequestDto();
        emailRequestDto.setDestinatario(task.getAssignedUser().getEmail());
        emailRequestDto.setRemitente(correoAutenticado);
        emailRequestDto.setAsunto("Nueva Tarea Asignada");
        String message = String.format("Se te ha reasignado la tarea '%s'.", task.getTitle());
        emailRequestDto.setMensaje(message);
        emailHandler.sendEmail(emailRequestDto);
    }

    @Override
    public void updateTaskDeadline(Long taskId, LocalDate deadline) {
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);

        String correoAutenticado = securityUtils.obtenerCorreoDelToken();
        securityUtils.validarAccesoProyecto(taskEntity.getProject().getId(), correoAutenticado);

        taskEntity.setLimitDate(deadline);
        taskRepository.save(taskEntity);

        EmailRequestDto emailRequestDto = new EmailRequestDto();
        emailRequestDto.setDestinatario(taskEntity.getAssignedUser().getEmail());
        emailRequestDto.setRemitente(correoAutenticado);
        emailRequestDto.setAsunto("Actualización de Tarea");
        String message = String.format("Se te ha reasignado la tarea '%s'.", taskEntity.getTitle());
        emailRequestDto.setMensaje(message);
        emailHandler.sendEmail(emailRequestDto);
    }

    @Override
    public void updateTaskState(Long taskId, TaskState newState) {
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);

        String correoAutenticado = securityUtils.obtenerCorreoDelToken();
        securityUtils.validarAccesoProyecto(taskEntity.getProject().getId(), correoAutenticado);

        taskEntity.setState(newState);
        taskRepository.save(taskEntity);

        EmailRequestDto emailRequestDto = new EmailRequestDto();
        emailRequestDto.setDestinatario(taskEntity.getAssignedUser().getEmail());
        emailRequestDto.setRemitente(correoAutenticado);
        emailRequestDto.setAsunto("Cambio en Tarea");
        String message = String.format("Se te ha reasignado la tarea '%s'.", taskEntity.getTitle());
        emailRequestDto.setMensaje(message);
        emailHandler.sendEmail(emailRequestDto);
    }

    @Override
    public List<TaskModel> getTasksByFilters(Long boardId, TaskState state, TaskPriority priority) {
        String correoDelToken = securityUtils.obtenerCorreoDelToken();

        BoardEntity board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);

        securityUtils.validarAccesoProyecto(board.getProject().getId(), correoDelToken);

        List<TaskEntity> taskEntities;

        if (state != null && priority != null) {
            taskEntities = taskRepository.findByBoardColumn_Board_IdAndStateAndPriority(boardId, state, priority);
        } else if (state != null) {
            taskEntities = taskRepository.findByBoardColumn_Board_IdAndState(boardId, state);
        } else if (priority != null) {
            taskEntities = taskRepository.findByBoardColumn_Board_IdAndPriority(boardId, priority);
        } else {
            taskEntities = taskRepository.findByBoardColumn_Board_IdAndState(boardId, TaskState.PENDING);
        }

        return taskEntities.stream()
                .map(taskEntityMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public long calculateTaskDuration(Long taskId) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);

        String correoAutenticado = securityUtils.obtenerCorreoDelToken();
        securityUtils.validarAccesoProyecto(task.getProject().getId(), correoAutenticado);


        if (!task.getState().equals(TaskState.DONE)) {
            throw new InvalidTaskStateException();
        }

        long diasTomados = ChronoUnit.DAYS.between(task.getCreationDate(), LocalDate.now());

        return diasTomados;
    }

    @Override
    public void deleteTask(Long taskId) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);

        String correoAutenticado = securityUtils.obtenerCorreoDelToken();
        securityUtils.validarAccesoProyecto(task.getProject().getId(), correoAutenticado);

        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskModel> searchTasksByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new InvalidKeywordException();
        }

        List<TaskEntity> taskEntities = taskRepository.findByKeyword(keyword);
        return taskEntityMapper.toDtoList(taskEntities);
    }

    @Override
    public String storeFile(MultipartFile file) throws Exception {
        try {
            String fileName = UUID.randomUUID().toString();
            byte[] bytes = file.getBytes();
            String fileOriginalName = file.getOriginalFilename();

            long fileSize = file.getSize();
            long maxFileSize = 5 * 1024 * 1024;

            if (fileSize > maxFileSize) {
                return "File size must be less then or equal 5MB";
            }

            if (
                    !fileOriginalName.endsWith(".jpg") &
                    !fileOriginalName.endsWith(".jpeg") &
                    !fileOriginalName.endsWith(".png")
            ) {
                return "Only JPG, JPEG, PNG files are allowed!";
            }
            String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
            String newFileName = fileName + fileExtension;

            File folder = new File("%USERPROFILE%\\Downloads");
            if (!folder.exists()) {
                folder.mkdir();
            }
            Path path = Paths.get("%USERPROFILE%\\Downloads" + newFileName);
            Files.write(path, bytes);
            return "File upload seccesfully!!";

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    // Es para descargar un .ics para luego exportar en Calendar de google u otros
    @Override
    public String generateIcsForTasks() {
        String correo = securityUtils.obtenerCorreoDelToken();

        List<TaskEntity> tasks = taskRepository.findAllByAssignedUserEmail(correo);

        StringBuilder sb = new StringBuilder();
        sb.append("BEGIN:VCALENDAR\n");
        sb.append("VERSION:2.0\n");
        sb.append("PRODID:-//TaskSync//EN\n");

        for (TaskEntity task : tasks) {
            if (task.getLimitDate() != null) {
                sb.append("BEGIN:VEVENT\n");
                sb.append("UID:task-").append(task.getId()).append("@taskapp.com\n");
                sb.append("SUMMARY:").append(escapeIcsText(task.getTitle())).append("\n");
                sb.append("DESCRIPTION:").append(escapeIcsText(task.getDescription() != null ? task.getDescription() : "")).append("\n");
                sb.append("DTSTART;VALUE=DATE:").append(task.getLimitDate().toString().replace("-", "")).append("\n");
                sb.append("DTEND;VALUE=DATE:").append(task.getLimitDate().plusDays(1).toString().replace("-", "")).append("\n");
                sb.append("END:VEVENT\n");
            }
        }

        sb.append("END:VCALENDAR");
        return sb.toString();
    }

    @Scheduled(cron = "0 0 8 * * ?", zone = "America/New_York")
    public void sendTaskReminders() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/New_York"));
        LocalDateTime startOfToday = now.toLocalDate().atStartOfDay();
        LocalDateTime endOfTomorrow = now.toLocalDate().plusDays(1).atTime(23, 59, 59);

        List<TaskEntity> tasks = taskRepository.findTasksDueSoon(startOfToday, endOfTomorrow);
        tasks.stream()
                .filter(task -> task.getAssignedUser() != null)
                .forEach(task -> {
                    String correo = task.getAssignedUser().getEmail();
                    EmailRequestDto emailRequestDto = new EmailRequestDto();
                    emailRequestDto.setDestinatario(task.getAssignedUser().getEmail());
                    emailRequestDto.setRemitente(correo);
                    emailRequestDto.setAsunto("Recordatorio: Tarea próxima a vencer");
                    emailRequestDto.setMensaje("La tarea '" + task.getTitle() + "' vence el " + task.getLimitDate() + ".");
                    emailHandler.sendEmail(emailRequestDto);
                });
    }
    private String escapeIcsText(String input) {
        return input.replace("\n", "\\n").replace("\r", "").replace("\"", "'");
    }
}
