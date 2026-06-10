package com.example.logistics.builder;

import com.example.logistics.cargo.Cargo;
import com.example.logistics.routing.Route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class ShipmentDirector {
    private ShipmentDirector() {
    }

    public static CargoPlaneShipment fullyLoadedFreighter(String flightNumber, Route route, List<Cargo> manifest) {
        CargoPlaneShipmentBuilder builder = CargoPlaneShipment.builder()
                .forFlight(flightNumber, "Airbus A310-300F", 39_000)
                .route(route);

        List<Cargo> heaviestFirst = new ArrayList<>(manifest);
        heaviestFirst.sort(Comparator.comparingDouble(Cargo::weightKg).reversed());
        for (Cargo cargo : heaviestFirst) {
            builder.loadHold(lightestHold(builder), cargo);
        }

        for (String check : CargoPlaneShipmentBuilder.REQUIRED_TSA_CHECKS) {
            builder.tsaCheck(check);
        }

        return builder.verifyWeightDistribution().build();
    }

    private static Hold lightestHold(CargoPlaneShipmentBuilder builder) {
        Hold lightest = Hold.FORWARD;
        for (Hold hold : Hold.values()) {
            if (builder.holdWeightKg(hold) < builder.holdWeightKg(lightest)) {
                lightest = hold;
            }
        }
        return lightest;
    }
}
