package com.shordinger.HeavyNuclearIndustry.NuclearReactor.fuel;

public class FuelPackage {

    private Fuel fuel;
    private long amount;

    public FuelPackage(Fuel fuel) {
        this.fuel = fuel;
        amount = fuel.depPeriod;
    }

    public boolean use(int amount) {
        if (this.amount < amount) return false;
        this.amount -= amount;
        return this.amount > 0;
    }

    public Object getMaterial() {
        return amount > 0 ? fuel.material : fuel.resultMaterial;
    }

    public boolean use() {
        return use(1);
    }

    public int getNeuEnergy() {
        return amount > 0 ? fuel.neutronsEnergy : 0;
    }

    public int getHeat() {
        return amount > 0 ? fuel.heatProcess : 0;
    }

    public int getNeuProcess() {
        return amount > 0 ? fuel.neutronProcess : 0;
    }
}
