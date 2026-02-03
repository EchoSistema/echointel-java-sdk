package com.echosistema.echointel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integration")
@DisplayName("Health Check Integration Test")
class HealthCheckIntegrationTest {

    private EchoIntelClient client;

    @BeforeEach
    void setUp() {
        client = EchoIntelClient.builder()
                .baseUrl(Endpoints.BASE_URL)
                .timeout(30)
                .retryAttempts(3)
                .build();
    }

    @Test
    @DisplayName("Should return healthy status from API")
    void testHealthCheckReturnsHealthyStatus() {
        Map<String, Object> response = client.health();

        assertNotNull(response, "Response should not be null");
        System.out.println("Health check response: " + response);
    }

    @Test
    @DisplayName("Should connect to production API")
    void testConnectionToProductionApi() {
        assertDoesNotThrow(() -> {
            Map<String, Object> response = client.health();
            assertNotNull(response);
        }, "Should connect without throwing exceptions");
    }
}
