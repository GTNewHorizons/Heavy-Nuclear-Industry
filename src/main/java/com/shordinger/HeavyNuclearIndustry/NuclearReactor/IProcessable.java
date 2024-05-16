package com.shordinger.HeavyNuclearIndustry.NuclearReactor;

public interface IProcessable {



    public boolean ProcessRun();

    public boolean preProcess();

    public boolean process();

    public boolean postProcess();

    public ProcessStatus status();

    public void encoder(byte[] buffer);
    public void decoder(byte[] buffer);
    public int getMemoryByte();

    public enum ProcessStatus{
        //Process jobs have submitted, waiting for process;
        waiting,
        //Processing is running
        processing,
        //Process finished
        finish,
        //Error issued by processing, waiting for reset;
        error
    }

}
