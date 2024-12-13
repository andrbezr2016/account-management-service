package com.andrbezr2016.account.management.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.List;
import java.util.Map;

@Getter
@ConfigurationProperties(prefix = "account-management.validation")
public class SourcesConfig {

    @ConstructorBinding
    public SourcesConfig(Map<String, List<String>> sources) {
        this.sources = sources;
    }

    private final Map<String, List<String>> sources;
}
