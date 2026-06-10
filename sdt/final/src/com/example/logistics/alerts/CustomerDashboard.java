package com.example.logistics.alerts;

public final class CustomerDashboard implements ShipmentObserver {
    private final String customerName;

    private CustomerDashboard(String customerName) {
        this.customerName = customerName;
    }

    public static CustomerDashboard forCustomer(String customerName) {
        return new CustomerDashboard(customerName);
    }

    @Override
    public void onStatusUpdate(String shipmentId, String oldStatus, String newStatus) {
        System.out.printf("  [DASHBOARD] %-22s | %s is now \"%s\" (was \"%s\")%n",
                customerName, shipmentId, newStatus, oldStatus);
    }
}
