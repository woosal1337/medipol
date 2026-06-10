package com.example.logistics.routing;

import java.util.List;

public final class RouteQuote {
    private final String mode;
    private final double costUsd;
    private final double transitHours;
    private final List<String> notes;

    private RouteQuote(String mode, double costUsd, double transitHours, List<String> notes) {
        this.mode = mode;
        this.costUsd = costUsd;
        this.transitHours = transitHours;
        this.notes = List.copyOf(notes);
    }

    public static RouteQuote of(String mode, double costUsd, double transitHours, List<String> notes) {
        return new RouteQuote(mode, costUsd, transitHours, notes);
    }

    public String mode() {
        return mode;
    }

    public double costUsd() {
        return costUsd;
    }

    public double transitHours() {
        return transitHours;
    }

    public List<String> notes() {
        return notes;
    }

    public String format() {
        return String.format("%-15s | cost $%,11.2f | transit %6.1f h | %s",
                mode, costUsd, transitHours, notes.isEmpty() ? "-" : String.join("; ", notes));
    }
}
