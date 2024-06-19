package com.tasos.onesecret.core.usecases.secret.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Secret {
    private Long id;
    private String secret;
    private String slug;
    private LocalDateTime createdAt;
}
