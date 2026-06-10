package com.example.logistics.routing;

public final class RoutingStrategies {
    private RoutingStrategies() {
    }

    public static RoutingStrategy airFreight() {
        return new AirFreight();
    }

    public static RoutingStrategy railTransport() {
        return new RailTransport();
    }

    public static RoutingStrategy groundShipping() {
        return new GroundShipping();
    }
}
