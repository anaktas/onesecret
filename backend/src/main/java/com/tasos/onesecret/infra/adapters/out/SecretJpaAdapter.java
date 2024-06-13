package com.tasos.onesecret.infra.adapters.out;

import com.tasos.onesecret.core.usecases.secret.domain.Secret;
import com.tasos.onesecret.core.usecases.secret.ports.out.SecretOutPort;
import com.tasos.onesecret.infra.db.entities.SecretEntity;
import com.tasos.onesecret.infra.db.repos.SecretRepo;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SecretJpaAdapter implements SecretOutPort {
    private final SecretRepo repo;

    @Override
    public String createSecret(Secret secret) {
        var saved = repo.save(new SecretEntity()
                .setSecret(secret.getSecret())
                .setSlug(secret.getSlug())
                .setCreatedAt(secret.getCreatedAt()));
        return saved.getSlug();
    }

    @Override
    public Optional<Secret> findBySlug(String slug) {
        return repo.findBySlug(slug).map(s -> Secret.builder()
                .id(s.getId())
                .secret(s.getSecret())
                .slug(s.getSlug())
                .createdAt(s.getCreatedAt())
                .build());
    }

    @Override
    public void deleterAllBefore(LocalDateTime date) {
        repo.deleteAllBefore(date);
    }
}
