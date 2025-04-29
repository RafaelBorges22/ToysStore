package com.project.toys_store.model;

import com.project.toys_store.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_users")

public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private UserRole userRole;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String filePath;
}

