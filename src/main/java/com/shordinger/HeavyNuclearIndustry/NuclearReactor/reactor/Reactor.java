package com.shordinger.HeavyNuclearIndustry.NuclearReactor.reactor;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.component.Component;
import com.shordinger.HeavyNuclearIndustry.NuclearReactor.coolant.CoolantPackage;

public class Reactor {

    private int maxCoreHeat;
    private int EscapedRadiation;
    private CoolantPackage coolant;
    private CoolantPackage output;
    private final ArrayList<Integer> InsertionStatus = new ArrayList<>();
    private final ArrayList<Integer> IsolationStatus = new ArrayList<>();
    private long EUGenerated;

    private boolean fluidMode = false;
    private static final int[] maxSize = new int[]{0, 4, 6, 8};
    public int tier = 0;

    private final ArrayList<Component> components = new ArrayList<>();

    public Component getComponent(int i, int j, int k) {
        int x = maxSize[tier];
        return components.get(i * x * x + j * x + k);
    }

    public void setComponents(int i, int j, int k, Component component) {
        int x = maxSize[tier];
        components.set(i * x * x + j * x + k, component);
    }

    public long generateEU() {
        return 0;
    }

    public boolean doExplode() {
        return false;
    }

    public void setByNBT(NBTTagCompound tag) {

    }

    public NBTTagCompound saveToNBT(NBTTagCompound tag) {
        return tag;
    }

    public boolean fluidProcessing() {
        return false;
    }

    public boolean process(int period) {
        return false;
    }

}
