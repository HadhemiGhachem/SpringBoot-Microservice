package com.example.seanceserver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "seances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    // Référence vers Coach microservice
    private Long coachId;

    @ElementCollection
    @CollectionTable(
            name = "seance_members",
            joinColumns = @JoinColumn(name = "seance_id")
    )
    @Column(name = "member_id")
    private Set<Long> memberIds;

}