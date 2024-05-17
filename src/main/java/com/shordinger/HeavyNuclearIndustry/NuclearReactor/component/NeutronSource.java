package com.shordinger.HeavyNuclearIndustry.NuclearReactor.component;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.fuel.FuelPackage;

public class NeutronSource extends Component {

    public NeutronSource(float energyMultiplier, float neutronMultiplier, float euMultiplier, FuelPackage fuel,long maxHeat,
        boolean isPreview) {
        super(energyMultiplier, neutronMultiplier, euMultiplier, fuel, maxHeat, isPreview);
    }

    public boolean consume(Component receive, int count) {
        Component a = preview;
        Component b = receive.preview;
        if (b.neutronsEnergyReceived == 0 || b.neutronsEnergyReceived == fuel.getNeuEnergy()) {
            b.neutronsEnergyReceived = a.neutronsEnergyReceived;
            b.neutronsReceived += (fuel.getNeuProcess() + count / 2) / count;
        }
        return true;
    }
}
