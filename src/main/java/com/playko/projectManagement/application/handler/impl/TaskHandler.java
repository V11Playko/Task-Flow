package com.playko.projectManagement.application.handler.impl;

import com.playko.projectManagement.application.dto.request.TaskRequestDto;
import com.playko.projectManagement.application.handler.ITaskHandler;
import com.playko.projectManagement.application.mapper.request.ITaskRequestMapper;
import com.playko.projectManagement.domain.api.ITaskServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskHandler implements ITaskHandler {
    private final ITaskServicePort taskServicePort;
    private final ITaskRequestMapper taskRequestMapper;
    @Override
    public void saveTask(TaskRequestDto taskRequestDto) {

    }
}
