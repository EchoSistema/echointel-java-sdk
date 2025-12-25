# EchoIntel Java SDK - Development Guidelines

## Project Overview

SDK Java para integração com a API EchoIntel AI, oferecendo capacidades de Machine Learning para forecasting, analytics de clientes, otimização de inventário e mais.

**Baseado em:** SDK Laravel em `/home/ewerton/Jetbrains/PhpStormProjects/echointel-sdk/laravel`

## Project Structure

```
java/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── echosistema/
│   │   │           └── echointel/
│   │   │               ├── EchoIntelClient.java
│   │   │               ├── EchoIntelClientBuilder.java
│   │   │               ├── Endpoints.java
│   │   │               ├── RouteResolver.java
│   │   │               ├── config/
│   │   │               │   └── EchoIntelConfig.java
│   │   │               ├── exceptions/
│   │   │               │   ├── EchoIntelException.java
│   │   │               │   ├── EchoIntelAuthenticationException.java
│   │   │               │   └── EchoIntelValidationException.java
│   │   │               └── responses/
│   │   │                   ├── common/
│   │   │                   │   └── EchoIntelResponse.java
│   │   │                   ├── admin/
│   │   │                   │   └── CustomerOut.java
│   │   │                   ├── anomaly/
│   │   │                   │   ├── AnomalyDetail.java
│   │   │                   │   ├── AnomalyKpiBlock.java
│   │   │                   │   ├── GraphDetail.java
│   │   │                   │   ├── GraphResponse.java
│   │   │                   │   └── TabularResponse.java
│   │   │                   ├── attribution/
│   │   │                   │   ├── AttributionResponse.java
│   │   │                   │   ├── ChannelContribution.java
│   │   │                   │   ├── DecileSummary.java
│   │   │                   │   ├── UpliftDetail.java
│   │   │                   │   └── UpliftResponse.java
│   │   │                   ├── churn/
│   │   │                   │   └── ChurnLabelResponse.java
│   │   │                   ├── creditrisk/
│   │   │                   │   ├── ExplainResponse.java
│   │   │                   │   ├── Prediction.java
│   │   │                   │   └── ScoreResponse.java
│   │   │                   ├── crosssell/
│   │   │                   │   ├── CrossSellResponse.java
│   │   │                   │   ├── UpsellPair.java
│   │   │                   │   └── UpsellResponse.java
│   │   │                   ├── customer/
│   │   │                   │   ├── AlgorithmMetrics.java
│   │   │                   │   ├── BuildFeaturesResponse.java
│   │   │                   │   ├── ClusterDetail.java
│   │   │                   │   ├── ClvFeatureOut.java
│   │   │                   │   ├── ClvFeaturesResponse.java
│   │   │                   │   ├── ClvForecastCustomer.java
│   │   │                   │   ├── ClvForecastResponse.java
│   │   │                   │   ├── CustomerLabel.java
│   │   │                   │   ├── CustomerScore.java
│   │   │                   │   ├── FeaturesObject.java
│   │   │                   │   ├── LoyaltyResponse.java
│   │   │                   │   ├── LoyaltySummary.java
│   │   │                   │   ├── RfmCustomerOut.java
│   │   │                   │   ├── RfmResponse.java
│   │   │                   │   └── SegmentationResponse.java
│   │   │                   ├── forecast/
│   │   │                   │   ├── ForecastAlgorithmResult.java
│   │   │                   │   ├── ForecastData.java
│   │   │                   │   ├── ForecastEvaluationMetrics.java
│   │   │                   │   ├── ForecastUnitsResponse.java
│   │   │                   │   └── ForecastValue.java
│   │   │                   ├── inventory/
│   │   │                   │   ├── DailyInventoryRecord.java
│   │   │                   │   ├── InventoryHistoryResponse.java
│   │   │                   │   ├── ProcessingInfo.java
│   │   │                   │   └── ProductDetails.java
│   │   │                   ├── journey/
│   │   │                   │   ├── MarkovResponse.java
│   │   │                   │   ├── Path.java
│   │   │                   │   ├── SequenceResponse.java
│   │   │                   │   └── Transition.java
│   │   │                   ├── pricing/
│   │   │                   │   ├── PriceOutcome.java
│   │   │                   │   └── PricingResponse.java
│   │   │                   ├── propensity/
│   │   │                   │   └── PropensityResponse.java
│   │   │                   ├── recommendation/
│   │   │                   │   ├── ModelInfo.java
│   │   │                   │   ├── RecommendationOut.java
│   │   │                   │   └── RecResponse.java
│   │   │                   └── sentiment/
│   │   │                       ├── RealtimeResponse.java
│   │   │                       ├── ReportResponse.java
│   │   │                       ├── SentimentDetail.java
│   │   │                       └── SentimentKpiBlock.java
│   │   └── resources/
│   │       └── echointel.properties
│   └── test/
│       └── java/
│           └── com/
│               └── echosistema/
│                   └── echointel/
│                       ├── EchoIntelClientTest.java
│                       └── RouteResolverTest.java
└── README.md
```

