package com.example.logistics.handling;

import com.example.logistics.cargo.Cargo;

import java.util.ArrayList;
import java.util.List;

public abstract class CargoDecorator implements Cargo {
    protected final Cargo wrapped;

    protected CargoDecorator(Cargo wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String contents() {
        return wrapped.contents();
    }

    @Override
    public double weightKg() {
        return wrapped.weightKg();
    }

    @Override
    public double volumeM3() {
        return wrapped.volumeM3();
    }

    @Override
    public double declaredValueUsd() {
        return wrapped.declaredValueUsd();
    }

    @Override
    public double handlingSurchargeUsd() {
        return wrapped.handlingSurchargeUsd();
    }

    @Override
    public List<String> handlingLabels() {
        return wrapped.handlingLabels();
    }

    protected final List<String> withLabel(String label) {
        List<String> labels = new ArrayList<>(wrapped.handlingLabels());
        labels.add(label);
        return List.copyOf(labels);
    }
}
