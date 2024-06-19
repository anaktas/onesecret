package com.tasos.onesecret.infra.db.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "secret")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class SecretEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "secret")
    private String secret;

    @Column(name = "slug")
    private String slug;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
