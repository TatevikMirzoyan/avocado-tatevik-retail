package com.avocado.tatevik.retail.repository.user;

import com.avocado.tatevik.retail.repository.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> findByUsername(@Param("username") String username);

    @Modifying
    @Query(value = "UPDATE UserEntity SET deleted = 'true' WHERE id = :id")
    void updateDeleted(@Param("id") Long id);
}
