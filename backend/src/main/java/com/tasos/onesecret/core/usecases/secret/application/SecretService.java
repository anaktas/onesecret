package com.tasos.onesecret.core.usecases.secret.application;

import com.tasos.onesecret.core.usecases.secret.domain.CreateSecretCommand;
import com.tasos.onesecret.core.usecases.secret.domain.Secret;
import com.tasos.onesecret.core.usecases.secret.ports.in.SecretUseCase;
import com.tasos.onesecret.core.usecases.secret.ports.out.SecretOutPort;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SecretService implements SecretUseCase {
    private final SecretOutPort port;

    @Transactional
    @Override
    public String createSecret(CreateSecretCommand command) {
        LocalDateTime now = LocalDateTime.now();
        return port.createSecret(Secret.builder()
                .secret(command.getSecret())
                .createdAt(now)
                .slug(createSlug(now))
                .build());
    }

    @Transactional(readOnly = true, noRollbackFor = Exception.class)
    @Override
    public Optional<Secret> findBySlug(String slug) {
        return port.findBySlug(slug);
    }

    @Transactional
    @Override
    public void deleteAllBefore(LocalDateTime date) {
        port.deleterAllBefore(date);
    }

    public String createSlug(LocalDateTime date) {
        return "secret-" + date.toEpochSecond(ZoneOffset.MAX);
    }
}
