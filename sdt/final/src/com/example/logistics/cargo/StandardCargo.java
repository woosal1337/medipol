package com.example.logistics.cargo;

import java.util.List;

public final class StandardCargo implements Cargo {
    private final String contents;
    private final double weightKg;
    private final double volumeM3;
    private final double declaredValueUsd;

    private StandardCargo(String contents, double weightKg, double volumeM3, double declaredValueUsd) {
        this.contents = contents;
        this.weightKg = weightKg;
        this.volumeM3 = volumeM3;
        this.declaredValueUsd = declaredValueUsd;
    }

    public static StandardCargo of(String contents, double weightKg, double volumeM3, double declaredValueUsd) {
        return new StandardCargo(contents, weightKg, volumeM3, declaredValueUsd);
    }

    @Override
    public String contents() {
        return contents;
    }

    @Override
    public double weightKg() {
        return weightKg;
    }

    @Override
    public double volumeM3() {
        return volumeM3;
    }

    @Override
    public double declaredValueUsd() {
        return declaredValueUsd;
    }

    @Override
    public double handlingSurchargeUsd() {
        return 0.0;
    }

    @Override
    public List<String> handlingLabels() {
        return List.of();
    }
}
