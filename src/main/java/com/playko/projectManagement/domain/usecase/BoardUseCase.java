package com.playko.projectManagement.domain.usecase;

import com.playko.projectManagement.domain.api.IBoardServicePort;
import com.playko.projectManagement.domain.model.BoardModel;
import com.playko.projectManagement.domain.spi.IBoardPersistencePort;

public class BoardUseCase implements IBoardServicePort {
    private final IBoardPersistencePort boardPersistencePort;

    public BoardUseCase(IBoardPersistencePort boardPersistencePort) {
        this.boardPersistencePort = boardPersistencePort;
    }

    @Override
    public BoardModel getBoardById(Long boardId) {
        return boardPersistencePort.getBoardById(boardId);
    }
}
