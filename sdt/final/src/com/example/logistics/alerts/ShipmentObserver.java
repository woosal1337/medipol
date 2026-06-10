package com.example.logistics.alerts;

public interface ShipmentObserver {
    void onStatusUpdate(String shipmentId, String oldStatus, String newStatus);
}
