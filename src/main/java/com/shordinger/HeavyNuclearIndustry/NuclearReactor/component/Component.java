package com.shordinger.HeavyNuclearIndustry.NuclearReactor.component;

import net.minecraft.nbt.NBTTagCompound;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.fuel.FuelPackage;
import com.shordinger.HeavyNuclearIndustry.NuclearReactor.reactor.Reactor;

public class Component {

    private Component preview;
    private int neutronsReceived;
    private int neutronsEnergyReceived;
    private long maxHeat;
    private long heat;
    private long EUGenerated;
    private long damaged;
    private final FuelPackage fuel;
    private long maxWeight;
    private long remain;
    private final float energyMultiplier;
    private final float neutronMultiplier;
    private final float EUMultiplier;
    public final boolean isPreview;

    public Component(float energyMultiplier, float neutronMultiplier, float euMultiplier, FuelPackage fuel,
        boolean isPreview) {
        preview = new Component(energyMultiplier, neutronMultiplier, euMultiplier, fuel);
        this.energyMultiplier = energyMultiplier;
        this.neutronMultiplier = neutronMultiplier;
        EUMultiplier = euMultiplier;
        this.fuel = fuel;
        this.isPreview = false;
    }

    protected Component(float energyMultiplier, float neutronMultiplier, float euMultiplier, FuelPackage fuel) {
        isPreview = true;
        this.fuel = fuel;
        this.energyMultiplier = energyMultiplier;
        this.neutronMultiplier = neutronMultiplier;
        EUMultiplier = euMultiplier;
    }

    private void move() {
        neutronsReceived = preview.neutronsEnergyReceived;
        neutronsEnergyReceived = preview.neutronsEnergyReceived;
        heat = preview.heat;
        EUGenerated = preview.EUGenerated;
        damaged = preview.damaged;
        remain = preview.remain;
    }

    public Component getPreview() {
        return preview;
    }

    public Component flush() {
        move();
        return this;
    }

    public boolean process(Reactor reactor, int x, int y, int z) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (reactor.getComponent(x + i, y + j, z + k)
                        .consume(this)) {

                    } else {

                    }
                }
            }
        }
        return true;
    }

    public boolean consume(Component component) {
        return true;
    }

    public void setByNBT(NBTTagCompound tag) {

    }

    public NBTTagCompound saveToNBT(NBTTagCompound tag) {
        return tag;
    }
}
