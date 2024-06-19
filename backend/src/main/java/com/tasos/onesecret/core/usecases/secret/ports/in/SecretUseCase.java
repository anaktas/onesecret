package com.tasos.onesecret.core.usecases.secret.ports.in;

import com.tasos.onesecret.core.usecases.secret.domain.CreateSecretCommand;
import com.tasos.onesecret.core.usecases.secret.domain.Secret;
import java.time.LocalDateTime;
import java.util.Optional;

public interface SecretUseCase {
    String createSecret(CreateSecretCommand command);

    Optional<Secret> findBySlug(String slug);

    void deleteAllBefore(LocalDateTime date);
}
