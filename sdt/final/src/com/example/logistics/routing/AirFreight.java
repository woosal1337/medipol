package com.example.logistics.routing;

import com.example.logistics.cargo.Cargo;

import java.util.ArrayList;
import java.util.List;

final class AirFreight implements RoutingStrategy {
    private static final double CRUISE_SPEED_KMH = 850.0;
    private static final double GROUND_HANDLING_HOURS = 6.0;
    private static final double BASE_FEE_USD = 120.0;
    private static final double PER_KG_PER_1000KM_USD = 0.45;
    private static final double HAZMAT_MULTIPLIER = 1.35;

    @Override
    public String mode() {
        return "AirFreight";
    }

    @Override
    public RouteQuote estimate(Cargo cargo, Route route) {
        List<String> notes = new ArrayList<>();
        double transport = BASE_FEE_USD + PER_KG_PER_1000KM_USD * cargo.weightKg() * route.distanceKm() / 1000.0;
        if (carriesHazmat(cargo)) {
            transport *= HAZMAT_MULTIPLIER;
            notes.add("IATA dangerous-goods surcharge x1.35");
        }
        double transitHours = route.distanceKm() / CRUISE_SPEED_KMH + GROUND_HANDLING_HOURS;
        return RouteQuote.of(mode(), transport + cargo.handlingSurchargeUsd(), transitHours, notes);
    }

    private static boolean carriesHazmat(Cargo cargo) {
        return cargo.handlingLabels().stream().anyMatch(label -> label.startsWith("HAZMAT"));
    }
}
