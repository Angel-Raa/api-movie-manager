package com.github.angel.raa.configuration;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
        basePackages = "com.github.angel.raa.persistence.repository",
        value = {"io.hypersistence.utils.spring.repository", "com.github.angel.raa.persistence.repository" },
        repositoryBaseClass = BaseJpaRepositoryImpl.class
)
@Configuration
public class JpaConfiguration {

}
