package com.shordinger.HeavyNuclearIndustry.minecraft.register;

import com.shordinger.HeavyNuclearIndustry.minecraft.block.coolantTower.BlockCoolantController;
import com.shordinger.HeavyNuclearIndustry.minecraft.block.turbine.BlockTurbineController;

public class BlockRegister {
    public static void run() {
        BlockCoolantController.register();
        BlockTurbineController.register();

    }
}
