package com.shordinger.HeavyNuclearIndustry.NuclearReactor.component;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.fuel.FuelPackage;

public class EmptyComponent extends Component{
    public EmptyComponent(float energyMultiplier, float neutronMultiplier, float euMultiplier, FuelPackage fuel,long maxHeat ,boolean isPreview) {
        super(energyMultiplier, neutronMultiplier, euMultiplier, fuel, maxHeat, isPreview);
    }

    protected EmptyComponent(long maxHeat,float energyMultiplier, float neutronMultiplier, float euMultiplier, FuelPackage fuel) {
        super(maxHeat, energyMultiplier, neutronMultiplier, euMultiplier, fuel);
    }


    public static EmptyComponent EMPTY=new EmptyComponent(0,0,0,0,null);
}
