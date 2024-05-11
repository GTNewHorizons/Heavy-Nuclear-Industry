package com.shordinger.HeavyNuclearIndustry.warpper;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.IProcessable;
import com.sun.jna.Pointer;

public class InstanceDecoder<T extends IProcessable> implements Runnable {

    public void decoder(T instance,Long address){
        Pointer pointer=new Pointer(address);
        byte[] buffer=new byte[0];
        pointer.read(0,buffer,0,instance.getMemoryByte());
        instance.encoder(buffer);
    }

    public void encoder(T instance,Long address){
        Pointer pointer=new Pointer(address);
        byte[] buffer=new byte[0];
        pointer.write(0,buffer,0,instance.getMemoryByte());
        instance.encoder(buffer);
    }

    @Override
    public void run() {

    }
}
