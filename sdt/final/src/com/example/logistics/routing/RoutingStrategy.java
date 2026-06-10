package com.example.logistics.routing;

import com.example.logistics.cargo.Cargo;

public interface RoutingStrategy {
    String mode();

    RouteQuote estimate(Cargo cargo, Route route);
}
