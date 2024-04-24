package com.shordinger.HeavyNuclearIndustry.minecraft.block.turbine;

import com.shordinger.HeavyNuclearIndustry.minecraft.block.BasicBlock;
import com.shordinger.HeavyNuclearIndustry.minecraft.block.coolantTower.BlockCoolantController;
import com.shordinger.HeavyNuclearIndustry.minecraft.block.machine.GT_TileEntity_NuclearReactor;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.IBlockAccess;

public class BlockTurbineController extends BasicBlock {
    private final int tier;
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier1 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(1, new String[][]{}, "TurbineController-1");
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier2 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(2, new String[][]{}, "TurbineController-2");
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier3 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(3, new String[][]{}, "TurbineController-3");
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier4 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(4, new String[][]{}, "TurbineController-4");
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier5 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(5, new String[][]{}, "TurbineController-5");
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier6 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(6, new String[][]{}, "TurbineController-6");
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier7 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(7, new String[][]{}, "TurbineController-7");
    static final GT_TileEntity_NuclearReactor.MultiPiecesStructure tier8 = new GT_TileEntity_NuclearReactor.MultiPiecesStructure(8, new String[][]{}, "TurbineController-8");
    public static final BlockTurbineController controller1 = new BlockTurbineController("Block.TurbineController.1", 1);
    public static final BlockTurbineController controller2 = new BlockTurbineController("Block.TurbineController.2", 2);
    public static final BlockTurbineController controller3 = new BlockTurbineController("Block.TurbineController.3", 3);
    public static final BlockTurbineController controller4 = new BlockTurbineController("Block.TurbineController.4", 4);
    public static final BlockTurbineController controller5 = new BlockTurbineController("Block.TurbineController.5", 5);
    public static final BlockTurbineController controller6 = new BlockTurbineController("Block.TurbineController.6", 6);
    public static final BlockTurbineController controller7 = new BlockTurbineController("Block.TurbineController.7", 7);
    public static final BlockTurbineController controller8 = new BlockTurbineController("Block.TurbineController.8", 8);

    public BlockTurbineController(String unlocalizedName, int tier) {
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
            case 6 -> tier6;
            case 7 -> tier7;
            case 8 -> tier8;
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
        GameRegistry.registerBlock(controller6, InternalItemBlock.class, controller6.getUnlocalizedName());
        GameRegistry.registerBlock(controller7, InternalItemBlock.class, controller7.getUnlocalizedName());
        GameRegistry.registerBlock(controller8, InternalItemBlock.class, controller8.getUnlocalizedName());
    }
}
