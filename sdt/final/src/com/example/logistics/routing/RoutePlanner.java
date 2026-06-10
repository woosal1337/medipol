package com.example.logistics.routing;

import com.example.logistics.cargo.Cargo;

import java.util.Objects;

public final class RoutePlanner {
    private RoutingStrategy strategy;

    private RoutePlanner(RoutingStrategy strategy) {
        this.strategy = strategy;
    }

    public static RoutePlanner using(RoutingStrategy strategy) {
        return new RoutePlanner(Objects.requireNonNull(strategy, "strategy"));
    }

    public RoutePlanner use(RoutingStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy");
        return this;
    }

    public RouteQuote plan(Cargo cargo, Route route) {
        return strategy.estimate(cargo, route);
    }
}
