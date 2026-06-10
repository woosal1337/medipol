package com.example.logistics.alerts;

public final class EmailNotificationService implements ShipmentObserver {
    private final String recipient;

    private EmailNotificationService(String recipient) {
        this.recipient = recipient;
    }

    public static EmailNotificationService to(String recipient) {
        return new EmailNotificationService(recipient);
    }

    @Override
    public void onStatusUpdate(String shipmentId, String oldStatus, String newStatus) {
        System.out.printf("  [EMAIL]     to %-19s | subject: \"%s update: %s\"%n",
                recipient, shipmentId, newStatus);
    }
}
