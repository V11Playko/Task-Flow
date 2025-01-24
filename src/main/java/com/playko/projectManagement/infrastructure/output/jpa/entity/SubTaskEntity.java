package com.playko.projectManagement.infrastructure.output.jpa.entity;

import com.playko.projectManagement.shared.enums.SubTaskState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "subTask")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private SubTaskState state;
    private LocalDate limitDate;
    private String projectAssociate;
}
