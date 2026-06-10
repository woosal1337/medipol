package com.example.logistics.handling;

import com.example.logistics.cargo.Cargo;

public enum HandlingFeature {
    REFRIGERATION {
        @Override
        public Cargo applyTo(Cargo cargo) {
            return new Refrigeration(cargo);
        }
    },

    FRAGILE_PACKAGING {
        @Override
        public Cargo applyTo(Cargo cargo) {
            return new FragilePackaging(cargo);
        }
    },

    HAZMAT_SECURITY {
        @Override
        public Cargo applyTo(Cargo cargo) {
            return new HazmatSecurity(cargo);
        }
    },

    OVERSIZED {
        @Override
        public Cargo applyTo(Cargo cargo) {
            return new Oversized(cargo);
        }
    };

    public abstract Cargo applyTo(Cargo cargo);
}
