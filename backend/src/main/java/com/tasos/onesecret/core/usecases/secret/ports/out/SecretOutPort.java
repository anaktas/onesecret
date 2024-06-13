package com.tasos.onesecret.core.usecases.secret.ports.out;

import com.tasos.onesecret.core.usecases.secret.domain.Secret;
import java.time.LocalDateTime;
import java.util.Optional;

public interface SecretOutPort {
    String createSecret(Secret secret);

    Optional<Secret> findBySlug(String slug);

    void deleterAllBefore(LocalDateTime date);
}
