package com.shordinger.HeavyNuclearIndustry.minecraft.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import static net.minecraft.util.StatCollector.translateToLocalFormatted;

public class Util {
    public static final CreativeTabs MetaItems = new CreativeTabs(
        translateToLocalFormatted("CreativeTabs.HeavyNuclearReactor.Items")) {


        @Override
        public Item getTabIconItem() {
            return null;
        }
    };

    public static final CreativeTabs MetaBlocks = new CreativeTabs(
        translateToLocalFormatted("CreativeTabs.HeavyNuclearReactor.Blocks")) {


        @Override
        public Item getTabIconItem() {
            return null;
        }
    };

}
