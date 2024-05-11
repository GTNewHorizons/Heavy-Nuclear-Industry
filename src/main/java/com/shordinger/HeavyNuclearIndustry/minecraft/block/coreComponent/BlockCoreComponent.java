package com.shordinger.HeavyNuclearIndustry.minecraft.block.coreComponent;

import com.shordinger.HeavyNuclearIndustry.minecraft.block.BasicBlock;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.IBlockAccess;

public class BlockCoreComponent extends BasicBlock {
    public BlockCoreComponent(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
        return false;
    }
}
