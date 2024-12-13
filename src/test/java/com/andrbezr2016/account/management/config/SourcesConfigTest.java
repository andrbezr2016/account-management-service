package com.andrbezr2016.account.management.config;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SourcesConfigTest {

    @Test
    void sourcesConfigValid1Test() {
        SourcesConfig sourcesConfig = new SourcesConfig("key1=value11");

        Map<String, List<String>> sources = sourcesConfig.getSources();
        assertEquals(1, sources.size());
        assertEquals(1, sources.get("key1").size());
    }

    @Test
    void sourcesConfigValid2Test() {
        SourcesConfig sourcesConfig = new SourcesConfig("key1=value11,value12,value13;key2=value21,value22");

        Map<String, List<String>> sources = sourcesConfig.getSources();
        assertEquals(2, sources.size());
        assertEquals(3, sources.get("key1").size());
        assertEquals(2, sources.get("key2").size());
    }

    @Test
    void sourcesConfigValid3Test() {
        SourcesConfig sourcesConfig = new SourcesConfig("key1=value11;key2=value21");

        Map<String, List<String>> sources = sourcesConfig.getSources();
        assertEquals(2, sources.size());
        assertEquals(1, sources.get("key1").size());
        assertEquals(1, sources.get("key2").size());
    }
}
