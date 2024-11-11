package com.github.angel.raa.persistence.repository;

import com.github.angel.raa.dto.UserDTO;
import com.github.angel.raa.persistence.entity.User;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends ListPagingAndSortingRepository<User, Long>, BaseJpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);
    @Query("SELECT new com.github.angel.raa.dto.UserDTO(u.userId, u.username, u.name) FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<UserDTO> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query(value = """
            SELECT new com.github.angel.raa.dto.UserDTO(u.userId, u.username, u.name) FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """, countQuery = "SELECT COUNT(u) FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<UserDTO> findByNameContainingIgnoreCase(@Param("name")  String name, Pageable pageable);
    @Modifying
    @Query("UPDATE User u SET u.username = :newUsername WHERE u.username = :oldUsername")
    int updateByUsername(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername);
    @Modifying
    @Query("DELETE FROM User u WHERE u.username = :username")
    int deleteByUsername(@Param("username") String username);

    boolean existsByUsername(String username);
    @Query("SELECT new com.github.angel.raa.dto.UserDTO(u.userId, u.username, u.name) FROM User u")
    List<UserDTO> findAllDto();
    @Query("SELECT new com.github.angel.raa.dto.UserDTO(u.userId, u.username, u.name) FROM User u WHERE u.username = :username")
    Optional<UserDTO> findByUsernameDto(@Param("username") String username);
    @Query("SELECT new com.github.angel.raa.dto.UserDTO(u.userId, u.username, u.name) FROM User u WHERE u.userId = :id")
    Optional<UserDTO> findByIdDto(@Param("id") Long id);
    @Query(value = "SELECT new com.github.angel.raa.dto.UserDTO(u.userId, u.username, u.name) FROM User u", countQuery = " SELECT COUNT(u) FROM User u")
    Page<UserDTO> findAllPage(Pageable pageable);
}
