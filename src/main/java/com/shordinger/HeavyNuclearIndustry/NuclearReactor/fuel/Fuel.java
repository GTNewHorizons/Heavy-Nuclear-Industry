package com.shordinger.HeavyNuclearIndustry.NuclearReactor.fuel;

import gregtech.api.enums.Materials;

import static ic2.core.Ic2Items.Uran238;

public class Fuel {
    Object material;
    Object resultMaterial;

    //should be a number between 0-100
    int neutronsEnergy;
    //should be a number between 0-100
    int heatProcess;
    //should be a number between 0-100
    int depPeriod;

    int neutronProcess;
    public Fuel(Object material, Object resultMaterial, int neutronsEnergy, int heatProcess,int neutronProcess, int depPeriod) {
        this.material = material;
        this.resultMaterial = resultMaterial;
        this.neutronProcess = neutronProcess;
        this.neutronsEnergy = neutronsEnergy;
        this.heatProcess = heatProcess;
        this.depPeriod = depPeriod;
    }
    public static Fuel U235=new Fuel(Materials.Uranium235,Materials.Plutonium,20,5,1,100);
    public static Fuel U238=new Fuel(Uran238,Materials.Plutonium,20,1,1,100);



}
