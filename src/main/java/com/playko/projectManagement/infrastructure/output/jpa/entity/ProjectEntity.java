package com.playko.projectManagement.infrastructure.output.jpa.entity;

import com.playko.projectManagement.shared.enums.ProjectState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "project")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalDate CreationDate;

    private LocalDate FinishedDate;

    @Enumerated(EnumType.STRING)
    private ProjectState state;

    private String owner;

    @OneToMany(mappedBy = "project")
    private List<BoardEntity> boards;

    @OneToMany(mappedBy = "project")
    private List<TaskEntity> tasks;
}
