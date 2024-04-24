package com.shordinger.HeavyNuclearIndustry.NuclearReactor.component;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.coolant.CoolantPackage;
import com.shordinger.HeavyNuclearIndustry.NuclearReactor.fuel.FuelPackage;

public class CoolingIOComponent extends Component {

    CoolantPackage coolantPackage;
    CoolantPackage output;

    public CoolingIOComponent(float energyMultiplier, float neutronMultiplier, float euMultiplier, FuelPackage fuel,
                              boolean isPreview) {
        super(energyMultiplier, neutronMultiplier, euMultiplier, fuel, isPreview);
    }
}
