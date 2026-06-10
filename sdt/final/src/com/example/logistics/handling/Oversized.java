package com.example.logistics.handling;

import com.example.logistics.cargo.Cargo;

import java.util.List;

public final class Oversized extends CargoDecorator {
    private static final double DUNNAGE_VOLUME_FACTOR = 1.25;
    private static final double PERMIT_FEE_USD = 400.0;

    public Oversized(Cargo wrapped) {
        super(wrapped);
    }

    @Override
    public double volumeM3() {
        return wrapped.volumeM3() * DUNNAGE_VOLUME_FACTOR;
    }

    @Override
    public double handlingSurchargeUsd() {
        return wrapped.handlingSurchargeUsd() + PERMIT_FEE_USD;
    }

    @Override
    public List<String> handlingLabels() {
        return withLabel("OVERSIZED-LOAD");
    }
}
