# Commit Message

## Type: feat

## Message

feat: add comprehensive unit tests for EchoIntelClient and RouteResolver

Complete the SDK implementation by adding JUnit 5 unit tests with MockWebServer for the main client and RouteResolver classes.

## Changelog

### Added

- **EchoIntelClientTest.java** - Comprehensive unit tests for the main SDK client
  - Builder pattern tests
  - Health check endpoint tests
  - Forecasting endpoints tests (revenue, cost, units)
  - Customer analytics tests (segmentation, RFM)
  - Admin operations tests (CRUD for customers)
  - Authentication header verification tests
  - Error handling tests (401, 403, 422, 500 responses)
  - Various endpoint coverage tests (sentiment, anomaly, credit risk, journey, NLP)
  - i18n endpoint parameter tests

- **RouteResolverTest.java** - Complete unit tests for route resolution
  - Wildcard resolution tests (`*` excludes admin routes)
  - Category resolution tests (forecasting, customer, inventory, churn, propensity, recommendations, sentiment, anomaly, credit_risk, journey, nlp)
  - Specific route resolution tests (`category.endpoint` format)
  - Mixed categories and routes resolution
  - Unknown category/route handling
  - Empty and null input handling
  - Deduplication tests
  - `categories()` and `endpoints()` helper method tests
  - Admin category exclusion verification

### Technical Details

- Uses JUnit 5 for testing framework
- Uses OkHttp MockWebServer for HTTP mocking
- Tests cover 40+ client methods
- Tests verify HTTP methods, paths, headers, and request bodies
- Exception handling tests for all error scenarios

## Files Modified

### New Files
- `src/test/java/com/echosistema/echointel/EchoIntelClientTest.java`
- `src/test/java/com/echosistema/echointel/RouteResolverTest.java`

## SDK Implementation Status

### Complete Components (65 files)

#### Core
- `pom.xml` - Maven configuration with all dependencies
- `Endpoints.java` - 45+ endpoint constants and `all()` method
- `EchoIntelConfig.java` - Configuration with Lombok builder
- `EchoIntelClient.java` - Main client with 45+ methods
- `EchoIntelClientBuilder.java` - Fluent builder pattern
- `RouteResolver.java` - Route resolution for admin operations

#### Exceptions
- `EchoIntelException.java` - Base exception with status code and context
- `EchoIntelAuthenticationException.java` - For 401/403 errors
- `EchoIntelValidationException.java` - For 422 validation errors

#### Response Classes (57 files)
- `common/EchoIntelResponse.java` - Abstract base response
- `admin/CustomerOut.java`
- `anomaly/` - AnomalyDetail, AnomalyKpiBlock, GraphDetail, GraphResponse, TabularResponse
- `attribution/` - AttributionResponse, ChannelContribution, DecileSummary, UpliftDetail, UpliftResponse
- `churn/ChurnLabelResponse.java`
- `creditrisk/` - ExplainResponse, Prediction, ScoreResponse
- `crosssell/` - CrossSellResponse, UpsellPair, UpsellResponse
- `customer/` - AlgorithmMetrics, BuildFeaturesResponse, ClusterDetail, ClvFeatureOut, ClvFeaturesResponse, ClvForecastCustomer, ClvForecastResponse, CustomerLabel, CustomerScore, FeaturesObject, LoyaltyResponse, LoyaltySummary, RfmCustomerOut, RfmResponse, SegmentationResponse
- `forecast/` - ForecastAlgorithmResult, ForecastData, ForecastEvaluationMetrics, ForecastUnitsResponse, ForecastValue
- `inventory/` - DailyInventoryRecord, InventoryHistoryResponse, ProcessingInfo, ProductDetails
- `journey/` - MarkovResponse, Path, SequenceResponse, Transition
- `pricing/` - PriceOutcome, PricingResponse
- `propensity/PropensityResponse.java`
- `recommendation/` - ModelInfo, RecommendationOut, RecResponse
- `sentiment/` - RealtimeResponse, ReportResponse, SentimentDetail, SentimentKpiBlock

#### Tests (2 files)
- `EchoIntelClientTest.java` - 25+ test methods
- `RouteResolverTest.java` - 20+ test methods

## Build Instructions

```bash
# Compile the project
mvn clean compile

# Run tests
mvn test

# Build JAR
mvn package

# Install to local repository
mvn install
```

## Usage Example

```java
// Create client with builder
EchoIntelClient client = EchoIntelClient.builder()
    .customerApiId("your-customer-id")
    .secret("your-secret")
    .adminSecret("your-admin-secret")
    .timeout(30)
    .build();

// Health check
Map<String, Object> health = client.health();

// Forecast revenue
Map<String, Object> forecastData = new HashMap<>();
forecastData.put("product_id", "prod-123");
forecastData.put("periods", 30);
Map<String, Object> forecast = client.forecastRevenue(forecastData);

// Customer RFM analysis
RfmResponse rfm = RfmResponse.fromMap(client.customerRfm(data));
for (RfmCustomerOut customer : rfm.getCustomers()) {
    System.out.println(customer.getRfmTier());
}

// Admin operations
Map<String, Object> customerData = new HashMap<>();
customerData.put("name", "New Customer");
customerData.put("allowed_routes", Arrays.asList("forecasting", "customer.rfm"));
Map<String, Object> newCustomer = client.createCustomer(customerData);
```
