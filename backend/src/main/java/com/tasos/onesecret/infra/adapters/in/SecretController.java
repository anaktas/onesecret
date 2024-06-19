package com.tasos.onesecret.infra.adapters.in;

import com.tasos.onesecret.core.usecases.secret.domain.CreateSecretCommand;
import com.tasos.onesecret.core.usecases.secret.ports.in.SecretUseCase;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SecretController {
    private final SecretUseCase useCase;

    @CrossOrigin
    @PostMapping(
            value = "/api/secrets",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateSecretResponseDto> createSecret(@RequestBody CreateSecretRequestDto request) {
        return ResponseEntity.ok(new CreateSecretResponseDto(useCase.createSecret(
                CreateSecretCommand.builder().secret(request.secret()).build())));
    }

    @CrossOrigin
    @GetMapping(value = "/api/secrets/{slug}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetSecretResponseDto> findSecretBySlug(@PathVariable(value = "slug") String slug) {
        return ResponseEntity.of(useCase.findBySlug(slug)
                .map(secret -> new GetSecretResponseDto(
                        secret.getId(), secret.getSecret(), secret.getSlug(), secret.getCreatedAt())));
    }

    record CreateSecretRequestDto(String secret) {}

    record CreateSecretResponseDto(String slug) {}

    record GetSecretResponseDto(Long id, String secret, String slug, LocalDateTime createdAt) {}
}
