package com.playko.projectManagement.infrastructure.output.jpa.adapter;

import com.playko.projectManagement.domain.model.CommentModel;
import com.playko.projectManagement.domain.spi.ICommentPersistencePort;
import com.playko.projectManagement.infrastructure.exception.TaskNotFoundException;
import com.playko.projectManagement.infrastructure.exception.UserNotFoundException;
import com.playko.projectManagement.infrastructure.output.jpa.entity.CommentEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.TaskEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.UserEntity;
import com.playko.projectManagement.infrastructure.output.jpa.mapper.ICommentEntityMapper;
import com.playko.projectManagement.infrastructure.output.jpa.repository.ICommentRepository;
import com.playko.projectManagement.infrastructure.output.jpa.repository.ITaskRepository;
import com.playko.projectManagement.infrastructure.output.jpa.repository.IUserRepository;
import com.playko.projectManagement.shared.SecurityUtils;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class CommentJpaAdapter implements ICommentPersistencePort {
    private final ICommentRepository commentRepository;
    private final IUserRepository userRepository;
    private final ITaskRepository taskRepository;
    private final ICommentEntityMapper commentEntityMapper;
    private final SecurityUtils securityUtils;

    @Override
    public void addComment(CommentModel commentModel) {
        TaskEntity task = taskRepository.findById(commentModel.getTask().getId())
                .orElseThrow(TaskNotFoundException::new);
        UserEntity user = userRepository.findById(commentModel.getUser().getId())
                .orElseThrow(UserNotFoundException::new);

        String correoAutenticado = securityUtils.obtenerCorreoDelToken();
        securityUtils.validarAccesoProyecto(task.getProject().getId(), correoAutenticado);


        CommentEntity commentEntity = commentEntityMapper.toEntity(commentModel);
        commentEntity.setTask(task);
        commentEntity.setUser(user);
        commentEntity.setCreatedAt(LocalDateTime.now());

        commentRepository.save(commentEntity);
    }

    @Override
    public List<CommentModel> getCommentsByTask(Long taskId) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);

        String correoAutenticado = securityUtils.obtenerCorreoDelToken();
        securityUtils.validarAccesoProyecto(task.getProject().getId(), correoAutenticado);

        return commentEntityMapper.toModelList(commentRepository.findByTaskId(taskId));
    }

}