## Technical Requirements

- **Java Version:** 11+ (LTS)
- **Build Tool:** Maven
- **HTTP Client:** OkHttp 4.x
- **JSON Processing:** Jackson 2.x
- **Testing:** JUnit 5, Mockito

## API Endpoints (Exatos do Laravel)

Base URL: `https://ai.echosistema.live`

### Endpoints Constants

```java
public final class Endpoints {
    public static final String BASE_URL = "https://ai.echosistema.live";

    // System
    public static final String HEALTH = "/health";

    // Forecasting
    public static final String FORECAST_REVENUE = "/api/forecast_revenue";
    public static final String FORECAST_COST = "/api/forecast_cost";
    public static final String FORECAST_COST_IMPROVED = "/api/forecast_cost_improved";
    public static final String FORECAST_UNITS = "/api/forecast_units";
    public static final String FORECAST_COST_TOTUS = "/api/forecast_cost_totus";

    // Inventory
    public static final String INVENTORY_OPTIMIZATION = "/api/inventory_optimization";
    public static final String INVENTORY_HISTORY_IMPROVED = "/api/inventory_history_improved";

    // Customer Analytics
    public static final String CUSTOMER_SEGMENTATION = "/api/customer_segmentation";
    public static final String CUSTOMER_FEATURES = "/api/customer_features";
    public static final String CUSTOMER_LOYALTY = "/api/customer_loyalty";
    public static final String CUSTOMER_RFM = "/api/customer_rfm";
    public static final String CUSTOMER_CLV_FEATURES = "/api/customer_clv_features";
    public static final String CUSTOMER_CLV_FORECAST = "/api/customer_clv_forecast";

    // Churn
    public static final String CHURN_RISK = "/api/churn_risk";
    public static final String CHURN_LABEL = "/api/churn_label";

    // NPS
    public static final String NPS = "/api/nps";

    // Propensity
    public static final String PROPENSITY_BUY_PRODUCT = "/api/propensity_buy_product";
    public static final String PROPENSITY_RESPOND_CAMPAIGN = "/api/propensity_respond_campaign";
    public static final String PROPENSITY_UPGRADE_PLAN = "/api/propensity_upgrade_plan";

    // Recommendations
    public static final String RECOMMEND_USER_ITEMS = "/api/recommend_user_items";
    public static final String RECOMMEND_SIMILAR_ITEMS = "/api/recommend_similar_items";

    // Cross-Sell & Upsell
    public static final String CROSS_SELL_MATRIX = "/api/cross_sell_matrix";
    public static final String UPSELL_SUGGESTIONS = "/api/upsell_suggestions";

    // Dynamic Pricing
    public static final String DYNAMIC_PRICING_RECOMMEND = "/api/dynamic_pricing_recommend";

    // Sentiment
    public static final String SENTIMENT_REPORT = "/api/sentiment_report";
    public static final String SENTIMENT_REALTIME = "/api/sentiment_realtime";

    // Anomaly Detection
    public static final String ANOMALY_TRANSACTIONS = "/api/anomaly_transactions";
    public static final String ANOMALY_ACCOUNTS = "/api/anomaly_accounts";
    public static final String ANOMALY_GRAPH = "/api/anomaly_graph";

    // Credit Risk
    public static final String CREDIT_RISK_SCORE = "/api/credit_risk_score";
    public static final String CREDIT_RISK_EXPLAIN = "/api/credit_risk_explain";

    // Marketing Attribution
    public static final String CHANNEL_ATTRIBUTION = "/api/channel_attribution";
    public static final String UPLIFT_MODEL = "/api/uplift_model";

    // Customer Journey
    public static final String JOURNEY_MARKOV = "/api/journey_markov";
    public static final String JOURNEY_SEQUENCES = "/api/journey_sequences";

    // NLP
    public static final String NLP_ANALYSIS = "/api/nlp_analisys";
    public static final String NLP_ANALYSIS_EN = "/api/nlp_analisys_en";
    public static final String NLP_EXCESS_INVENTORY_REPORT = "/api/nlp_openai_excess_inventory_report";
    public static final String SANITIZE_TEXT = "/api/sanitize_text";

    // Advanced Segmentation (Admin)
    public static final String PURCHASING_SEGMENTATION = "/api/purchasing_segmentation";
    public static final String PURCHASING_SEGMENTATION_DENDROGRAM = "/api/purchasing_segmentation_dendrogram";
    public static final String SEGMENT_HIERARCHY_CHART = "/api/segment_hierarchy_chart";
    public static final String SEGMENT_SUBSEGMENT_EXPLORE = "/api/segment_subsegment_explore";
    public static final String SEGMENT_CLUSTER_PROFILES = "/api/segment_cluster_profiles";

    // Reporting (Admin)
    public static final String SEGMENTATION_REPORT = "/api/segmentation_report";
    public static final String SEGMENTATION_REPORT_I18N = "/api/segmentation_report_i18n";
    public static final String SEGMENTATION_REPORT_JSON = "/api/segmentation_report_json";

    // Admin
    public static final String ADMIN_CUSTOMERS = "/admin/customers";
}
```

