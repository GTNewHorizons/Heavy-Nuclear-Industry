package com.shordinger.HeavyNuclearIndustry.minecraft.block.machine;

import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.shordinger.HeavyNuclearIndustry.NuclearReactor.coolantTower.CoolantTower;
import com.shordinger.HeavyNuclearIndustry.NuclearReactor.reactor.Reactor;
import com.shordinger.HeavyNuclearIndustry.NuclearReactor.turbine.Turbine;
import com.shordinger.HeavyNuclearIndustry.minecraft.block.coolantTower.BlockCoolantController;
import com.shordinger.HeavyNuclearIndustry.minecraft.block.turbine.BlockTurbineController;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GT_TileEntity_NuclearReactor extends GT_MetaTileEntity_MultiblockBase_EM {

    private final HashMap<Offset, MultiPiecesStructure> coolantTier = new HashMap<>();
    private final HashMap<Offset, MultiPiecesStructure> turbineTier = new HashMap<>();
    private final ArrayList<CoolantTower> coolantTowers = new ArrayList<>();
    private final ArrayList<Turbine> turbines = new ArrayList<>();
    private final Reactor reactor = new Reactor();
    private final ArrayList<Offset> structures = new ArrayList<>();
    private MultiPiecesStructure core;
    private final int tier = 0;
    private static final MultiPiecesStructure tier1 = new MultiPiecesStructure(1, new String[][]{}, "NuclearReactorCore-1");
    private static final MultiPiecesStructure tier2 = new MultiPiecesStructure(2, new String[][]{}, "NuclearReactorCore-2");
    private static final MultiPiecesStructure tier3 = new MultiPiecesStructure(3, new String[][]{}, "NuclearReactorCore-3");
    private static final MultiPiecesStructure tier4 = new MultiPiecesStructure(4, new String[][]{}, "NuclearReactorCore-4");

    private boolean structureChanged = false;

    public static class MultiPiecesStructure {
        public final int tier;
        public final String[][] shape;
        public final String name;
        Offset offset;

        public MultiPiecesStructure(int tier, String[][] shape, String name) {
            this.tier = tier;
            this.shape = shape;
            this.name = name;
        }

        public MultiPiecesStructure setOffset(Offset offset) {
            this.offset = offset;
            return this;
        }
    }

    @Override
    public void onFirstTick_EM(IGregTechTileEntity aBaseMetaTileEntity) {
        super.onFirstTick_EM(aBaseMetaTileEntity);
        reactor.init();
    }

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        super.onPostTick(aBaseMetaTileEntity, aTick);
        reactor.process((int) aTick);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        reactor.setByNBT(aNBT);
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        reactor.saveToNBT(aNBT);
    }

    public static class Offset {
        public int offsetX;
        public int offsetY;
        public int offsetZ;

        public Offset(int x, int y, int z) {
            offsetX = x;
            offsetY = y;
            offsetZ = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Offset offset = (Offset) o;
            return offsetX == offset.offsetX && offsetY == offset.offsetY && offsetZ == offset.offsetZ;
        }

        @Override
        public int hashCode() {
            return Objects.hash(offsetX, offsetY, offsetZ);
        }
    }

    public boolean checkStructureValid(Block block, Offset offset) {
        if (block instanceof BlockTurbineController turbineController) {
            return true;
        } else if (block instanceof BlockCoolantController coolantController) {
            return true;
        }
        return false;
    }

    public boolean addStructure(Block block, int x, int y, int z) {
        Offset offset = new Offset(x, y, z);
        if (!structures.contains(offset) && checkStructureValid(block, offset)) {
            structures.add(offset);
            if (block instanceof BlockTurbineController turbineController) {
                turbineTier.put(offset, BlockTurbineController.getStructure(turbineController.getTier()));
            } else if (block instanceof BlockCoolantController coolantController) {
                coolantTier.put(offset, BlockCoolantController.getStructure(coolantController.getTier()));
            }
            structureChanged = true;
            return true;
        }
        return false;
    }

    public boolean rebuildNuclearInstance() {
        return true;
    }


    @Override
    public void onPreTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        if (aBaseMetaTileEntity.isServerSide()) {
            if (structureChanged) {
                if (rebuildNuclearInstance()) {
                    //TODO
                }
            }
            reactor.process((int) aTick);
            for (var coolant : coolantTowers) {
                coolant.process((int) aTick);
            }
            for (var turbine : turbines) {
                turbine.process((int) aTick);
            }
        }
        super.onPreTick(aBaseMetaTileEntity, aTick);
    }

    @Override
    protected boolean checkMachine_EM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        for (var structure : coolantTier.entrySet()) {
            if (checkPiece(structure.getValue())) {
                structureChanged = true;
                coolantTier.remove(structure.getKey());
                structures.remove(structure.getKey());
            }
        }
        for (var structure : turbineTier.entrySet()) {
            if (checkPiece(structure.getValue())) {
                structureChanged = true;
                turbineTier.remove(structure.getKey());
                structures.remove(structure.getKey());
            }
        }
        return checkPiece(core);
    }


    private boolean checkPiece(MultiPiecesStructure structure) {
        return checkPiece(structure.name, structure.offset.offsetX, structure.offset.offsetY, structure.offset.offsetZ);
    }

    private static IStructureDefinition<GT_TileEntity_NuclearReactor> STRUCTURE_DEFINITION = null;


    protected GT_TileEntity_NuclearReactor(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    protected GT_TileEntity_NuclearReactor(String aName) {
        super(aName);
    }

    @Override
    public IStructureDefinition<GT_TileEntity_NuclearReactor> getStructure_EM() {
        if (STRUCTURE_DEFINITION == null) {
            STRUCTURE_DEFINITION = StructureDefinition.<GT_TileEntity_NuclearReactor>builder()
                .addShape(core.name, core.shape)
                //TODO
                .build();
        }
        return STRUCTURE_DEFINITION;
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {

    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_TileEntity_NuclearReactor(mName);
    }


}
