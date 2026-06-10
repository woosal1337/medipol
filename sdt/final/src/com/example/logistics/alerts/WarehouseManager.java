package com.example.logistics.alerts;

public final class WarehouseManager implements ShipmentObserver {
    private final String name;

    private WarehouseManager(String name) {
        this.name = name;
    }

    public static WarehouseManager onDuty(String name) {
        return new WarehouseManager(name);
    }

    @Override
    public void onStatusUpdate(String shipmentId, String oldStatus, String newStatus) {
        System.out.printf("  [WAREHOUSE] %-22s | %s \"%s\" -> action: %s%n",
                name, shipmentId, newStatus, actionFor(newStatus));
    }

    private static String actionFor(String newStatus) {
        if (newStatus.contains("Delayed")) {
            return "hold outbound dock slot, keep cargo in climate bay";
        }
        if (newStatus.contains("Out for Delivery")) {
            return "release dock slot, close yard ticket";
        }
        return "update yard plan";
    }
}
