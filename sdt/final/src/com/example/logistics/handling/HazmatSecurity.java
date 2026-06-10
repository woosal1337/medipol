package com.example.logistics.handling;

import com.example.logistics.cargo.Cargo;

import java.util.List;

public final class HazmatSecurity extends CargoDecorator {
    private static final double PLACARD_KIT_WEIGHT_KG = 15.0;
    private static final double SCREENING_FEE_USD = 250.0;
    private static final double PER_KG_FEE_USD = 0.10;

    public HazmatSecurity(Cargo wrapped) {
        super(wrapped);
    }

    @Override
    public double weightKg() {
        return wrapped.weightKg() + PLACARD_KIT_WEIGHT_KG;
    }

    @Override
    public double handlingSurchargeUsd() {
        return wrapped.handlingSurchargeUsd() + SCREENING_FEE_USD + PER_KG_FEE_USD * wrapped.weightKg();
    }

    @Override
    public List<String> handlingLabels() {
        return withLabel("HAZMAT-CLASS-3");
    }
}
