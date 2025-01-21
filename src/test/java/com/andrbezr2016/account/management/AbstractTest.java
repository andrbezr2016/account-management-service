package com.andrbezr2016.account.management;

import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

public abstract class AbstractTest {

    public static RedisContainer REDIS_CONTAINER = new RedisContainer(DockerImageName.parse("redis:latest"));

    @BeforeAll
    public static void setUp() {
        REDIS_CONTAINER.setPortBindings(List.of("6379:6379"));
        REDIS_CONTAINER.start();
    }

    @DynamicPropertySource
    private static void registerRedisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.host", REDIS_CONTAINER::getHost);
        registry.add("spring.redis.port", () -> REDIS_CONTAINER.getMappedPort(6379).toString());
    }

    @AfterAll
    public static void tearDown() {
        REDIS_CONTAINER.close();
    }
}
