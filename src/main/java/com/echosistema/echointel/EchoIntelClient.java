package com.echosistema.echointel;

import com.echosistema.echointel.config.EchoIntelConfig;
import com.echosistema.echointel.exceptions.EchoIntelAuthenticationException;
import com.echosistema.echointel.exceptions.EchoIntelException;
import com.echosistema.echointel.exceptions.EchoIntelValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class EchoIntelClient {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final OkHttpClient httpClient;
    private final String baseUrl;
    private final String customerApiId;
    private final String secret;
    private final String adminSecret;
    private final int retryAttempts;
    private final int retryDelay;

    public EchoIntelClient(EchoIntelConfig config) {
        this.baseUrl = config.getBaseUrl().replaceAll("/+$", "");
        this.customerApiId = config.getCustomerApiId();
        this.secret = config.getSecret();
        this.adminSecret = config.getAdminSecret();
        this.retryAttempts = config.getRetryAttempts();
        this.retryDelay = config.getRetryDelay();

        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(config.getTimeout(), TimeUnit.SECONDS)
                .readTimeout(config.getTimeout(), TimeUnit.SECONDS)
                .writeTimeout(config.getTimeout(), TimeUnit.SECONDS)
                .build();
    }

    public static EchoIntelClientBuilder builder() {
        return new EchoIntelClientBuilder();
    }

    // =========================================================================
    // SYSTEM
    // =========================================================================

    public Map<String, Object> health() {
        return request("GET", Endpoints.HEALTH, null, false);
    }

    // =========================================================================
    // FORECASTING
    // =========================================================================

    public Map<String, Object> forecastRevenue(Map<String, Object> data) {
        return request("POST", Endpoints.FORECAST_REVENUE, data, false);
    }

    public Map<String, Object> forecastCost(Map<String, Object> data) {
        return request("POST", Endpoints.FORECAST_COST, data, false);
    }

    public Map<String, Object> forecastCostImproved(Map<String, Object> data) {
        return request("POST", Endpoints.FORECAST_COST_IMPROVED, data, false);
    }

    public Map<String, Object> forecastUnits(Map<String, Object> data) {
        return request("POST", Endpoints.FORECAST_UNITS, data, false);
    }

    public Map<String, Object> forecastCostTotus(Map<String, Object> data) {
        return request("POST", Endpoints.FORECAST_COST_TOTUS, data, false);
    }

    // =========================================================================
    // INVENTORY
    // =========================================================================

    public Map<String, Object> inventoryOptimization(Map<String, Object> data) {
        return request("POST", Endpoints.INVENTORY_OPTIMIZATION, data, false);
    }

    public Map<String, Object> inventoryHistoryImproved(Map<String, Object> data) {
        return request("POST", Endpoints.INVENTORY_HISTORY_IMPROVED, data, false);
    }

    // =========================================================================
    // CUSTOMER ANALYTICS
    // =========================================================================

    public Map<String, Object> customerSegmentation(Map<String, Object> data) {
        return request("POST", Endpoints.CUSTOMER_SEGMENTATION, data, false);
    }

    public Map<String, Object> customerFeatures(Map<String, Object> data) {
        return request("POST", Endpoints.CUSTOMER_FEATURES, data, false);
    }

    public Map<String, Object> customerLoyalty(Map<String, Object> data) {
        return request("POST", Endpoints.CUSTOMER_LOYALTY, data, false);
    }

    public Map<String, Object> customerRfm(Map<String, Object> data) {
        return request("POST", Endpoints.CUSTOMER_RFM, data, false);
    }

    public Map<String, Object> customerClvFeatures(Map<String, Object> data) {
        return request("POST", Endpoints.CUSTOMER_CLV_FEATURES, data, false);
    }

    public Map<String, Object> customerClvForecast(Map<String, Object> data) {
        return request("POST", Endpoints.CUSTOMER_CLV_FORECAST, data, false);
    }

    // =========================================================================
    // CHURN ANALYSIS
    // =========================================================================

    public Map<String, Object> churnRisk(Map<String, Object> data) {
        return request("POST", Endpoints.CHURN_RISK, data, false);
    }

    public Map<String, Object> churnLabel(Map<String, Object> data) {
        return request("POST", Endpoints.CHURN_LABEL, data, false);
    }

    // =========================================================================
    // NPS
    // =========================================================================

    public Map<String, Object> nps(Map<String, Object> data) {
        return request("POST", Endpoints.NPS, data, false);
    }

    // =========================================================================
    // PROPENSITY MODELING
    // =========================================================================

    public Map<String, Object> propensityBuyProduct(Map<String, Object> data) {
        return request("POST", Endpoints.PROPENSITY_BUY_PRODUCT, data, false);
    }

    public Map<String, Object> propensityRespondCampaign(Map<String, Object> data) {
        return request("POST", Endpoints.PROPENSITY_RESPOND_CAMPAIGN, data, false);
    }

    public Map<String, Object> propensityUpgradePlan(Map<String, Object> data) {
        return request("POST", Endpoints.PROPENSITY_UPGRADE_PLAN, data, false);
    }

    // =========================================================================
    // RECOMMENDATIONS
    // =========================================================================

    public Map<String, Object> recommendUserItems(Map<String, Object> data) {
        return request("POST", Endpoints.RECOMMEND_USER_ITEMS, data, false);
    }

    public Map<String, Object> recommendSimilarItems(Map<String, Object> data) {
        return request("POST", Endpoints.RECOMMEND_SIMILAR_ITEMS, data, false);
    }

    // =========================================================================
    // CROSS-SELL & UPSELL
    // =========================================================================

    public Map<String, Object> crossSellMatrix(Map<String, Object> data) {
        return request("POST", Endpoints.CROSS_SELL_MATRIX, data, false);
    }

    public Map<String, Object> upsellSuggestions(Map<String, Object> data) {
        return request("POST", Endpoints.UPSELL_SUGGESTIONS, data, false);
    }

    // =========================================================================
    // DYNAMIC PRICING
    // =========================================================================

    public Map<String, Object> dynamicPricingRecommend(Map<String, Object> data) {
        return request("POST", Endpoints.DYNAMIC_PRICING_RECOMMEND, data, false);
    }

    // =========================================================================
    // SENTIMENT ANALYSIS
    // =========================================================================

    public Map<String, Object> sentimentReport(Map<String, Object> data) {
        return request("POST", Endpoints.SENTIMENT_REPORT, data, false);
    }

    public Map<String, Object> sentimentRealtime(Map<String, Object> data) {
        return request("POST", Endpoints.SENTIMENT_REALTIME, data, false);
    }

    // =========================================================================
    // ANOMALY DETECTION
    // =========================================================================

    public Map<String, Object> anomalyTransactions(Map<String, Object> data) {
        return request("POST", Endpoints.ANOMALY_TRANSACTIONS, data, false);
    }

    public Map<String, Object> anomalyAccounts(Map<String, Object> data) {
        return request("POST", Endpoints.ANOMALY_ACCOUNTS, data, false);
    }

    public Map<String, Object> anomalyGraph(Map<String, Object> data) {
        return request("POST", Endpoints.ANOMALY_GRAPH, data, false);
    }

    // =========================================================================
    // CREDIT RISK
    // =========================================================================

    public Map<String, Object> creditRiskScore(Map<String, Object> data) {
        return request("POST", Endpoints.CREDIT_RISK_SCORE, data, false);
    }

    public Map<String, Object> creditRiskExplain(Map<String, Object> data) {
        return request("POST", Endpoints.CREDIT_RISK_EXPLAIN, data, false);
    }

    // =========================================================================
    // MARKETING ATTRIBUTION
    // =========================================================================

    public Map<String, Object> channelAttribution(Map<String, Object> data) {
        return request("POST", Endpoints.CHANNEL_ATTRIBUTION, data, false);
    }

    public Map<String, Object> upliftModel(Map<String, Object> data) {
        return request("POST", Endpoints.UPLIFT_MODEL, data, false);
    }

    // =========================================================================
    // CUSTOMER JOURNEY
    // =========================================================================

    public Map<String, Object> journeyMarkov(Map<String, Object> data) {
        return request("POST", Endpoints.JOURNEY_MARKOV, data, false);
    }

    public Map<String, Object> journeySequences(Map<String, Object> data) {
        return request("POST", Endpoints.JOURNEY_SEQUENCES, data, false);
    }

    // =========================================================================
    // NLP & TEXT PROCESSING
    // =========================================================================

    public Map<String, Object> nlpAnalysis(Map<String, Object> data) {
        return request("POST", Endpoints.NLP_ANALYSIS, data, false);
    }

    public Map<String, Object> nlpAnalysisEn(Map<String, Object> data) {
        return request("POST", Endpoints.NLP_ANALYSIS_EN, data, false);
    }

    public Map<String, Object> nlpExcessInventoryReport(Map<String, Object> data) {
        return request("POST", Endpoints.NLP_EXCESS_INVENTORY_REPORT, data, false);
    }

    public Map<String, Object> sanitizeText(Map<String, Object> data) {
        return request("POST", Endpoints.SANITIZE_TEXT, data, false);
    }

    // =========================================================================
    // ADVANCED SEGMENTATION (ADMIN)
    // =========================================================================

    public Map<String, Object> purchasingSegmentation(Map<String, Object> data) {
        return request("POST", Endpoints.PURCHASING_SEGMENTATION, data, true);
    }

    public Map<String, Object> purchasingSegmentationDendrogram(Map<String, Object> data) {
        return request("POST", Endpoints.PURCHASING_SEGMENTATION_DENDROGRAM, data, true);
    }

    public Map<String, Object> segmentHierarchyChart(Map<String, Object> data) {
        return request("POST", Endpoints.SEGMENT_HIERARCHY_CHART, data, true);
    }

    public Map<String, Object> segmentSubsegmentExplore(Map<String, Object> data) {
        return request("POST", Endpoints.SEGMENT_SUBSEGMENT_EXPLORE, data, true);
    }

    public Map<String, Object> segmentClusterProfiles(Map<String, Object> data) {
        return request("POST", Endpoints.SEGMENT_CLUSTER_PROFILES, data, true);
    }

    // =========================================================================
    // REPORTING (ADMIN)
    // =========================================================================

    public Map<String, Object> segmentationReport(Map<String, Object> data) {
        return request("POST", Endpoints.SEGMENTATION_REPORT, data, true);
    }

    public Map<String, Object> segmentationReportI18n(Map<String, Object> data) {
        return segmentationReportI18n(data, "pt");
    }

    public Map<String, Object> segmentationReportI18n(Map<String, Object> data, String lang) {
        return request("POST", Endpoints.SEGMENTATION_REPORT_I18N + "?lang=" + lang, data, true);
    }

    public Map<String, Object> segmentationReportJson(Map<String, Object> data) {
        return segmentationReportJson(data, "en");
    }

    public Map<String, Object> segmentationReportJson(Map<String, Object> data, String lang) {
        return request("POST", Endpoints.SEGMENTATION_REPORT_JSON + "?lang=" + lang, data, true);
    }

    // =========================================================================
    // ADMIN OPERATIONS
    // =========================================================================

    @SuppressWarnings("unchecked")
    public Map<String, Object> createCustomer(Map<String, Object> data) {
        Map<String, Object> requestData = new HashMap<>(data);
        if (requestData.containsKey("allowed_routes")) {
            Object allowedRoutes = requestData.get("allowed_routes");
            if (allowedRoutes instanceof List) {
                requestData.put("allowed_routes", RouteResolver.resolve((List<String>) allowedRoutes));
            }
        }
        return requestAdmin("POST", Endpoints.ADMIN_CUSTOMERS, requestData);
    }

    public Map<String, Object> listCustomers() {
        return listCustomers(false);
    }

    public Map<String, Object> listCustomers(boolean includeDisabled) {
        String query = includeDisabled ? "?include_disabled=true" : "";
        return requestAdmin("GET", Endpoints.ADMIN_CUSTOMERS + query, null);
    }

    public Map<String, Object> getCustomer(String customerId) {
        return requestAdmin("GET", Endpoints.ADMIN_CUSTOMERS + "/" + customerId, null);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> updateCustomer(String customerId, Map<String, Object> data) {
        Map<String, Object> requestData = new HashMap<>(data);
        if (requestData.containsKey("allowed_routes")) {
            Object allowedRoutes = requestData.get("allowed_routes");
            if (allowedRoutes instanceof List) {
                requestData.put("allowed_routes", RouteResolver.resolve((List<String>) allowedRoutes));
            }
        }
        return requestAdmin("PUT", Endpoints.ADMIN_CUSTOMERS + "/" + customerId, requestData);
    }

    public Map<String, Object> deleteCustomer(String customerId) {
        return requestAdmin("DELETE", Endpoints.ADMIN_CUSTOMERS + "/" + customerId, null);
    }

    // =========================================================================
    // HTTP METHODS
    // =========================================================================

    protected Map<String, Object> request(String method, String endpoint, Map<String, Object> data, boolean withAuth) {
        Map<String, String> headers = new HashMap<>();
        if (withAuth) {
            headers.putAll(getAuthHeaders());
        }
        return executeRequest(method, endpoint, data, headers);
    }

    protected Map<String, Object> requestAdmin(String method, String endpoint, Map<String, Object> data) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-ADMIN-SECRET", adminSecret);
        return executeRequest(method, endpoint, data, headers);
    }

    protected Map<String, String> getAuthHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-ADMIN-SECRET", adminSecret);
        headers.put("X-Customer-Api-Id", customerApiId);
        headers.put("X-Secret", secret);
        return headers;
    }

    protected Map<String, Object> executeRequest(String method, String endpoint, Map<String, Object> data, Map<String, String> headers) {
        int attempts = 0;

        while (attempts < retryAttempts) {
            try {
                Request.Builder requestBuilder = new Request.Builder()
                        .url(baseUrl + endpoint)
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json");

                for (Map.Entry<String, String> header : headers.entrySet()) {
                    if (header.getValue() != null) {
                        requestBuilder.addHeader(header.getKey(), header.getValue());
                    }
                }

                RequestBody body = null;
                if (data != null && !data.isEmpty()) {
                    body = RequestBody.create(OBJECT_MAPPER.writeValueAsString(data), JSON);
                }

                switch (method.toUpperCase()) {
                    case "GET":
                        requestBuilder.get();
                        break;
                    case "POST":
                        requestBuilder.post(body != null ? body : RequestBody.create("", JSON));
                        break;
                    case "PUT":
                        requestBuilder.put(body != null ? body : RequestBody.create("", JSON));
                        break;
                    case "DELETE":
                        if (body != null) {
                            requestBuilder.delete(body);
                        } else {
                            requestBuilder.delete();
                        }
                        break;
                    default:
                        throw new EchoIntelException("Unsupported HTTP method: " + method);
                }

                try (Response response = httpClient.newCall(requestBuilder.build()).execute()) {
                    return handleResponse(response);
                }

            } catch (EchoIntelAuthenticationException | EchoIntelValidationException e) {
                throw e;
            } catch (IOException e) {
                attempts++;
                if (attempts >= retryAttempts) {
                    throw new EchoIntelException(
                            "Request failed after " + retryAttempts + " attempts: " + e.getMessage(),
                            null,
                            Collections.emptyMap(),
                            e
                    );
                }
                try {
                    Thread.sleep(retryDelay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new EchoIntelException("Request interrupted", null, Collections.emptyMap(), ie);
                }
            }
        }

        return Collections.emptyMap();
    }

    protected Map<String, Object> handleResponse(Response response) throws IOException {
        String responseBody = response.body() != null ? response.body().string() : "";
        Map<String, Object> body = Collections.emptyMap();

        if (!responseBody.isEmpty()) {
            try {
                body = OBJECT_MAPPER.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
            } catch (JsonProcessingException e) {
                body = Collections.emptyMap();
            }
        }

        int statusCode = response.code();

        if (statusCode == 401 || statusCode == 403) {
            String message = body.containsKey("detail") ? String.valueOf(body.get("detail")) : "Authentication failed";
            throw new EchoIntelAuthenticationException(message, statusCode);
        }

        if (statusCode == 422) {
            String message = body.containsKey("detail") ? String.valueOf(body.get("detail")) : "Validation failed";
            Object errors = body.getOrDefault("errors", body.get("detail"));
            List<Object> errorList = errors instanceof List ? (List<Object>) errors : Collections.singletonList(errors);
            throw new EchoIntelValidationException(message, errorList);
        }

        if (!response.isSuccessful()) {
            String message = body.containsKey("detail") ? String.valueOf(body.get("detail")) : "Request failed";
            throw new EchoIntelException(message, statusCode, body);
        }

        return body;
    }
}
