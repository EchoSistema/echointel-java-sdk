package com.echosistema.echointel;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

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

    private Endpoints() {
    }

    public static Map<String, Map<String, String>> all() {
        Map<String, Map<String, String>> endpoints = new LinkedHashMap<>();

        // System
        Map<String, String> system = new LinkedHashMap<>();
        system.put("health", HEALTH);
        endpoints.put("system", system);

        // Forecasting
        Map<String, String> forecasting = new LinkedHashMap<>();
        forecasting.put("revenue", FORECAST_REVENUE);
        forecasting.put("cost", FORECAST_COST);
        forecasting.put("cost_improved", FORECAST_COST_IMPROVED);
        forecasting.put("units", FORECAST_UNITS);
        forecasting.put("cost_totus", FORECAST_COST_TOTUS);
        endpoints.put("forecasting", forecasting);

        // Inventory
        Map<String, String> inventory = new LinkedHashMap<>();
        inventory.put("optimization", INVENTORY_OPTIMIZATION);
        inventory.put("history_improved", INVENTORY_HISTORY_IMPROVED);
        endpoints.put("inventory", inventory);

        // Customer
        Map<String, String> customer = new LinkedHashMap<>();
        customer.put("segmentation", CUSTOMER_SEGMENTATION);
        customer.put("features", CUSTOMER_FEATURES);
        customer.put("loyalty", CUSTOMER_LOYALTY);
        customer.put("rfm", CUSTOMER_RFM);
        customer.put("clv_features", CUSTOMER_CLV_FEATURES);
        customer.put("clv_forecast", CUSTOMER_CLV_FORECAST);
        endpoints.put("customer", customer);

        // Churn
        Map<String, String> churn = new LinkedHashMap<>();
        churn.put("risk", CHURN_RISK);
        churn.put("label", CHURN_LABEL);
        endpoints.put("churn", churn);

        // NPS
        Map<String, String> nps = new LinkedHashMap<>();
        nps.put("calculate", NPS);
        endpoints.put("nps", nps);

        // Propensity
        Map<String, String> propensity = new LinkedHashMap<>();
        propensity.put("buy_product", PROPENSITY_BUY_PRODUCT);
        propensity.put("respond_campaign", PROPENSITY_RESPOND_CAMPAIGN);
        propensity.put("upgrade_plan", PROPENSITY_UPGRADE_PLAN);
        endpoints.put("propensity", propensity);

        // Recommendations
        Map<String, String> recommendations = new LinkedHashMap<>();
        recommendations.put("user_items", RECOMMEND_USER_ITEMS);
        recommendations.put("similar_items", RECOMMEND_SIMILAR_ITEMS);
        endpoints.put("recommendations", recommendations);

        // Cross-Sell
        Map<String, String> crossSell = new LinkedHashMap<>();
        crossSell.put("matrix", CROSS_SELL_MATRIX);
        crossSell.put("upsell", UPSELL_SUGGESTIONS);
        endpoints.put("cross_sell", crossSell);

        // Pricing
        Map<String, String> pricing = new LinkedHashMap<>();
        pricing.put("dynamic", DYNAMIC_PRICING_RECOMMEND);
        endpoints.put("pricing", pricing);

        // Sentiment
        Map<String, String> sentiment = new LinkedHashMap<>();
        sentiment.put("report", SENTIMENT_REPORT);
        sentiment.put("realtime", SENTIMENT_REALTIME);
        endpoints.put("sentiment", sentiment);

        // Anomaly
        Map<String, String> anomaly = new LinkedHashMap<>();
        anomaly.put("transactions", ANOMALY_TRANSACTIONS);
        anomaly.put("accounts", ANOMALY_ACCOUNTS);
        anomaly.put("graph", ANOMALY_GRAPH);
        endpoints.put("anomaly", anomaly);

        // Credit Risk
        Map<String, String> creditRisk = new LinkedHashMap<>();
        creditRisk.put("score", CREDIT_RISK_SCORE);
        creditRisk.put("explain", CREDIT_RISK_EXPLAIN);
        endpoints.put("credit_risk", creditRisk);

        // Attribution
        Map<String, String> attribution = new LinkedHashMap<>();
        attribution.put("channel", CHANNEL_ATTRIBUTION);
        attribution.put("uplift", UPLIFT_MODEL);
        endpoints.put("attribution", attribution);

        // Journey
        Map<String, String> journey = new LinkedHashMap<>();
        journey.put("markov", JOURNEY_MARKOV);
        journey.put("sequences", JOURNEY_SEQUENCES);
        endpoints.put("journey", journey);

        // NLP
        Map<String, String> nlp = new LinkedHashMap<>();
        nlp.put("analysis", NLP_ANALYSIS);
        nlp.put("analysis_en", NLP_ANALYSIS_EN);
        nlp.put("excess_inventory", NLP_EXCESS_INVENTORY_REPORT);
        nlp.put("sanitize", SANITIZE_TEXT);
        endpoints.put("nlp", nlp);

        // Segmentation Admin
        Map<String, String> segmentationAdmin = new LinkedHashMap<>();
        segmentationAdmin.put("purchasing", PURCHASING_SEGMENTATION);
        segmentationAdmin.put("dendrogram", PURCHASING_SEGMENTATION_DENDROGRAM);
        segmentationAdmin.put("hierarchy", SEGMENT_HIERARCHY_CHART);
        segmentationAdmin.put("subsegment", SEGMENT_SUBSEGMENT_EXPLORE);
        segmentationAdmin.put("profiles", SEGMENT_CLUSTER_PROFILES);
        endpoints.put("segmentation_admin", segmentationAdmin);

        // Reporting Admin
        Map<String, String> reportingAdmin = new LinkedHashMap<>();
        reportingAdmin.put("report", SEGMENTATION_REPORT);
        reportingAdmin.put("report_i18n", SEGMENTATION_REPORT_I18N);
        reportingAdmin.put("report_json", SEGMENTATION_REPORT_JSON);
        endpoints.put("reporting_admin", reportingAdmin);

        // Admin
        Map<String, String> admin = new LinkedHashMap<>();
        admin.put("customers", ADMIN_CUSTOMERS);
        endpoints.put("admin", admin);

        return Collections.unmodifiableMap(endpoints);
    }
}
