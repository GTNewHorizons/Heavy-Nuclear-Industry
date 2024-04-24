package com.shordinger.HeavyNuclearIndustry.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.shordinger.HeavyNuclearIndustry.minecraft.util.Util.MetaBlocks;

public abstract class BasicBlock extends Block {

    public final Set<Integer> usedMetaSet = new HashSet<>(16);
    public final Map<Integer, String[]> tooltipsMap = new HashMap<>(16);
    public final Map<Integer, IIcon> iconMap = new HashMap<>(16);
    public final String unlocalizedName;

    public BasicBlock(String unlocalizedName) {
        super(Material.iron);
        this.unlocalizedName = unlocalizedName;
        this.setCreativeTab(MetaBlocks);
    }

    // region Abstract

    @Override
    public abstract boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z);

    @Override
    public abstract boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z);


    @Override
    public abstract boolean isNormalCube(IBlockAccess world, int x, int y, int z);

    // endregion

    public Set<Integer> getUsedMetaSet() {
        return usedMetaSet;
    }


    public Map<Integer, String[]> getTooltipsMap() {
        return tooltipsMap;
    }


    public Map<Integer, IIcon> getIconMap() {
        return iconMap;
    }

    @Override
    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return getIconMap().get(meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        Map<Integer, IIcon> iconMap;
        Set<Integer> usedMetaSet;
        if ((iconMap = getIconMap()) == null || (usedMetaSet = getUsedMetaSet()) == null) {
            throw new NullPointerException("Null in " + this.getUnlocalizedName());
        }
        String root = "heavynuclearindustry" + ":" + getUnlocalizedName() + "/";
        this.blockIcon = reg.registerIcon(root + "0");
        for (int Meta : usedMetaSet) {
            iconMap.put(Meta, reg.registerIcon(root + Meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"unchecked"})
    public void getSubBlocks(Item aItem, CreativeTabs aCreativeTabs, List list) {
        Set<Integer> usedMetaSet;
        if ((usedMetaSet = getUsedMetaSet()) == null) {
            throw new NullPointerException("Null in " + this.getUnlocalizedName());
        }
        for (int Meta : usedMetaSet) {
            list.add(new ItemStack(aItem, 1, Meta));
        }
    }

    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }

    @Override
    public int getDamageValue(World aWorld, int aX, int aY, int aZ) {
        return aWorld.getBlockMetadata(aX, aY, aZ);
    }
}
