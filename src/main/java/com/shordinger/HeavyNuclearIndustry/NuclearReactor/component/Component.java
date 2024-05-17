package com.shordinger.HeavyNuclearIndustry.NuclearReactor.component;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.IProcessable;
import com.shordinger.HeavyNuclearIndustry.warpper.InstanceDecoder;
import net.minecraft.nbt.NBTTagCompound;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.fuel.FuelPackage;
import com.shordinger.HeavyNuclearIndustry.NuclearReactor.reactor.Reactor;

import static com.shordinger.HeavyNuclearIndustry.NuclearReactor.component.EmptyComponent.EMPTY;

public class Component implements IProcessable {

    private static InstanceDecoder<Component> decoder;
    protected Component preview;
    protected int neutronsReceived;
    protected int neutronsEnergyReceived;
    protected final long maxHeat;
    protected long heat;
    protected long EUGenerated;
    //this is not the percentage of fuel reminded, this is the health of that component;
    protected long damaged;
    protected final FuelPackage fuel;
    protected long maxWeight;
    protected final float heatMultiplier;
    protected final float neutronMultiplier;
    protected final float EUMultiplier;
    public final boolean isPreview;

    public Component(float heatMultiplier, float neutronMultiplier, float euMultiplier, FuelPackage fuel,
                     long maxHeat, boolean isPreview) {
        this.maxHeat = maxHeat;
        preview = new Component(maxHeat, heatMultiplier, neutronMultiplier, euMultiplier, fuel);
        this.heatMultiplier = heatMultiplier;
        this.neutronMultiplier = neutronMultiplier;
        EUMultiplier = euMultiplier;
        this.fuel = fuel;
        this.isPreview = isPreview;
    }

    protected Component(long maxHeat, float heatMultiplier, float neutronMultiplier, float euMultiplier, FuelPackage fuel) {
        this.maxHeat = maxHeat;
        isPreview = true;
        this.fuel = fuel;
        this.heatMultiplier = heatMultiplier;
        this.neutronMultiplier = neutronMultiplier;
        EUMultiplier = euMultiplier;
    }

    private void move() {
        neutronsReceived = preview.neutronsReceived;
        neutronsEnergyReceived = preview.neutronsEnergyReceived;
        heat = preview.heat;
        EUGenerated = preview.EUGenerated;
        damaged = preview.damaged;
        preview.clear();

    }

    public void clear() {
        neutronsEnergyReceived = 0;
        neutronsReceived = 0;
        heat = 0;
        EUGenerated = 0;
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
                    if (x + i < 0 || x + i > 15 || y + j < 0 || y + j > 15 || z + k < 0 || z + k > 15) continue;
                    if (consume(reactor.getComponent(x + i, y + j, z + k), count)) {
                        reactor.setComponents(x + i, y + j, z + k, EMPTY);
                    }
                }
            }
        }
        fuel.use();
        return ProcessRun();
    }

    public boolean consume(Component receive, int count) {
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
        return true;
    }

    @Override
    public boolean process() {
        return true;
    }

    @Override
    public boolean postProcess() {
        return true;
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
