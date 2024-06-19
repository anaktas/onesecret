package com.tasos.onesecret.infra.db.repos;

import com.tasos.onesecret.infra.db.entities.SecretEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SecretRepo extends JpaRepository<SecretEntity, Long> {
    @Query("SELECT s FROM SecretEntity s WHERE s.slug = ?1")
    Optional<SecretEntity> findBySlug(String slug);

    @Modifying
    @Query("DELETE FROM SecretEntity s WHERE s.createdAt < ?1")
    void deleteAllBefore(LocalDateTime date);
}
