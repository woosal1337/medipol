package com.example.logistics.handling;

import com.example.logistics.cargo.Cargo;

import java.util.List;

public final class FragilePackaging extends CargoDecorator {
    private static final double CRATING_WEIGHT_FACTOR = 1.05;
    private static final double FLAT_FEE_USD = 75.0;
    private static final double VALUE_RISK_RATE = 0.015;

    public FragilePackaging(Cargo wrapped) {
        super(wrapped);
    }

    @Override
    public double weightKg() {
        return wrapped.weightKg() * CRATING_WEIGHT_FACTOR;
    }

    @Override
    public double handlingSurchargeUsd() {
        return wrapped.handlingSurchargeUsd() + FLAT_FEE_USD + VALUE_RISK_RATE * wrapped.declaredValueUsd();
    }

    @Override
    public List<String> handlingLabels() {
        return withLabel("FRAGILE-THIS-SIDE-UP");
    }
}
