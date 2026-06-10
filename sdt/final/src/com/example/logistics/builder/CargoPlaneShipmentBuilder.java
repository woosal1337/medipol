package com.example.logistics.builder;

import com.example.logistics.cargo.Cargo;
import com.example.logistics.routing.Route;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class CargoPlaneShipmentBuilder {
    public static final List<String> REQUIRED_TSA_CHECKS = List.of(
            "X-ray manifest scan",
            "Explosives trace detection",
            "Dangerous goods declaration audit",
            "Chain-of-custody seal verification");

    private static final double MAX_HOLD_SHARE = 0.45;

    private String flightNumber;
    private String aircraftModel;
    private double maxPayloadKg;
    private Route route;
    private final Map<Hold, List<Cargo>> manifest = new EnumMap<>(Hold.class);
    private final List<String> securityLog = new ArrayList<>();
    private boolean weightDistributionVerified = false;

    CargoPlaneShipmentBuilder() {
        for (Hold hold : Hold.values()) {
            manifest.put(hold, new ArrayList<>());
        }
    }

    public CargoPlaneShipmentBuilder forFlight(String flightNumber, String aircraftModel, double maxPayloadKg) {
        this.flightNumber = flightNumber;
        this.aircraftModel = aircraftModel;
        this.maxPayloadKg = maxPayloadKg;
        return this;
    }

    public CargoPlaneShipmentBuilder route(Route route) {
        this.route = route;
        return this;
    }

    public CargoPlaneShipmentBuilder loadHold(Hold hold, Cargo cargo) {
        manifest.get(hold).add(cargo);
        weightDistributionVerified = false;
        return this;
    }

    public double holdWeightKg(Hold hold) {
        return manifest.get(hold).stream().mapToDouble(Cargo::weightKg).sum();
    }

    public CargoPlaneShipmentBuilder tsaCheck(String checkName) {
        securityLog.add("PASS " + checkName);
        return this;
    }

    public CargoPlaneShipmentBuilder verifyWeightDistribution() {
        double total = totalPayloadKg();
        if (total <= 0) {
            throw new IllegalStateException("cannot balance an empty manifest");
        }
        for (Hold hold : Hold.values()) {
            double share = holdWeightKg(hold) / total;
            if (share > MAX_HOLD_SHARE) {
                throw new IllegalStateException(String.format(
                        "%s hold carries %.1f%% of payload, limit is %.0f%%",
                        hold, share * 100.0, MAX_HOLD_SHARE * 100.0));
            }
        }
        weightDistributionVerified = true;
        return this;
    }

    public CargoPlaneShipment build() {
        require(flightNumber != null && aircraftModel != null, "flight spec missing (call forFlight)");
        require(route != null, "route missing (call route)");
        require(totalPayloadKg() > 0, "manifest is empty (call loadHold)");
        require(totalPayloadKg() <= maxPayloadKg, String.format(
                "payload %,.0f kg exceeds max %,.0f kg", totalPayloadKg(), maxPayloadKg));
        require(weightDistributionVerified, "weight distribution not verified (call verifyWeightDistribution)");
        for (String check : REQUIRED_TSA_CHECKS) {
            require(securityLog.contains("PASS " + check), "missing TSA check: " + check);
        }
        return new CargoPlaneShipment(flightNumber, aircraftModel, maxPayloadKg, route, manifest, securityLog);
    }

    private double totalPayloadKg() {
        return manifest.values().stream()
                .flatMap(List::stream)
                .mapToDouble(Cargo::weightKg)
                .sum();
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}
