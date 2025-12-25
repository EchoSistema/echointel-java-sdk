package com.echosistema.echointel;

import com.echosistema.echointel.config.EchoIntelConfig;
import com.echosistema.echointel.exceptions.EchoIntelAuthenticationException;
import com.echosistema.echointel.exceptions.EchoIntelException;
import com.echosistema.echointel.exceptions.EchoIntelValidationException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EchoIntelClientTest {

    private MockWebServer mockWebServer;
    private EchoIntelClient client;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        EchoIntelConfig config = EchoIntelConfig.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .customerApiId("test-customer-id")
                .secret("test-secret")
                .adminSecret("test-admin-secret")
                .timeout(10)
                .retryAttempts(1)
                .retryDelay(10)
                .build();

        client = new EchoIntelClient(config);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    // =========================================================================
    // BUILDER TESTS
    // =========================================================================

    @Test
    void testBuilderCreatesClient() {
        EchoIntelClient builtClient = EchoIntelClient.builder()
                .baseUrl("https://test.example.com")
                .customerApiId("customer-123")
                .secret("secret-456")
                .adminSecret("admin-789")
                .timeout(60)
                .retryAttempts(5)
                .retryDelay(200)
                .build();

        assertNotNull(builtClient);
    }

    // =========================================================================
    // HEALTH CHECK TESTS
    // =========================================================================

    @Test
    void testHealthReturnsSuccessResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"status\":\"healthy\",\"version\":\"1.0.0\"}"));

        Map<String, Object> result = client.health();

        assertEquals("healthy", result.get("status"));
        assertEquals("1.0.0", result.get("version"));

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/health", request.getPath());
    }

    // =========================================================================
    // FORECASTING TESTS
    // =========================================================================

    @Test
    void testForecastRevenuePostsData() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"forecast\":[{\"date\":\"2024-01-01\",\"value\":1000}]}"));

        Map<String, Object> data = new HashMap<>();
        data.put("product_id", "prod-123");
        data.put("periods", 30);

        Map<String, Object> result = client.forecastRevenue(data);

        assertNotNull(result.get("forecast"));

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/api/forecast_revenue", request.getPath());
        assertTrue(request.getBody().readUtf8().contains("prod-123"));
    }

    @Test
    void testForecastCostPostsData() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"forecast\":[]}"));

        Map<String, Object> data = new HashMap<>();
        data.put("category", "electronics");

        Map<String, Object> result = client.forecastCost(data);

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/api/forecast_cost", request.getPath());
    }

    @Test
    void testForecastUnitsPostsData() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"results\":[]}"));

        Map<String, Object> result = client.forecastUnits(Collections.emptyMap());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/api/forecast_units", request.getPath());
    }

    // =========================================================================
    // CUSTOMER ANALYTICS TESTS
    // =========================================================================

    @Test
    void testCustomerSegmentationPostsData() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"segments\":[{\"id\":1,\"name\":\"VIP\"}]}"));

        Map<String, Object> data = new HashMap<>();
        data.put("customers", Arrays.asList("c1", "c2", "c3"));

        Map<String, Object> result = client.customerSegmentation(data);

        assertNotNull(result.get("segments"));

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/api/customer_segmentation", request.getPath());
    }

    @Test
    void testCustomerRfmPostsData() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"customers\":[{\"customer_id\":\"c1\",\"rfm_tier\":\"Gold\"}]}"));

        Map<String, Object> result = client.customerRfm(Collections.emptyMap());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/api/customer_rfm", request.getPath());
    }

    // =========================================================================
    // ADMIN OPERATIONS TESTS
    // =========================================================================

    @Test
    void testCreateCustomerWithAdminSecret() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"id\":\"new-customer-id\",\"name\":\"Test Customer\"}"));

        Map<String, Object> data = new HashMap<>();
        data.put("name", "Test Customer");
        data.put("email", "test@example.com");

        Map<String, Object> result = client.createCustomer(data);

        assertEquals("new-customer-id", result.get("id"));

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/admin/customers", request.getPath());
        assertEquals("test-admin-secret", request.getHeader("X-ADMIN-SECRET"));
    }

    @Test
    void testCreateCustomerResolvesRoutes() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"id\":\"cust-1\"}"));

        Map<String, Object> data = new HashMap<>();
        data.put("name", "Test");
        data.put("allowed_routes", Arrays.asList("forecasting"));

        client.createCustomer(data);

        RecordedRequest request = mockWebServer.takeRequest();
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("/api/forecast_revenue"));
    }

    @Test
    void testListCustomersReturnsData() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"customers\":[{\"id\":\"c1\"},{\"id\":\"c2\"}]}"));

        Map<String, Object> result = client.listCustomers();

        assertNotNull(result.get("customers"));

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/admin/customers", request.getPath());
    }

    @Test
    void testListCustomersIncludeDisabled() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"customers\":[]}"));

        client.listCustomers(true);

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/admin/customers?include_disabled=true", request.getPath());
    }

    @Test
    void testGetCustomerById() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"id\":\"cust-123\",\"name\":\"Test\"}"));

        Map<String, Object> result = client.getCustomer("cust-123");

        assertEquals("cust-123", result.get("id"));

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/admin/customers/cust-123", request.getPath());
    }

    @Test
    void testUpdateCustomer() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"id\":\"cust-123\",\"name\":\"Updated\"}"));

        Map<String, Object> data = new HashMap<>();
        data.put("name", "Updated");

        Map<String, Object> result = client.updateCustomer("cust-123", data);

        assertEquals("Updated", result.get("name"));

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/admin/customers/cust-123", request.getPath());
    }

    @Test
    void testDeleteCustomer() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"deleted\":true}"));

        Map<String, Object> result = client.deleteCustomer("cust-123");

        assertEquals(true, result.get("deleted"));

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/admin/customers/cust-123", request.getPath());
    }

    // =========================================================================
    // AUTH HEADERS TESTS
    // =========================================================================

    @Test
    void testWithAuthSendsAllHeaders() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{}"));

        client.purchasingSegmentation(Collections.emptyMap());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("test-admin-secret", request.getHeader("X-ADMIN-SECRET"));
        assertEquals("test-customer-id", request.getHeader("X-Customer-Api-Id"));
        assertEquals("test-secret", request.getHeader("X-Secret"));
    }

    // =========================================================================
    // ERROR HANDLING TESTS
    // =========================================================================

    @Test
    void testAuthenticationErrorThrowsException() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(401)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"detail\":\"Invalid API key\"}"));

        EchoIntelAuthenticationException exception = assertThrows(
                EchoIntelAuthenticationException.class,
                () -> client.health()
        );

        assertEquals(401, exception.getStatusCode());
        assertTrue(exception.getMessage().contains("Invalid API key"));
    }

    @Test
    void testForbiddenErrorThrowsAuthenticationException() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(403)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"detail\":\"Access denied\"}"));

        EchoIntelAuthenticationException exception = assertThrows(
                EchoIntelAuthenticationException.class,
                () -> client.health()
        );

        assertEquals(403, exception.getStatusCode());
    }

    @Test
    void testValidationErrorThrowsException() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(422)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"detail\":\"Validation failed\",\"errors\":[{\"field\":\"email\",\"message\":\"Invalid\"}]}"));

        EchoIntelValidationException exception = assertThrows(
                EchoIntelValidationException.class,
                () -> client.createCustomer(Collections.emptyMap())
        );

        assertEquals(422, exception.getStatusCode());
        assertFalse(exception.getErrors().isEmpty());
    }

    @Test
    void testServerErrorThrowsException() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"detail\":\"Internal server error\"}"));

        EchoIntelException exception = assertThrows(
                EchoIntelException.class,
                () -> client.health()
        );

        assertEquals(500, exception.getStatusCode());
    }

    // =========================================================================
    // VARIOUS ENDPOINT TESTS
    // =========================================================================

    @Test
    void testSentimentReportEndpoint() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{}"));

        client.sentimentReport(Collections.emptyMap());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/api/sentiment_report", request.getPath());
    }

    @Test
    void testAnomalyTransactionsEndpoint() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{}"));

        client.anomalyTransactions(Collections.emptyMap());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/api/anomaly_transactions", request.getPath());
    }

    @Test
    void testCreditRiskScoreEndpoint() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{}"));

        client.creditRiskScore(Collections.emptyMap());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/api/credit_risk_score", request.getPath());
    }

    @Test
    void testJourneyMarkovEndpoint() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{}"));

        client.journeyMarkov(Collections.emptyMap());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/api/journey_markov", request.getPath());
    }

    @Test
    void testNlpAnalysisEndpoint() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{}"));

        client.nlpAnalysis(Collections.emptyMap());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/api/nlp_analisys", request.getPath());
    }

    @Test
    void testSegmentationReportI18nWithLang() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{}"));

        client.segmentationReportI18n(Collections.emptyMap(), "en");

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/api/segmentation_report_i18n?lang=en", request.getPath());
    }

    @Test
    void testSegmentationReportJsonWithDefaultLang() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{}"));

        client.segmentationReportJson(Collections.emptyMap());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/api/segmentation_report_json?lang=en", request.getPath());
    }
}
