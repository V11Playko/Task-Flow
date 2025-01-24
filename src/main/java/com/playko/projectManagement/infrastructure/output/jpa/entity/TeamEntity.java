package com.playko.projectManagement.infrastructure.output.jpa.entity;

import com.playko.projectManagement.shared.enums.TeamState;
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
@Table(name = "team")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalDate CreationDate;

    @Enumerated(EnumType.STRING)
    private TeamState state;
}
