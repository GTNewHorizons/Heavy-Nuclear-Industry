package com.shordinger.HeavyNuclearIndustry.NuclearReactor.coolantTower;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.IProcessable;
import com.shordinger.HeavyNuclearIndustry.warpper.InstanceDecoder;

public class CoolantTower implements IProcessable {

    private static InstanceDecoder<CoolantTower> decoder;
    private int tier;

    public boolean process(int period) {
        return false;
    }

    @Override
    public boolean run() {
        return false;
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
    public boolean status() {
        return false;
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
