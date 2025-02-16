package com.playko.projectManagement.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskReassignmentRequestDto {
    private Long taskId;
    private Long newUserId;
}