package com.example.logistics.cargo;

import java.util.List;

public interface Cargo {
    String contents();

    double weightKg();

    double volumeM3();

    double declaredValueUsd();

    double handlingSurchargeUsd();

    List<String> handlingLabels();

    default String describe() {
        return String.format("%-28s | %,8.0f kg | %6.1f m3 | value $%,11.0f | handling $%,9.2f | %s",
                contents(), weightKg(), volumeM3(), declaredValueUsd(), handlingSurchargeUsd(),
                handlingLabels().isEmpty() ? "[no labels]" : String.join(", ", handlingLabels()));
    }
}
