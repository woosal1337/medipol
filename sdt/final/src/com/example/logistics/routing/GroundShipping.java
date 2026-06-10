package com.example.logistics.routing;

import com.example.logistics.cargo.Cargo;

import java.util.ArrayList;
import java.util.List;

final class GroundShipping implements RoutingStrategy {
    private static final double TRUCK_SPEED_KMH = 65.0;
    private static final double REST_STOP_FACTOR = 1.25;
    private static final double PER_KM_USD = 1.10;
    private static final double PER_KG_USD = 0.02;
    private static final double HAZMAT_ESCORT_FEE_USD = 500.0;

    @Override
    public String mode() {
        return "GroundShipping";
    }

    @Override
    public RouteQuote estimate(Cargo cargo, Route route) {
        List<String> notes = new ArrayList<>();
        double transport = PER_KM_USD * route.distanceKm() + PER_KG_USD * cargo.weightKg();
        if (carriesHazmat(cargo)) {
            transport += HAZMAT_ESCORT_FEE_USD;
            notes.add("ADR escort vehicle +$500");
        }
        double transitHours = route.distanceKm() / TRUCK_SPEED_KMH * REST_STOP_FACTOR;
        return RouteQuote.of(mode(), transport + cargo.handlingSurchargeUsd(), transitHours, notes);
    }

    private static boolean carriesHazmat(Cargo cargo) {
        return cargo.handlingLabels().stream().anyMatch(label -> label.startsWith("HAZMAT"));
    }
}
