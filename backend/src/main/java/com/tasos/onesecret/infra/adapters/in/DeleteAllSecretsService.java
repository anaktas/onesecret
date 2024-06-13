package com.tasos.onesecret.infra.adapters.in;

import com.tasos.onesecret.core.usecases.secret.ports.in.SecretUseCase;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
public class DeleteAllSecretsService {
    private final SecretUseCase useCase;

    @Async
    @Scheduled(fixedRate = 5 * 60 * 1_000)
    public void deleteAllSecretsBeforeDate() {
        log.info("The time of the purge...");
        useCase.deleteAllBefore(LocalDateTime.now().minusMinutes(5L));
    }
}
