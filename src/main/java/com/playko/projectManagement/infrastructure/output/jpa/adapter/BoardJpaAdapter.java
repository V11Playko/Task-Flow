package com.playko.projectManagement.infrastructure.output.jpa.adapter;

import com.playko.projectManagement.domain.model.BoardModel;
import com.playko.projectManagement.domain.spi.IBoardPersistencePort;
import com.playko.projectManagement.infrastructure.exception.BoardColumnNotFoundException;
import com.playko.projectManagement.infrastructure.exception.BoardNotFoundException;
import com.playko.projectManagement.infrastructure.exception.InvalidBoardOperationException;
import com.playko.projectManagement.infrastructure.exception.ProjectNotFoundException;
import com.playko.projectManagement.infrastructure.exception.TaskNotFoundException;
import com.playko.projectManagement.infrastructure.output.jpa.entity.BoardColumnEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.BoardEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.ProjectEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.TaskEntity;
import com.playko.projectManagement.infrastructure.output.jpa.mapper.IBoardEntityMapper;
import com.playko.projectManagement.infrastructure.output.jpa.repository.IBoardColumnRepository;
import com.playko.projectManagement.infrastructure.output.jpa.repository.IBoardRepository;
import com.playko.projectManagement.infrastructure.output.jpa.repository.IProjectRepository;
import com.playko.projectManagement.infrastructure.output.jpa.repository.ITaskRepository;
import com.playko.projectManagement.shared.SecurityUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardJpaAdapter implements IBoardPersistencePort {
    private final IBoardRepository boardRepository;
    private final IBoardEntityMapper boardEntityMapper;
    private final ITaskRepository taskRepository;
    private final IBoardColumnRepository boardColumnRepository;
    private final IProjectRepository projectRepository;
    private final SecurityUtils securityUtils;

    @Override
    public BoardModel getBoardById(Long boardId) {
        BoardEntity board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);

        String correoAutenticado = securityUtils.obtenerCorreoDelToken();
        securityUtils.validarAccesoProyecto(board.getProject().getId(), correoAutenticado);

        return boardEntityMapper.toResponse(board);
    }

    @Override
    public void moveTask(Long taskId, Long targetColumnId) {
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
        BoardColumnEntity boardColumn = boardColumnRepository.findById(targetColumnId)
                .orElseThrow(BoardColumnNotFoundException::new);

        String correoAutenticado = securityUtils.obtenerCorreoDelToken();
        securityUtils.validarAccesoProyecto(taskEntity.getProject().getId(), correoAutenticado);

        if (!taskEntity.getBoardColumn().getBoard().getId().equals(boardColumn.getBoard().getId())) {
            throw new InvalidBoardOperationException();
        }


        taskEntity.setBoardColumn(boardColumn);
        taskRepository.save(taskEntity);
    }

    @Override
    public void saveBoard(BoardModel boardModel) {
        ProjectEntity project = projectRepository.findById(boardModel.getProject().getId())
                .orElseThrow(ProjectNotFoundException::new);

        String correoAutenticado = securityUtils.obtenerCorreoDelToken();
        securityUtils.validarAccesoProyecto(project.getId(), correoAutenticado);


        BoardEntity board = boardEntityMapper.toEntity(boardModel);
        board.setProject(project);

        boardRepository.save(board);
    }
}
