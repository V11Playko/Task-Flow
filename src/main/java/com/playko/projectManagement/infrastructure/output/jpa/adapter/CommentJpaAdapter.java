package com.playko.projectManagement.infrastructure.output.jpa.adapter;

import com.playko.projectManagement.domain.model.CommentModel;
import com.playko.projectManagement.domain.spi.ICommentPersistencePort;
import com.playko.projectManagement.infrastructure.output.jpa.entity.CommentEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.TaskEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.UserEntity;
import com.playko.projectManagement.infrastructure.output.jpa.repository.ICommentRepository;
import com.playko.projectManagement.infrastructure.output.jpa.repository.ITaskRepository;
import com.playko.projectManagement.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CommentJpaAdapter implements ICommentPersistencePort {
    private final ICommentRepository commentRepository;
    private final IUserRepository userRepository;
    private final ITaskRepository taskRepository;
    private final ICommentEntityMapper commentEntityMapper;

    @Override
    public void addComment(CommentModel commentModel) {
        TaskEntity task = taskRepository.findById(commentModel.getTaskId())
                .orElseThrow(TaskNotFoundException::new);
        UserEntity user = userRepository.findById(commentModel.getUserId())
                .orElseThrow(UserNotFoundException::new);

        CommentEntity commentEntity = commentEntityMapper.toEntity(commentModel);
        commentEntity.setTask(task);
        commentEntity.setUser(user);
        commentEntity.setCreatedAt(LocalDateTime.now());

        commentRepository.save(commentEntity);
    }

}
