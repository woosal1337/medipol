package com.example.logistics.routing;

public final class Route {
    private final String origin;
    private final String destination;
    private final double distanceKm;

    private Route(String origin, String destination, double distanceKm) {
        this.origin = origin;
        this.destination = destination;
        this.distanceKm = distanceKm;
    }

    public static Route of(String origin, String destination, double distanceKm) {
        return new Route(origin, destination, distanceKm);
    }

    public String origin() {
        return origin;
    }

    public String destination() {
        return destination;
    }

    public double distanceKm() {
        return distanceKm;
    }

    public String describe() {
        return String.format("%s -> %s (%,.0f km)", origin, destination, distanceKm);
    }
}
