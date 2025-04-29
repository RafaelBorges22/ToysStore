package com.project.toys_store.dto.User;

import com.project.toys_store.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertUserDTo implements Serializable {
    private String name;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String email;
    private String password;
    private MultipartFile photo;
}
