package com.godbyul.modern_api.user.service;

import com.godbyul.modern_api.global.exception.BusinessException;
import com.godbyul.modern_api.global.exception.ErrorCode;
import com.godbyul.modern_api.user.domain.User;
import com.godbyul.modern_api.user.dto.CreateUserRequest;
import com.godbyul.modern_api.user.dto.UpdateUserRequest;
import com.godbyul.modern_api.user.dto.UserResponse;
import com.godbyul.modern_api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        validateDuplicateEmail(request.getEmail());

        User user = User.create(request.getName(), request.getEmail());
        User savedUser = userRepository.save(user);

        return UserResponse.from(savedUser);
    }

    @Override
    public UserResponse getUser(Long userId) {
        User user = findUserById(userId);
        return UserResponse.from(user);
    }

    @Override
    public Page<UserResponse> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserResponse::from);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long userId, UpdateUserRequest request) {
        User user = findUserById(userId);
        user.updateName(request.getName());

        return UserResponse.from(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = findUserById(userId);
        userRepository.delete(user);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    private void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.USER_EMAIL_ALREADY_EXISTS);
        }
    }
}