## Authentication Headers

```java
// Standard authentication
headers.put("X-Customer-Api-Id", customerApiId);
headers.put("X-Secret", secret);
```

## Client Methods (40+ methods)

### System
- `health()` → GET /health

### Forecasting
- `forecastRevenue(Map<String, Object> data)` → POST
- `forecastCost(Map<String, Object> data)` → POST
- `forecastCostImproved(Map<String, Object> data)` → POST
- `forecastUnits(Map<String, Object> data)` → POST
- `forecastCostTotus(Map<String, Object> data)` → POST

### Inventory
- `inventoryOptimization(Map<String, Object> data)` → POST
- `inventoryHistoryImproved(Map<String, Object> data)` → POST

### Customer Analytics
- `customerSegmentation(Map<String, Object> data)` → POST
- `customerFeatures(Map<String, Object> data)` → POST
- `customerLoyalty(Map<String, Object> data)` → POST
- `customerRfm(Map<String, Object> data)` → POST
- `customerClvFeatures(Map<String, Object> data)` → POST
- `customerClvForecast(Map<String, Object> data)` → POST

### Churn
- `churnRisk(Map<String, Object> data)` → POST
- `churnLabel(Map<String, Object> data)` → POST

### NPS
- `nps(Map<String, Object> data)` → POST

### Propensity
- `propensityBuyProduct(Map<String, Object> data)` → POST
- `propensityRespondCampaign(Map<String, Object> data)` → POST
- `propensityUpgradePlan(Map<String, Object> data)` → POST

### Recommendations
- `recommendUserItems(Map<String, Object> data)` → POST
- `recommendSimilarItems(Map<String, Object> data)` → POST

### Cross-Sell & Upsell
- `crossSellMatrix(Map<String, Object> data)` → POST
- `upsellSuggestions(Map<String, Object> data)` → POST

### Dynamic Pricing
- `dynamicPricingRecommend(Map<String, Object> data)` → POST

### Sentiment
- `sentimentReport(Map<String, Object> data)` → POST
- `sentimentRealtime(Map<String, Object> data)` → POST

### Anomaly Detection
- `anomalyTransactions(Map<String, Object> data)` → POST
- `anomalyAccounts(Map<String, Object> data)` → POST
- `anomalyGraph(Map<String, Object> data)` → POST

