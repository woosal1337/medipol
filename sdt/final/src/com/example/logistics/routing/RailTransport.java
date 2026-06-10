package com.example.logistics.routing;

import com.example.logistics.cargo.Cargo;

import java.util.ArrayList;
import java.util.List;

final class RailTransport implements RoutingStrategy {
    private static final double NETWORK_SPEED_KMH = 60.0;
    private static final double YARD_DELAY_HOURS = 24.0;
    private static final double BASE_FEE_USD = 60.0;
    private static final double PER_KG_PER_1000KM_USD = 0.08;
    private static final double HAZMAT_MULTIPLIER = 1.15;

    @Override
    public String mode() {
        return "RailTransport";
    }

    @Override
    public RouteQuote estimate(Cargo cargo, Route route) {
        List<String> notes = new ArrayList<>();
        double transport = BASE_FEE_USD + PER_KG_PER_1000KM_USD * cargo.weightKg() * route.distanceKm() / 1000.0;
        if (carriesHazmat(cargo)) {
            transport *= HAZMAT_MULTIPLIER;
            notes.add("segregated hazmat wagon x1.15");
        }
        double transitHours = route.distanceKm() / NETWORK_SPEED_KMH + YARD_DELAY_HOURS;
        return RouteQuote.of(mode(), transport + cargo.handlingSurchargeUsd(), transitHours, notes);
    }

    private static boolean carriesHazmat(Cargo cargo) {
        return cargo.handlingLabels().stream().anyMatch(label -> label.startsWith("HAZMAT"));
    }
}
