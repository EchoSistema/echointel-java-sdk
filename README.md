# EchoIntel Java SDK

Java SDK for EchoIntel AI API - Machine Learning capabilities for forecasting, customer analytics, inventory optimization and more.

## Requirements

- Java 11+
- Maven 3.6+

## Installation

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.echosistema</groupId>
    <artifactId>echointel-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quick Start

```java
import com.echosistema.echointel.EchoIntelClient;

import java.util.HashMap;
import java.util.Map;

public class Example {
    public static void main(String[] args) {
        // Create client using builder pattern
        EchoIntelClient client = EchoIntelClient.builder()
                .baseUrl("https://ai.echosistema.live")
                .customerApiId("your-customer-id")
                .secret("your-secret")
                .adminSecret("your-admin-secret")  // optional
                .timeout(30)
                .retryAttempts(3)
                .retryDelay(100)
                .build();

        // Check API health
        Map<String, Object> health = client.health();
        System.out.println("API Status: " + health);

        // Example: Customer RFM Analysis
        Map<String, Object> rfmData = new HashMap<>();
        rfmData.put("transactions", myTransactionsList);

        Map<String, Object> rfmResult = client.customerRfm(rfmData);
        System.out.println("RFM Analysis: " + rfmResult);
    }
}
```

## Configuration

### Using Builder Pattern

```java
EchoIntelClient client = EchoIntelClient.builder()
        .baseUrl("https://ai.echosistema.live")  // Default
        .customerApiId("your-customer-id")
        .secret("your-secret")
        .adminSecret("your-admin-secret")
        .timeout(30)              // seconds
        .retryAttempts(3)         // number of retries
        .retryDelay(100)          // milliseconds between retries
        .build();
```

### Using Config Object

```java
import com.echosistema.echointel.config.EchoIntelConfig;

EchoIntelConfig config = EchoIntelConfig.builder()
        .baseUrl("https://ai.echosistema.live")
        .customerApiId("your-customer-id")
        .secret("your-secret")
        .build();

EchoIntelClient client = new EchoIntelClient(config);
```

## Available Methods

### System
- `health()` - Check API status

### Forecasting
- `forecastRevenue(data)` - Revenue forecasting
- `forecastCost(data)` - Cost forecasting
- `forecastCostImproved(data)` - Improved cost forecasting
- `forecastUnits(data)` - Units forecasting
- `forecastCostTotus(data)` - Totus cost forecasting

### Inventory
- `inventoryOptimization(data)` - Inventory optimization
- `inventoryHistoryImproved(data)` - Improved inventory history analysis

### Customer Analytics
- `customerSegmentation(data)` - Customer segmentation
- `customerFeatures(data)` - Customer feature extraction
- `customerLoyalty(data)` - Loyalty analysis
- `customerRfm(data)` - RFM (Recency, Frequency, Monetary) analysis
- `customerClvFeatures(data)` - CLV feature extraction
- `customerClvForecast(data)` - CLV forecasting

### Churn
- `churnRisk(data)` - Churn risk prediction
- `churnLabel(data)` - Churn labeling

### NPS
- `nps(data)` - NPS calculation

### Propensity
- `propensityBuyProduct(data)` - Propensity to buy product
- `propensityRespondCampaign(data)` - Propensity to respond to campaign
- `propensityUpgradePlan(data)` - Propensity to upgrade plan

### Recommendations
- `recommendUserItems(data)` - User-based recommendations
- `recommendSimilarItems(data)` - Item-based recommendations

### Cross-Sell & Upsell
- `crossSellMatrix(data)` - Cross-sell opportunity matrix
- `upsellSuggestions(data)` - Upsell suggestions

### Dynamic Pricing
- `dynamicPricingRecommend(data)` - Price recommendations

### Sentiment Analysis
- `sentimentReport(data)` - Sentiment analysis report
- `sentimentRealtime(data)` - Real-time sentiment analysis

