package com.shordinger.HeavyNuclearIndustry.NuclearReactor.component;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.fuel.FuelPackage;

public class NeutronReflector extends Component {

    public NeutronReflector(float energyMultiplier, float neutronMultiplier, float euMultiplier, FuelPackage fuel,long maxHeat,
        boolean isPreview) {
        super(energyMultiplier, neutronMultiplier, euMultiplier, fuel, maxHeat, isPreview);
    }
}
