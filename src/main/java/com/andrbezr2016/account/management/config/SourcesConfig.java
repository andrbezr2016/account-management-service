package com.andrbezr2016.account.management.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@ConfigurationProperties(prefix = "account-management.validation")
public class SourcesConfig {

    @ConstructorBinding
    public SourcesConfig(String sources) {
        this.sources = new HashMap<>();
        try {
            String[] entities = sources.split(";");
            for (String entity : entities) {
                String[] keyValuesPair = entity.split("=");
                String key = keyValuesPair[0];
                String[] values = keyValuesPair[1].split(",");
                this.sources.put(key, Arrays.asList(values));
            }
        } catch (Exception exception) {
            this.sources.clear();
            throw new RuntimeException("Something went wrong during SOURCES property parsing");
        }
        log.info("Sources was loaded: {}", this.sources);
    }

    private final Map<String, List<String>> sources;
}
