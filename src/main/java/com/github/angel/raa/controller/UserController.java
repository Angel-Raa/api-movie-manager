package com.github.angel.raa.controller;

import com.github.angel.raa.dto.UserDTO;
import com.github.angel.raa.dto.ChangeUsernameDTO;
import com.github.angel.raa.persistence.entity.User;
import com.github.angel.raa.service.UserService;
import com.github.angel.raa.utils.Response;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserDTO>> getAllPage(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getAllPage(pageable));
    }

    //@GetMapping("/name")
    public ResponseEntity<List<UserDTO>> getUsersByName(@RequestParam String name) {
        return ResponseEntity.ok(userService.getUsersByName(name));
    }

    @GetMapping("/name")
    public ResponseEntity<Page<UserDTO>> getUserByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getUsersByName(name, pageable));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<Response<UserDTO>> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/up/{userId}")
    public ResponseEntity<Response<UserDTO>> updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(userId, user));
    }

    @PatchMapping("/up/{oldUsername}")
    public ResponseEntity<Response<String>> updateUsername(@PathVariable String oldUsername, @RequestBody @NotNull ChangeUsernameDTO newChangeUsernameDTO) {
        String username = newChangeUsernameDTO.username();
        return ResponseEntity.ok(userService.updateByUsername(oldUsername, username));
    }

    @DeleteMapping("/del/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/del/username/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable("username") String username) {
        userService.deleteByUsername(username);
        return ResponseEntity.status(204).build();
    }
}
