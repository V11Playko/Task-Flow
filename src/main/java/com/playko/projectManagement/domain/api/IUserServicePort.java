package com.playko.projectManagement.domain.api;

import com.playko.projectManagement.domain.model.UserModel;

import java.util.List;

public interface IUserServicePort {
    UserModel findByEmail(String email);
    List<UserModel> findAllUsers();
    void saveUser(UserModel userModel);
}
