package com.tasos.onesecret.infra.config;

import com.tasos.onesecret.core.usecases.secret.ports.in.SecretUseCase;
import com.tasos.onesecret.infra.adapters.in.DeleteAllSecretsService;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class SchedulingConfiguration implements SchedulingConfigurer {
    private final Executor executor;

    public SchedulingConfiguration(@Qualifier("mainExecutor") Executor executor) {
        this.executor = executor;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(executor);
    }

    @Bean
    public DeleteAllSecretsService deleteAllSecretsService(SecretUseCase secretUseCase) {
        return new DeleteAllSecretsService(secretUseCase);
    }
}
