package com.shordinger.HeavyNuclearIndustry.NuclearReactor.component;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.IProcessable;
import com.shordinger.HeavyNuclearIndustry.warpper.InstanceDecoder;
import net.minecraft.nbt.NBTTagCompound;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.fuel.FuelPackage;
import com.shordinger.HeavyNuclearIndustry.NuclearReactor.reactor.Reactor;

public class Component implements IProcessable {

    private static InstanceDecoder<Component> decoder;
    private Component preview;
    private int neutronsReceived;
    private int neutronsEnergyReceived;
    private long maxHeat;
    private long heat;
    private long EUGenerated;
    //this is not the percentage of fuel reminded, this is the health of that component;
    private long damaged;
    private final FuelPackage fuel;
    private long maxWeight;
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
    }

    public Component getPreview() {
        return preview;
    }

    public Component flush() {
        move();
        return this;
    }

    static final int[] counts = new int[]{26, 17, 11, 7};

    public boolean process(Reactor reactor, int x, int y, int z) {
        int count = 0;
        if (x == 0 || x == 15) count++;
        if (y == 0 || y == 15) count++;
        if (z == 0 || z == 15) count++;
        count = counts[count];
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (i == 0 && j == 0 && k == 0) continue;
                    reactor.getComponent(x + i, y + j, z + k).consume(this, count);
                }
            }
        }
        return ProcessRun();
    }

    public boolean consume(Component component, int count) {
        return true;
    }

    public void setByNBT(NBTTagCompound tag) {

    }

    public NBTTagCompound saveToNBT(NBTTagCompound tag) {
        return tag;
    }

    @Override
    public boolean ProcessRun() {
        return preProcess() && process() && postProcess();
    }

    @Override
    public boolean preProcess() {
        return false;
    }

    @Override
    public boolean process() {
        return false;
    }

    @Override
    public boolean postProcess() {
        return false;
    }

    @Override
    public ProcessStatus status() {
        return ProcessStatus.processing;
    }

    @Override
    public void encoder(byte[] buffer) {

    }

    @Override
    public void decoder(byte[] buffer) {

    }

    @Override
    public int getMemoryByte() {
        return 0;
    }
}
