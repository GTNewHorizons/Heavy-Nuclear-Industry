package com.shordinger.HeavyNuclearIndustry.NuclearReactor.turbine;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.IProcessable;
import com.shordinger.HeavyNuclearIndustry.NuclearReactor.reactor.Reactor;
import com.shordinger.HeavyNuclearIndustry.warpper.InstanceDecoder;

public class Turbine implements IProcessable {

    private static InstanceDecoder<Reactor> decoder;
    private final long[] supercriticalSteamFlux = new long[] {};
    private final long[] CriticalSteamFlux = new long[] {};
    private final long[] steamFlux = new long[] {};
    private int tier;

    public boolean process(int period) {
        return false;
    }

    @Override
    public boolean ProcessRun() {
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
