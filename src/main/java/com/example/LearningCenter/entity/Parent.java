package com.example.LearningCenter.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Parent implements Serializable {
    @Id
    @SequenceGenerator(
            name = "parent_id_sequence",
            sequenceName = "parent_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="parent_id_sequence"
    )
    Long id;

    @Column(unique = true)
    String phoneNumber;

    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles;

}