### Anomaly Detection
- `anomalyTransactions(data)` - Transaction anomaly detection
- `anomalyAccounts(data)` - Account anomaly detection
- `anomalyGraph(data)` - Graph-based anomaly detection

### Credit Risk
- `creditRiskScore(data)` - Credit risk scoring
- `creditRiskExplain(data)` - Credit risk explanation

### Marketing Attribution
- `channelAttribution(data)` - Channel attribution analysis
- `upliftModel(data)` - Uplift modeling

### Customer Journey
- `journeyMarkov(data)` - Markov chain analysis
- `journeySequences(data)` - Journey sequence analysis

### NLP
- `nlpAnalysis(data)` - NLP analysis (Portuguese)
- `nlpAnalysisEn(data)` - NLP analysis (English)
- `nlpExcessInventoryReport(data)` - Excess inventory report
- `sanitizeText(data)` - Text sanitization

### Admin Operations (requires adminSecret)
- `purchasingSegmentation(data)` - Purchasing segmentation
- `purchasingSegmentationDendrogram(data)` - Dendrogram visualization
- `segmentHierarchyChart(data)` - Hierarchy chart
- `segmentSubsegmentExplore(data)` - Subsegment exploration
- `segmentClusterProfiles(data)` - Cluster profiles
- `segmentationReport(data)` - Segmentation report
- `segmentationReportI18n(data, lang)` - i18n report
- `segmentationReportJson(data, lang)` - JSON report
- `createCustomer(data)` - Create customer
- `listCustomers()` / `listCustomers(includeDisabled)` - List customers
- `getCustomer(customerId)` - Get customer
- `updateCustomer(customerId, data)` - Update customer
- `deleteCustomer(customerId)` - Delete customer

## Error Handling

```java
import com.echosistema.echointel.exceptions.*;

try {
    Map<String, Object> result = client.forecastRevenue(data);
} catch (EchoIntelAuthenticationException e) {
    // Handle 401/403 errors
    System.err.println("Authentication failed: " + e.getMessage());
    System.err.println("Status code: " + e.getStatusCode());
} catch (EchoIntelValidationException e) {
    // Handle 422 errors
    System.err.println("Validation failed: " + e.getMessage());
    System.err.println("Errors: " + e.getErrors());
} catch (EchoIntelException e) {
    // Handle other API errors
    System.err.println("API error: " + e.getMessage());
    System.err.println("Status code: " + e.getStatusCode());
    System.err.println("Context: " + e.getContext());
}
```

## Response Objects

The SDK provides typed response classes for all API responses:

```java
import com.echosistema.echointel.responses.customer.RfmResponse;
import com.echosistema.echointel.responses.customer.RfmCustomerOut;

// Get raw Map response
Map<String, Object> result = client.customerRfm(data);

// Convert to typed response
RfmResponse rfmResponse = RfmResponse.fromMap(result);

// Access typed properties
for (RfmCustomerOut customer : rfmResponse.getCustomers()) {
    System.out.println("Customer: " + customer.getCustomerId());
    System.out.println("RFM Class: " + customer.getRfmClass());
    System.out.println("RFM Tier: " + customer.getRfmTier());
    System.out.println("Interpretation: " + customer.getInterpretation());
}
```

## Route Resolver

For admin operations, you can use the RouteResolver to resolve route patterns:

```java
import com.echosistema.echointel.RouteResolver;

// Resolve all non-admin routes
List<String> allRoutes = RouteResolver.resolve(List.of("*"));

// Resolve specific category
List<String> forecastingRoutes = RouteResolver.resolve(List.of("forecasting"));

// Resolve specific endpoint
List<String> revenueRoute = RouteResolver.resolve(List.of("forecasting.revenue"));

// Get all categories
List<String> categories = RouteResolver.categories();

// Get endpoints for a category
List<String> endpoints = RouteResolver.endpoints("forecasting");
```

## Building from Source

```bash
mvn clean install
```

## Running Tests

```bash
mvn test
```

## License

MIT License
