package com.echosistema.echointel;

import java.util.*;
import java.util.stream.Collectors;

public final class RouteResolver {

    private static final List<String> ADMIN_CATEGORIES = Collections.singletonList("admin");

    private RouteResolver() {
    }

    public static List<String> resolve(List<String> routes) {
        if (routes == null || routes.isEmpty()) {
            return Collections.emptyList();
        }

        if (routes.contains("*")) {
            return resolveWildcard();
        }

        Set<String> resolved = new LinkedHashSet<>();
        for (String route : routes) {
            resolved.addAll(resolveRoute(route));
        }

        return new ArrayList<>(resolved);
    }

    private static List<String> resolveWildcard() {
        Map<String, Map<String, String>> all = Endpoints.all();
        List<String> resolved = new ArrayList<>();

        for (Map.Entry<String, Map<String, String>> entry : all.entrySet()) {
            String category = entry.getKey();
            if (ADMIN_CATEGORIES.contains(category)) {
                continue;
            }
            resolved.addAll(entry.getValue().values());
        }

        return resolved;
    }

    private static List<String> resolveRoute(String route) {
        Map<String, Map<String, String>> all = Endpoints.all();

        if (!route.contains(".")) {
            return resolveCategoryRoutes(route, all);
        }

        return resolveSpecificRoute(route, all);
    }

    private static List<String> resolveCategoryRoutes(String category, Map<String, Map<String, String>> all) {
        if (!all.containsKey(category)) {
            return Collections.singletonList(category);
        }

        return new ArrayList<>(all.get(category).values());
    }

    private static List<String> resolveSpecificRoute(String route, Map<String, Map<String, String>> all) {
        String[] parts = route.split("\\.", 2);
        String category = parts[0];
        String endpoint = parts[1];

        if (!all.containsKey(category) || !all.get(category).containsKey(endpoint)) {
            return Collections.singletonList(route);
        }

        return Collections.singletonList(all.get(category).get(endpoint));
    }

    public static List<String> categories() {
        return new ArrayList<>(Endpoints.all().keySet());
    }

    public static List<String> endpoints(String category) {
        Map<String, Map<String, String>> all = Endpoints.all();
        if (!all.containsKey(category)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(all.get(category).keySet());
    }
}
