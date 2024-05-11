package com.shordinger.HeavyNuclearIndustry.warpper;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface JLib extends Library {
    public JLib processor= Native.load("processor", JLib.class);

    public boolean run();
    public boolean preProcess();
    public boolean process();
    public boolean postProcess();
    public boolean status();
    public Long INSTANCE();

}
