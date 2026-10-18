package com.godbyul.modern_api.user.service;

import com.godbyul.modern_api.user.dto.CreateUserRequest;
import com.godbyul.modern_api.user.dto.UpdateUserRequest;
import com.godbyul.modern_api.user.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse getUser(Long userId);

    Page<UserResponse> getUsers(Pageable pageable);

    UserResponse updateUser(Long userId, UpdateUserRequest request);

    void deleteUser(Long userId);
}
