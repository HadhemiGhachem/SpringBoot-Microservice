package com.example.coachserver.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "coaches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String speciality;

    private String email;

    private int user_id;
}
