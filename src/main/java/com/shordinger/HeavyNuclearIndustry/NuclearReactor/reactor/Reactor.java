package com.shordinger.HeavyNuclearIndustry.NuclearReactor.reactor;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.IProcessable;
import com.shordinger.HeavyNuclearIndustry.warpper.InstanceDecoder;
import net.minecraft.nbt.NBTTagCompound;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.component.Component;
import com.shordinger.HeavyNuclearIndustry.NuclearReactor.coolant.CoolantPackage;

public class Reactor implements IProcessable, Runnable {

    private static InstanceDecoder<Reactor> decoder;
    private int maxCoreHeat;
    private int EscapedRadiation;
    private CoolantPackage coolant;
    private CoolantPackage output;
    private final ArrayList<Integer> InsertionStatus = new ArrayList<>();
    private final ArrayList<Integer> IsolationStatus = new ArrayList<>();
    private long EUGenerated;

    private ProcessStatus status = ProcessStatus.finish;

    private boolean fluidMode = false;
    private static final int[] maxSize = new int[]{0, 4, 8, 12};
    public int tier = 0;


    private final ArrayList<Component> components = new ArrayList<>(16 * 16 * 16);

    private Thread thread;
    public Reactor(){

    }

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

    private boolean fluidProcessing() {
        return false;
    }

    public boolean process(int period) {
        if(thread==null){
            thread=new Thread(this);
        }
        if (status != ProcessStatus.finish) {
            return false;
        }
        else{
            status = ProcessStatus.waiting;
            LockSupport.unpark(thread);
        }

        return true;
    }

    @Override
    public boolean ProcessRun() {
        return preProcess() && process() && postProcess();
    }


    //run value initialization and other pre-processing jobs
    @Override
    public boolean preProcess() {
        return false;
    }

    //run calculations(after finish implement this, we should move them to DLL instead of running in FXXKing Java)
    @Override
    public boolean process() {
        for(IProcessable component:components){
            component.ProcessRun();
        }
        return false;
    }

    //garbage collection and other jobs
    @Override
    public boolean postProcess() {
        return false;
    }

    @Override
    public ProcessStatus status() {
        return status;
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

    @Override
    public void run() {
        if (status == ProcessStatus.waiting) {
            status = ProcessStatus.processing;
            if (ProcessRun()) {
                status = ProcessStatus.finish;
            } else {
                status = ProcessStatus.error;
            }
        }
        LockSupport.park();
    }
}
