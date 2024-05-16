package com.shordinger.HeavyNuclearIndustry.NuclearReactor.component;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.fuel.FuelPackage;

public class EmptyComponent extends Component{
    public EmptyComponent(float energyMultiplier, float neutronMultiplier, float euMultiplier, FuelPackage fuel, boolean isPreview) {
        super(energyMultiplier, neutronMultiplier, euMultiplier, fuel, isPreview);
    }

    protected EmptyComponent(float energyMultiplier, float neutronMultiplier, float euMultiplier, FuelPackage fuel) {
        super(energyMultiplier, neutronMultiplier, euMultiplier, fuel);
    }
}
