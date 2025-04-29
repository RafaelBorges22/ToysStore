package com.project.toys_store.dto.User;

import com.project.toys_store.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTo implements Serializable {
    private String name;
    private String email;
    private String password;
    private String photo;

    public UserDTo(UserModel userModel){
        this.setName(userModel.getName());
        this.setEmail(userModel.getEmail());
        this.setPassword(userModel.getPassword());
        if (!userModel.getFilePath().isEmpty()){
            this.setPhoto(userModel.getFilePath());
        }
    }
}
