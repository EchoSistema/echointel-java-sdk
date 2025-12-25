package com.echosistema.echointel.config;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EchoIntelConfig {

    @Builder.Default
    private String baseUrl = Defaults.BASE_URL;

    private String customerApiId;

    private String secret;

    private String adminSecret;

    @Builder.Default
    private int timeout = Defaults.TIMEOUT;

    @Builder.Default
    private int retryAttempts = Defaults.RETRY_ATTEMPTS;

    @Builder.Default
    private int retryDelay = Defaults.RETRY_DELAY;

    public static final class Defaults {
        public static final String BASE_URL = "https://ai.echosistema.live";
        public static final int TIMEOUT = 30;
        public static final int RETRY_ATTEMPTS = 3;
        public static final int RETRY_DELAY = 100;

        private Defaults() {
        }
    }
}
