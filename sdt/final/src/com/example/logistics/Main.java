package com.example.logistics;

import com.example.logistics.alerts.CustomerDashboard;
import com.example.logistics.alerts.EmailNotificationService;
import com.example.logistics.alerts.ShipmentObserver;
import com.example.logistics.alerts.WarehouseManager;
import com.example.logistics.builder.CargoPlaneShipment;
import com.example.logistics.builder.Hold;
import com.example.logistics.builder.ShipmentDirector;
import com.example.logistics.cargo.Cargo;
import com.example.logistics.cargo.StandardCargo;
import com.example.logistics.handling.HandlingFeature;
import com.example.logistics.routing.Route;
import com.example.logistics.routing.RoutePlanner;
import com.example.logistics.routing.RoutingStrategies;

import java.util.List;

public final class Main {
    public static void main(String[] args) {
        banner("1. DECORATOR | optional handling features stacked at runtime");
        Cargo vaccines = StandardCargo.of("Pharma vaccines (4 pallets)", 1_200, 3.2, 480_000);
        System.out.println("base               " + vaccines.describe());
        List<HandlingFeature> requested = List.of(
                HandlingFeature.REFRIGERATION,
                HandlingFeature.FRAGILE_PACKAGING,
                HandlingFeature.HAZMAT_SECURITY);
        for (HandlingFeature feature : requested) {
            vaccines = feature.applyTo(vaccines);
            System.out.printf("+ %-16s %s%n", feature, vaccines.describe());
        }

        banner("2. STRATEGY | one shipment, three interchangeable routing algorithms");
        Route route = Route.of("Istanbul (IST)", "Frankfurt (FRA)", 2_180);
        System.out.println("Route: " + route.describe());
        RoutePlanner planner = RoutePlanner.using(RoutingStrategies.airFreight());
        System.out.println(planner.plan(vaccines, route).format());
        planner.use(RoutingStrategies.railTransport());
        System.out.println(planner.plan(vaccines, route).format());
        planner.use(RoutingStrategies.groundShipping());
        System.out.println(planner.plan(vaccines, route).format());

        banner("3. BUILDER | director assembles a fully loaded CargoPlane");
        List<Cargo> manifest = List.of(
                vaccines,
                StandardCargo.of("Automotive parts", 5_400, 12.0, 240_000),
                StandardCargo.of("Steel coils", 9_000, 4.5, 95_000),
                StandardCargo.of("Coffee beans", 6_500, 18.0, 58_000),
                HandlingFeature.FRAGILE_PACKAGING.applyTo(
                        StandardCargo.of("Server racks", 3_100, 9.5, 410_000)),
                StandardCargo.of("Textile bales", 5_200, 22.0, 76_000));
        CargoPlaneShipment shipment = ShipmentDirector.fullyLoadedFreighter("MEP-441", route, manifest);
        System.out.println(shipment.manifestSummary());
        try {
            CargoPlaneShipment.builder()
                    .forFlight("MEP-442", "Airbus A310-300F", 39_000)
                    .route(route)
                    .loadHold(Hold.FORWARD, StandardCargo.of("Machine tools", 2_000, 6.0, 90_000))
                    .loadHold(Hold.CENTER, StandardCargo.of("Paper rolls", 2_100, 8.0, 12_000))
                    .loadHold(Hold.AFT, StandardCargo.of("Glassware", 1_900, 5.0, 45_000))
                    .verifyWeightDistribution()
                    .build();
        } catch (IllegalStateException rejected) {
            System.out.println("Guarded construction: build() rejected MEP-442 -> " + rejected.getMessage());
        }

        banner("4. OBSERVER | independent parties alerted on every status change");
        ShipmentObserver dashboard = CustomerDashboard.forCustomer("Acme Pharma GmbH");
        ShipmentObserver warehouse = WarehouseManager.onDuty("D. Kaya");
        ShipmentObserver email = EmailNotificationService.to("ops@acme.example");
        shipment.subscribe(dashboard);
        shipment.subscribe(warehouse);
        shipment.subscribe(email);
        shipment.updateStatus("Departed IST cargo terminal");
        System.out.println();
        shipment.updateStatus("Delayed due to weather");
        System.out.println("  -- warehouse manager goes off shift, unsubscribed --");
        shipment.unsubscribe(warehouse);
        System.out.println();
        shipment.updateStatus("Out for Delivery");

        banner("5. OCP PROOF | new Oversized feature, zero changes to base classes");
        Cargo turbineBlade = HandlingFeature.OVERSIZED.applyTo(
                StandardCargo.of("Wind turbine blade", 11_800, 96.0, 1_250_000));
        System.out.println(turbineBlade.describe());
        Route lastMile = Route.of("Frankfurt (FRA)", "Rotterdam Port", 580);
        System.out.println(RoutePlanner.using(RoutingStrategies.groundShipping())
                .plan(turbineBlade, lastMile).format());

        banner("Simulation complete: Builder, Decorator, Strategy, Observer all exercised");
    }

    private static void banner(String title) {
        System.out.println();
        System.out.println("=".repeat(100));
        System.out.println(title);
        System.out.println("=".repeat(100));
    }
}