### Credit Risk
- `creditRiskScore(Map<String, Object> data)` → POST
- `creditRiskExplain(Map<String, Object> data)` → POST

### Marketing Attribution
- `channelAttribution(Map<String, Object> data)` → POST
- `upliftModel(Map<String, Object> data)` → POST

### Customer Journey
- `journeyMarkov(Map<String, Object> data)` → POST
- `journeySequences(Map<String, Object> data)` → POST

### NLP
- `nlpAnalysis(Map<String, Object> data)` → POST
- `nlpAnalysisEn(Map<String, Object> data)` → POST
- `nlpExcessInventoryReport(Map<String, Object> data)` → POST
- `sanitizeText(Map<String, Object> data)` → POST

### Advanced Segmentation (Admin - withAuth=true)
- `purchasingSegmentation(Map<String, Object> data)` → POST
- `purchasingSegmentationDendrogram(Map<String, Object> data)` → POST
- `segmentHierarchyChart(Map<String, Object> data)` → POST
- `segmentSubsegmentExplore(Map<String, Object> data)` → POST
- `segmentClusterProfiles(Map<String, Object> data)` → POST

### Reporting (Admin - withAuth=true)
- `segmentationReport(Map<String, Object> data)` → POST
- `segmentationReportI18n(Map<String, Object> data, String lang)` → POST (default: "pt")
- `segmentationReportJson(Map<String, Object> data, String lang)` → POST (default: "en")

### Admin Operations (requestAdmin)
- `createCustomer(Map<String, Object> data)` → POST (resolve allowed_routes)
- `listCustomers(boolean includeDisabled)` → GET (?include_disabled=true)
- `getCustomer(String customerId)` → GET
- `updateCustomer(String customerId, Map<String, Object> data)` → PUT (resolve allowed_routes)
- `deleteCustomer(String customerId)` → DELETE

## Exception Classes

```java
// Base exception
public class EchoIntelException extends RuntimeException {
    private final Integer statusCode;
    private final Map<String, Object> context;

    public EchoIntelException(String message, Integer statusCode, Map<String, Object> context, Throwable cause);
    public Integer getStatusCode();
    public Map<String, Object> getContext();
}

// Authentication exception (401/403)
public class EchoIntelAuthenticationException extends EchoIntelException {
    public EchoIntelAuthenticationException(String message, Integer statusCode, Throwable cause);
    // Default message: "Authentication failed", statusCode: 401
}

// Validation exception (422)
public class EchoIntelValidationException extends EchoIntelException {
    private final List<Object> errors;

    public EchoIntelValidationException(String message, List<Object> errors, Throwable cause);
    public List<Object> getErrors();
    // Default message: "Validation failed", statusCode: 422
}
```

## Response Base Class Pattern

```java
public abstract class EchoIntelResponse {
    protected Map<String, Object> data;

    public EchoIntelResponse(Map<String, Object> data) {
        this.data = data;
        hydrate(data);
    }

    protected abstract void hydrate(Map<String, Object> data);

    public Map<String, Object> toMap() {
        return data;
    }

    public static <T extends EchoIntelResponse> T fromMap(Map<String, Object> data, Class<T> clazz);
}
```

## Response Class Example (RfmCustomerOut)

```java
@Data
public class RfmCustomerOut extends EchoIntelResponse {
    private String customerId;
    private int recencyDays;
    private int frequency;
    private double monetaryTotal;
    private int recencyScore;
    private int frequencyScore;
    private int monetaryScore;
    private String rfmClass;
    private int rfmTotal;
    private String rfmTier;
    private String interpretation;

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.customerId = (String) data.getOrDefault("customer_id", "");
        this.recencyDays = ((Number) data.getOrDefault("recency_days", 0)).intValue();
        this.frequency = ((Number) data.getOrDefault("frequency", 0)).intValue();
        this.monetaryTotal = ((Number) data.getOrDefault("monetary_total", 0.0)).doubleValue();
        this.recencyScore = ((Number) data.getOrDefault("recency_score", 0)).intValue();
        this.frequencyScore = ((Number) data.getOrDefault("frequency_score", 0)).intValue();
        this.monetaryScore = ((Number) data.getOrDefault("monetary_score", 0)).intValue();
        this.rfmClass = (String) data.getOrDefault("rfm_class", "");
        this.rfmTotal = ((Number) data.getOrDefault("rfm_total", 0)).intValue();
        this.rfmTier = (String) data.getOrDefault("rfm_tier", "");
        this.interpretation = (String) data.getOrDefault("interpretation", "");
    }
}
```

