package com.echosistema.echointel;

import com.echosistema.echointel.config.EchoIntelConfig;

public class EchoIntelClientBuilder {

    private String baseUrl = EchoIntelConfig.Defaults.BASE_URL;
    private String customerApiId;
    private String secret;
    private String adminSecret;
    private int timeout = EchoIntelConfig.Defaults.TIMEOUT;
    private int retryAttempts = EchoIntelConfig.Defaults.RETRY_ATTEMPTS;
    private int retryDelay = EchoIntelConfig.Defaults.RETRY_DELAY;

    public EchoIntelClientBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public EchoIntelClientBuilder customerApiId(String customerApiId) {
        this.customerApiId = customerApiId;
        return this;
    }

    public EchoIntelClientBuilder secret(String secret) {
        this.secret = secret;
        return this;
    }

    public EchoIntelClientBuilder adminSecret(String adminSecret) {
        this.adminSecret = adminSecret;
        return this;
    }

    public EchoIntelClientBuilder timeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public EchoIntelClientBuilder retryAttempts(int retryAttempts) {
        this.retryAttempts = retryAttempts;
        return this;
    }

    public EchoIntelClientBuilder retryDelay(int retryDelay) {
        this.retryDelay = retryDelay;
        return this;
    }

    public EchoIntelClient build() {
        EchoIntelConfig config = EchoIntelConfig.builder()
                .baseUrl(baseUrl)
                .customerApiId(customerApiId)
                .secret(secret)
                .adminSecret(adminSecret)
                .timeout(timeout)
                .retryAttempts(retryAttempts)
                .retryDelay(retryDelay)
                .build();

        return new EchoIntelClient(config);
    }
}
