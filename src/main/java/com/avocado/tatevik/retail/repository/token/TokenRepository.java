package com.avocado.tatevik.retail.repository.token;

import com.avocado.tatevik.retail.repository.token.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    @Query("SELECT t FROM TokenEntity t WHERE t.token = :token")
    TokenEntity findByToken(@Param("token") String token);

    @Query(value = "SELECT t FROM TokenEntity t WHERE t.userId = :userId")
    List<TokenEntity> findAllByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM TokenEntity t WHERE t.userId = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM TokenEntity t WHERE t.token = (:token)")
    void deleteAllByToken(@Param("token") String token);
}
