package com.shordinger.HeavyNuclearIndustry.minecraft.block.coolantTower;

import com.shordinger.HeavyNuclearIndustry.minecraft.block.BasicBlock;
import com.shordinger.HeavyNuclearIndustry.minecraft.block.machine.GT_TileEntity_NuclearReactor;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.IBlockAccess;


public class BlockCoolantController extends BasicBlock {
    private final int tier;
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier1 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(1, new String[][]{}, "CoolantController-1");
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier2 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(2, new String[][]{}, "CoolantController-2");
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier3 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(3, new String[][]{}, "CoolantController-3");
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier4 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(4, new String[][]{}, "CoolantController-4");
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier5 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(5, new String[][]{}, "CoolantController-5");

    public static final BlockCoolantController controller1 = new BlockCoolantController("Block.CoolantController.1", 1);
    public static final BlockCoolantController controller2 = new BlockCoolantController("Block.CoolantController.2", 2);
    public static final BlockCoolantController controller3 = new BlockCoolantController("Block.CoolantController.3", 3);
    public static final BlockCoolantController controller4 = new BlockCoolantController("Block.CoolantController.4", 4);
    public static final BlockCoolantController controller5 = new BlockCoolantController("Block.CoolantController.5", 5);

    public BlockCoolantController(String unlocalizedName, int tier) {
        super(unlocalizedName);
        this.tier = tier;
    }

    public static GT_TileEntity_NuclearReactor.MultiPiecesStructure getStructure(int tier) {
        return switch (tier) {
            case 1 -> tier1;
            case 2 -> tier2;
            case 3 -> tier3;
            case 4 -> tier4;
            case 5 -> tier5;
            default -> null;
        };
    }

    public int getTier() {
        return tier;
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

    public static class InternalItemBlock extends ItemBlock {
        public InternalItemBlock(Block p_i45328_1_) {
            super(p_i45328_1_);
        }
    }

    public static void register() {
        GameRegistry.registerBlock(controller1, InternalItemBlock.class, controller1.getUnlocalizedName());
        GameRegistry.registerBlock(controller2, InternalItemBlock.class, controller2.getUnlocalizedName());
        GameRegistry.registerBlock(controller3, InternalItemBlock.class, controller3.getUnlocalizedName());
        GameRegistry.registerBlock(controller4, InternalItemBlock.class, controller4.getUnlocalizedName());
        GameRegistry.registerBlock(controller5, InternalItemBlock.class, controller5.getUnlocalizedName());
    }
}
