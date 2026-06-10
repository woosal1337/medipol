package com.example.logistics.builder;

import com.example.logistics.alerts.ShipmentObserver;
import com.example.logistics.alerts.ShipmentStatusPublisher;
import com.example.logistics.cargo.Cargo;
import com.example.logistics.routing.Route;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class CargoPlaneShipment {
    private final String shipmentId;
    private final String aircraftModel;
    private final double maxPayloadKg;
    private final Route route;
    private final Map<Hold, List<Cargo>> manifest;
    private final List<String> securityLog;
    private final ShipmentStatusPublisher publisher = ShipmentStatusPublisher.create();

    private String status = "Created";

    CargoPlaneShipment(String shipmentId, String aircraftModel, double maxPayloadKg,
                       Route route, Map<Hold, List<Cargo>> manifest, List<String> securityLog) {
        this.shipmentId = shipmentId;
        this.aircraftModel = aircraftModel;
        this.maxPayloadKg = maxPayloadKg;
        this.route = route;
        EnumMap<Hold, List<Cargo>> copy = new EnumMap<>(Hold.class);
        manifest.forEach((hold, items) -> copy.put(hold, List.copyOf(items)));
        this.manifest = copy;
        this.securityLog = List.copyOf(securityLog);
    }

    public static CargoPlaneShipmentBuilder builder() {
        return new CargoPlaneShipmentBuilder();
    }

    public String shipmentId() {
        return shipmentId;
    }

    public String status() {
        return status;
    }

    public double totalPayloadKg() {
        return manifest.values().stream()
                .flatMap(List::stream)
                .mapToDouble(Cargo::weightKg)
                .sum();
    }

    public double holdWeightKg(Hold hold) {
        return manifest.get(hold).stream().mapToDouble(Cargo::weightKg).sum();
    }

    public void subscribe(ShipmentObserver observer) {
        publisher.subscribe(observer);
    }

    public void unsubscribe(ShipmentObserver observer) {
        publisher.unsubscribe(observer);
    }

    public void updateStatus(String newStatus) {
        String oldStatus = status;
        status = newStatus;
        publisher.publish(shipmentId, oldStatus, newStatus);
    }

    public String manifestSummary() {
        StringBuilder sb = new StringBuilder();
        double total = totalPayloadKg();
        sb.append(String.format("Shipment %s | %s | %s%n", shipmentId, aircraftModel, route.describe()));
        sb.append(String.format("Payload %,.0f kg of %,.0f kg max (%.1f%% load factor)%n",
                total, maxPayloadKg, total / maxPayloadKg * 100.0));
        for (Hold hold : Hold.values()) {
            double holdWeight = holdWeightKg(hold);
            sb.append(String.format("  %-7s hold | %,8.0f kg (%4.1f%% of payload)%n",
                    hold, holdWeight, holdWeight / total * 100.0));
            for (Cargo cargo : manifest.get(hold)) {
                sb.append(String.format("    - %s (%,.0f kg)%n", cargo.contents(), cargo.weightKg()));
            }
        }
        sb.append(String.format("TSA security log:%n"));
        securityLog.forEach(entry -> sb.append("    ").append(entry).append(System.lineSeparator()));
        sb.append(String.format("Status: %s | subscribers: %d", status, publisher.subscriberCount()));
        return sb.toString();
    }
}
