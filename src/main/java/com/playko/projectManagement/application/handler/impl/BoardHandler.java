package com.playko.projectManagement.application.handler.impl;

import com.playko.projectManagement.application.dto.response.BoardResponseDto;
import com.playko.projectManagement.application.handler.IBoardHandler;
import com.playko.projectManagement.application.mapper.response.IBoardResponseMapper;
import com.playko.projectManagement.domain.api.IBoardServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardHandler implements IBoardHandler {
    private final IBoardServicePort boardServicePort;
    private final IBoardResponseMapper boardResponseMapper;

    @Override
    public BoardResponseDto getBoardById(Long boardId) {
        return boardResponseMapper.toResponse(boardServicePort.getBoardById(boardId));
    }
}
