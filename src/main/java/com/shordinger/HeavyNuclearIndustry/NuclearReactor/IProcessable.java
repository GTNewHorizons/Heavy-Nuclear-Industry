package com.shordinger.HeavyNuclearIndustry.NuclearReactor;

public interface IProcessable {



    public boolean run();

    public boolean preProcess();

    public boolean process();

    public boolean postProcess();

    public boolean status();

    public void encoder(byte[] buffer);
    public void decoder(byte[] buffer);
    public int getMemoryByte();

}
