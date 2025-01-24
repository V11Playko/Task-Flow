package com.playko.projectManagement.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum TaskState {
    TO_DO,
    IN_PROGRESS,
    DONE
}