package com.shordinger.HeavyNuclearIndustry.NuclearReactor.component;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.fuel.FuelPackage;

public class Rod extends Component {

    public Rod(float energyMultiplier, float neutronMultiplier, float euMultiplier, FuelPackage fuel, long maxHeat,
               boolean isPreview) {
        super(energyMultiplier, neutronMultiplier, euMultiplier, fuel, maxHeat, isPreview);
    }

    public boolean consume(Component receive, int count) {
        Component a = preview;
        Component b = receive.preview;
        b.heat += (heat + count / 2) / count;
        if (b.neutronsEnergyReceived == 0 || b.neutronsEnergyReceived == fuel.getNeuEnergy()) {
            b.neutronsEnergyReceived = a.neutronsEnergyReceived;
            b.neutronsReceived += (fuel.getNeuProcess() + count / 2) / count;
        }
        return true;
    }
}
