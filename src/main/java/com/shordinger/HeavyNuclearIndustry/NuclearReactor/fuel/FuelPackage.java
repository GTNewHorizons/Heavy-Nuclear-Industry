package com.shordinger.HeavyNuclearIndustry.NuclearReactor.fuel;

public class FuelPackage {

    private Fuel fuel;
    private long amount;

    public boolean use(int amount) {
        this.amount -= amount;
        return this.amount >= 0;
    }

    public boolean use() {
        return use(1);
    }
}
