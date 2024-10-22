package com.github.angel.raa.service.impl;

import com.github.angel.raa.dto.UserDTO;
import com.github.angel.raa.excpetion.ResourceException;
import com.github.angel.raa.persistence.entity.User;
import com.github.angel.raa.persistence.repository.UserRepository;
import com.github.angel.raa.service.UserService;
import com.github.angel.raa.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDateTime.now;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    private static UserDTO mapUserToDTO(@NotNull User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Fetching all users");
        return repository.findAllDto();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getUsersByName(String name) {
        log.info("Fetching users by name: {}", name);
        return repository.findByNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getUserById(Long id) {
        log.info("Fetching user by id: {}", id);
        return repository.findByIdDto(id).orElseThrow(() -> new ResourceException("User not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getUserByUsername(String username) {
        log.info("Fetching user by username: {}", username);
        return repository.findByUsernameDto(username).orElseThrow(() -> new ResourceException("User not found"));
    }

    @Transactional
    @Override
    public Response<UserDTO> createUser(User user) {
        log.info("Creating user: {}", user);
        User o = repository.persist(user);
        UserDTO dto = mapUserToDTO(o);
        return Response.<UserDTO>builder()
                .message("User created successfully")
                .code(201)
                .error(false)
                .status(HttpStatus.CREATED)
                .timestamp(now())
                .data(dto)
                .build();
    }

    @Transactional
    @Override
    public Response<UserDTO> updateUser(Long id, User user) {
        if (repository.existsById(id)) {
            user.setUserId(id);
            log.info("Updating user: {}", user);
            var o = repository.update(user);
            return Response.<UserDTO>builder()
                    .message("User updated successfully.")
                    .code(200)
                    .error(false)
                    .status(HttpStatus.OK)
                    .timestamp(now())
                    .data(mapUserToDTO(o))
                    .build();
        } else {
            throw new ResourceException("User not found");
        }
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            log.info("User with ID {} has been deleted", id);
            return;
        }
        throw new ResourceException("User not found");
    }

    @Transactional
    @Override
    public void deleteByUsername(String username) {
        int res = repository.deleteByUsername(username);
        if (res > 0) {
            log.info("User with username {} has been deleted", username);
        } else {
            throw new ResourceException("User not found");
        }

    }

    @Transactional
    @Override
    public Response<String> updateByUsername(String oldUsername, String newUsername) {
        int res = repository.updateByUsername(oldUsername, newUsername);
        if (res > 0) {
            return Response.<String>builder()
                    .message("Username updated successfully.")
                    .code(200)
                    .error(false)
                    .status(HttpStatus.OK)
                    .timestamp(now())
                    .data("Username updated successfully.")
                    .build();
        } else {
            throw new ResourceException("User not found");
        }
    }


}
