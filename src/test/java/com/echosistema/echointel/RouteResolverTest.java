package com.echosistema.echointel;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RouteResolverTest {

    // =========================================================================
    // WILDCARD RESOLUTION TESTS
    // =========================================================================

    @Test
    void testResolveWildcardReturnsAllNonAdminRoutes() {
        List<String> result = RouteResolver.resolve(Arrays.asList("*"));

        assertTrue(result.size() > 30, "Should return many routes");
        assertTrue(result.contains(Endpoints.FORECAST_REVENUE));
        assertTrue(result.contains(Endpoints.CUSTOMER_SEGMENTATION));
        assertTrue(result.contains(Endpoints.SENTIMENT_REPORT));
        assertFalse(result.contains(Endpoints.ADMIN_CUSTOMERS), "Should not include admin routes");
    }

    // =========================================================================
    // CATEGORY RESOLUTION TESTS
    // =========================================================================

    @Test
    void testResolveForecastingCategory() {
        List<String> result = RouteResolver.resolve(Arrays.asList("forecasting"));

        assertEquals(5, result.size());
        assertTrue(result.contains(Endpoints.FORECAST_REVENUE));
        assertTrue(result.contains(Endpoints.FORECAST_COST));
        assertTrue(result.contains(Endpoints.FORECAST_COST_IMPROVED));
        assertTrue(result.contains(Endpoints.FORECAST_UNITS));
        assertTrue(result.contains(Endpoints.FORECAST_COST_TOTUS));
    }

    @Test
    void testResolveCustomerCategory() {
        List<String> result = RouteResolver.resolve(Arrays.asList("customer"));

        assertEquals(6, result.size());
        assertTrue(result.contains(Endpoints.CUSTOMER_SEGMENTATION));
        assertTrue(result.contains(Endpoints.CUSTOMER_FEATURES));
        assertTrue(result.contains(Endpoints.CUSTOMER_LOYALTY));
        assertTrue(result.contains(Endpoints.CUSTOMER_RFM));
        assertTrue(result.contains(Endpoints.CUSTOMER_CLV_FEATURES));
        assertTrue(result.contains(Endpoints.CUSTOMER_CLV_FORECAST));
    }

    @Test
    void testResolveInventoryCategory() {
        List<String> result = RouteResolver.resolve(Arrays.asList("inventory"));

        assertEquals(2, result.size());
        assertTrue(result.contains(Endpoints.INVENTORY_OPTIMIZATION));
        assertTrue(result.contains(Endpoints.INVENTORY_HISTORY_IMPROVED));
    }

    @Test
    void testResolveChurnCategory() {
        List<String> result = RouteResolver.resolve(Arrays.asList("churn"));

        assertEquals(2, result.size());
        assertTrue(result.contains(Endpoints.CHURN_RISK));
        assertTrue(result.contains(Endpoints.CHURN_LABEL));
    }

    @Test
    void testResolvePropensityCategory() {
        List<String> result = RouteResolver.resolve(Arrays.asList("propensity"));

        assertEquals(3, result.size());
        assertTrue(result.contains(Endpoints.PROPENSITY_BUY_PRODUCT));
        assertTrue(result.contains(Endpoints.PROPENSITY_RESPOND_CAMPAIGN));
        assertTrue(result.contains(Endpoints.PROPENSITY_UPGRADE_PLAN));
    }

    @Test
    void testResolveRecommendationsCategory() {
        List<String> result = RouteResolver.resolve(Arrays.asList("recommendations"));

        assertEquals(2, result.size());
        assertTrue(result.contains(Endpoints.RECOMMEND_USER_ITEMS));
        assertTrue(result.contains(Endpoints.RECOMMEND_SIMILAR_ITEMS));
    }

    @Test
    void testResolveSentimentCategory() {
        List<String> result = RouteResolver.resolve(Arrays.asList("sentiment"));

        assertEquals(2, result.size());
        assertTrue(result.contains(Endpoints.SENTIMENT_REPORT));
        assertTrue(result.contains(Endpoints.SENTIMENT_REALTIME));
    }

    @Test
    void testResolveAnomalyCategory() {
        List<String> result = RouteResolver.resolve(Arrays.asList("anomaly"));

        assertEquals(3, result.size());
        assertTrue(result.contains(Endpoints.ANOMALY_TRANSACTIONS));
        assertTrue(result.contains(Endpoints.ANOMALY_ACCOUNTS));
        assertTrue(result.contains(Endpoints.ANOMALY_GRAPH));
    }

    @Test
    void testResolveCreditRiskCategory() {
        List<String> result = RouteResolver.resolve(Arrays.asList("credit_risk"));

        assertEquals(2, result.size());
        assertTrue(result.contains(Endpoints.CREDIT_RISK_SCORE));
        assertTrue(result.contains(Endpoints.CREDIT_RISK_EXPLAIN));
    }

    @Test
    void testResolveJourneyCategory() {
        List<String> result = RouteResolver.resolve(Arrays.asList("journey"));

        assertEquals(2, result.size());
        assertTrue(result.contains(Endpoints.JOURNEY_MARKOV));
        assertTrue(result.contains(Endpoints.JOURNEY_SEQUENCES));
    }

    @Test
    void testResolveNlpCategory() {
        List<String> result = RouteResolver.resolve(Arrays.asList("nlp"));

        assertEquals(4, result.size());
        assertTrue(result.contains(Endpoints.NLP_ANALYSIS));
        assertTrue(result.contains(Endpoints.NLP_ANALYSIS_EN));
        assertTrue(result.contains(Endpoints.NLP_EXCESS_INVENTORY_REPORT));
        assertTrue(result.contains(Endpoints.SANITIZE_TEXT));
    }

    // =========================================================================
    // SPECIFIC ROUTE RESOLUTION TESTS
    // =========================================================================

    @Test
    void testResolveSpecificRoute() {
        List<String> result = RouteResolver.resolve(Arrays.asList("forecasting.revenue"));

        assertEquals(1, result.size());
        assertEquals(Endpoints.FORECAST_REVENUE, result.get(0));
    }

    @Test
    void testResolveMultipleSpecificRoutes() {
        List<String> result = RouteResolver.resolve(Arrays.asList(
                "forecasting.revenue",
                "customer.segmentation"
        ));

        assertEquals(2, result.size());
        assertTrue(result.contains(Endpoints.FORECAST_REVENUE));
        assertTrue(result.contains(Endpoints.CUSTOMER_SEGMENTATION));
    }

    @Test
    void testResolveMixedCategoriesAndRoutes() {
        List<String> result = RouteResolver.resolve(Arrays.asList(
                "forecasting",
                "customer.rfm"
        ));

        assertEquals(6, result.size());
        assertTrue(result.contains(Endpoints.FORECAST_REVENUE));
        assertTrue(result.contains(Endpoints.CUSTOMER_RFM));
    }

    // =========================================================================
    // UNKNOWN ROUTE TESTS
    // =========================================================================

    @Test
    void testResolveUnknownCategoryReturnsAsIs() {
        List<String> result = RouteResolver.resolve(Arrays.asList("unknown_category"));

        assertEquals(1, result.size());
        assertEquals("unknown_category", result.get(0));
    }

    @Test
    void testResolveUnknownSpecificRouteReturnsAsIs() {
        List<String> result = RouteResolver.resolve(Arrays.asList("forecasting.unknown"));

        assertEquals(1, result.size());
        assertEquals("forecasting.unknown", result.get(0));
    }

    // =========================================================================
    // EMPTY AND NULL TESTS
    // =========================================================================

    @Test
    void testResolveEmptyListReturnsEmpty() {
        List<String> result = RouteResolver.resolve(Collections.emptyList());

        assertTrue(result.isEmpty());
    }

    @Test
    void testResolveNullReturnsEmpty() {
        List<String> result = RouteResolver.resolve(null);

        assertTrue(result.isEmpty());
    }

    // =========================================================================
    // DEDUPLICATION TESTS
    // =========================================================================

    @Test
    void testResolveDuplicatesAreRemoved() {
        List<String> result = RouteResolver.resolve(Arrays.asList(
                "forecasting.revenue",
                "forecasting.revenue",
                "forecasting"
        ));

        long revenueCount = result.stream()
                .filter(r -> r.equals(Endpoints.FORECAST_REVENUE))
                .count();

        assertEquals(1, revenueCount, "Revenue endpoint should appear only once");
    }

    // =========================================================================
    // CATEGORIES AND ENDPOINTS METHODS TESTS
    // =========================================================================

    @Test
    void testCategoriesReturnsAllCategories() {
        List<String> categories = RouteResolver.categories();

        assertTrue(categories.contains("forecasting"));
        assertTrue(categories.contains("customer"));
        assertTrue(categories.contains("inventory"));
        assertTrue(categories.contains("churn"));
        assertTrue(categories.contains("propensity"));
        assertTrue(categories.contains("recommendations"));
        assertTrue(categories.contains("sentiment"));
        assertTrue(categories.contains("anomaly"));
        assertTrue(categories.contains("credit_risk"));
        assertTrue(categories.contains("journey"));
        assertTrue(categories.contains("nlp"));
        assertTrue(categories.contains("admin"));
    }

    @Test
    void testEndpointsForForecastingCategory() {
        List<String> endpoints = RouteResolver.endpoints("forecasting");

        assertEquals(5, endpoints.size());
        assertTrue(endpoints.contains("revenue"));
        assertTrue(endpoints.contains("cost"));
        assertTrue(endpoints.contains("cost_improved"));
        assertTrue(endpoints.contains("units"));
        assertTrue(endpoints.contains("cost_totus"));
    }

    @Test
    void testEndpointsForUnknownCategoryReturnsEmpty() {
        List<String> endpoints = RouteResolver.endpoints("unknown");

        assertTrue(endpoints.isEmpty());
    }

    // =========================================================================
    // ADMIN CATEGORY EXCLUSION TESTS
    // =========================================================================

    @Test
    void testWildcardExcludesAdminCategory() {
        List<String> result = RouteResolver.resolve(Arrays.asList("*"));

        assertFalse(result.contains(Endpoints.ADMIN_CUSTOMERS));
    }

    @Test
    void testAdminCategoryCanBeExplicitlyResolved() {
        List<String> result = RouteResolver.resolve(Arrays.asList("admin"));

        assertEquals(1, result.size());
        assertTrue(result.contains(Endpoints.ADMIN_CUSTOMERS));
    }
}
