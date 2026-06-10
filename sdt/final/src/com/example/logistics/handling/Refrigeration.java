package com.example.logistics.handling;

import com.example.logistics.cargo.Cargo;

import java.util.List;

public final class Refrigeration extends CargoDecorator {
    private static final double REEFER_UNIT_WEIGHT_KG = 180.0;
    private static final double REEFER_UNIT_VOLUME_M3 = 0.9;
    private static final double FLAT_FEE_USD = 200.0;
    private static final double POWER_FEE_PER_KG_USD = 0.35;

    public Refrigeration(Cargo wrapped) {
        super(wrapped);
    }

    @Override
    public double weightKg() {
        return wrapped.weightKg() + REEFER_UNIT_WEIGHT_KG;
    }

    @Override
    public double volumeM3() {
        return wrapped.volumeM3() + REEFER_UNIT_VOLUME_M3;
    }

    @Override
    public double handlingSurchargeUsd() {
        return wrapped.handlingSurchargeUsd() + FLAT_FEE_USD + POWER_FEE_PER_KG_USD * wrapped.weightKg();
    }

    @Override
    public List<String> handlingLabels() {
        return withLabel("KEEP-FROZEN(-18C)");
    }
}
