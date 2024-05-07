package com.example.LearningCenter.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    String firstName;

    String lastName;

    @Column(unique=true)
    String email;

    LocalDateTime dateOfBirth;

    // MALE or FEMALE
    String gender;

    String address;

    @OneToOne
    Attachment file;

    @OneToOne
    Parent parent;
}
