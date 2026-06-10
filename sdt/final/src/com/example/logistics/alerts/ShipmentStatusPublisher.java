package com.example.logistics.alerts;

import java.util.ArrayList;
import java.util.List;

public final class ShipmentStatusPublisher {
    private final List<ShipmentObserver> observers = new ArrayList<>();

    private ShipmentStatusPublisher() {
    }

    public static ShipmentStatusPublisher create() {
        return new ShipmentStatusPublisher();
    }

    public void subscribe(ShipmentObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void unsubscribe(ShipmentObserver observer) {
        observers.remove(observer);
    }

    public int subscriberCount() {
        return observers.size();
    }

    public void publish(String shipmentId, String oldStatus, String newStatus) {
        for (ShipmentObserver observer : List.copyOf(observers)) {
            observer.onStatusUpdate(shipmentId, oldStatus, newStatus);
        }
    }
}