## Client Configuration

```java
public class EchoIntelConfig {
    private String baseUrl = "https://ai.echosistema.live";
    private String customerApiId;
    private String secret;
    private int timeout = 30; // seconds
    private int retryAttempts = 3;
    private int retryDelay = 100; // milliseconds
}
```

## Builder Pattern

```java
EchoIntelClient client = EchoIntelClient.builder()
    .baseUrl("https://ai.echosistema.live")
    .customerApiId("your-customer-id")
    .secret("your-secret")
    .timeout(30)
    .retryAttempts(3)
    .retryDelay(100)
    .build();
```

## HTTP Request Flow

```java
// Standard request
protected Map<String, Object> request(String method, String endpoint, Map<String, Object> data, boolean withAuth) {
    // 1. Build options
    // 2. If withAuth, add getAuthHeaders()
    // 3. Call executeRequest()
}

// Admin request
protected Map<String, Object> requestAdmin(String method, String endpoint, Map<String, Object> data) {
    // 1. Add X-ADMIN-SECRET header
    // 2. Call executeRequest()
}

// Execute with retry
protected Map<String, Object> executeRequest(String method, String endpoint, Map<String, Object> options) {
    // 1. Retry loop (attempts)
    // 2. Execute HTTP request
    // 3. Handle responses:
    //    - 401/403 → throw EchoIntelAuthenticationException
    //    - 422 → throw EchoIntelValidationException
    //    - Other errors → throw EchoIntelException
    // 4. Sleep on retry (delay ms)
}
```

## RouteResolver

```java
public class RouteResolver {
    private static final List<String> ADMIN_CATEGORIES = List.of("admin");

    // Resolve ["*"] → all non-admin routes
    // Resolve ["forecasting"] → all forecasting routes
    // Resolve ["forecasting.revenue"] → specific route
    public static List<String> resolve(List<String> routes);

    public static List<String> categories();
    public static List<String> endpoints(String category);
}
```

## Implementation Order

1. `pom.xml` - Maven configuration
2. `Endpoints.java` - Endpoint constants
3. `EchoIntelConfig.java` - Configuration class
4. `exceptions/` - Exception classes (3 files)
5. `responses/common/EchoIntelResponse.java` - Base response
6. `EchoIntelClient.java` - Main client (40+ methods)
7. `EchoIntelClientBuilder.java` - Builder
8. `RouteResolver.java` - Route resolution
9. Response classes by category (57 files)
10. Tests

## Code Principles

- **DRY:** Extract repeated code
- **KISS:** Keep it simple
- **YAGNI:** Don't add unused features
- **SOLID:** Follow SOLID principles
- Follow Google Java Style Guide

## Git Commit Policy

**IMPORTANTE:** O AI Agent NÃO deve executar commits. Deve criar/atualizar `commit_message.md` com:
- Tipo semântico (feat, fix, refactor, etc.)
- Mensagem de commit
- Changelog detalhado
- Lista de arquivos modificados

## Reference Files

- **Client:** `/home/ewerton/Jetbrains/PhpStormProjects/echointel-sdk/laravel/src/EchoIntelClient.php`
- **Endpoints:** `/home/ewerton/Jetbrains/PhpStormProjects/echointel-sdk/laravel/src/Endpoints.php`
- **RouteResolver:** `/home/ewerton/Jetbrains/PhpStormProjects/echointel-sdk/laravel/src/RouteResolver.php`
- **Config:** `/home/ewerton/Jetbrains/PhpStormProjects/echointel-sdk/laravel/src/config/echointel.php`
- **Exceptions:** `/home/ewerton/Jetbrains/PhpStormProjects/echointel-sdk/laravel/src/Exceptions/`
- **Responses:** `/home/ewerton/Jetbrains/PhpStormProjects/echointel-sdk/laravel/src/Responses/`
