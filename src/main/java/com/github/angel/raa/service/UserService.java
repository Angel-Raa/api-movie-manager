package com.github.angel.raa.service;

import com.github.angel.raa.dto.UserDTO;
import com.github.angel.raa.persistence.entity.User;
import com.github.angel.raa.utils.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interface for user service.
 */
public interface UserService {
    /**
     * Get all users.
     * @return a list of users.
     */
    List<UserDTO> getAllUsers();

    /**
     * Get all users with pagination.
     * @param pageable
     * @return
     */
    Page<UserDTO> getAllPage(Pageable pageable);
    /**
     * Get users by name.
     * @param name the name of the user.
     * @return a list of users.
     */
    List<UserDTO> getUsersByName(String name);
    /**
     * Get users by name with pagination.
     * @param name the name of the user.
     * @param pageable the pagination.
     * @return a list of users.
     */
    Page<UserDTO> getUsersByName(String name, Pageable pageable);
    /**
     * Get user by id.
     * @param id the id of the user.
     * @return the user.
     */
    UserDTO getUserById(Long id);
    /**
     * Get user by username.
     * @param username the username of the user.
     * @return the user.
     */
    UserDTO getUserByUsername(String username);
    /**
     * Create a user.
     * @param user the user to create.
     * @return the created user.
     */
    Response<UserDTO> createUser(User user);
    /**
     * Update a user.
     * @param id the id of the user to update.
     * @param user the user to update.
     * @return the updated user.
     */
    Response<UserDTO> updateUser(Long id, User user);
    /**
     * Delete a user by id.
     * @param id the id of the user to delete.
     */
    void deleteUser(Long id);
    /**
     * Delete a user by username.
     * @param username the username of the user to delete.
     *
     */
    void deleteByUsername(String username);
    /**
     * Update a user by username.
     * @param oldUsername the old username of the user to update.
     * @param newUsername the new username of the user to update.
     * @return the updated user.
     */
    Response<String> updateByUsername(String oldUsername, String newUsername);




}
