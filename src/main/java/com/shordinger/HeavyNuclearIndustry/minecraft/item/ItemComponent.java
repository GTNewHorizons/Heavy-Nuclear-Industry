package com.shordinger.HeavyNuclearIndustry.minecraft.item;

import com.shordinger.HeavyNuclearIndustry.NuclearReactor.component.Component;

import static com.shordinger.HeavyNuclearIndustry.minecraft.util.Util.MetaItems;

public class ItemComponent extends BasicItem {
    Component itemComponent;
    public ItemComponent(String Name, String MetaName) {
        super(Name, MetaName, MetaItems);
    }


}
