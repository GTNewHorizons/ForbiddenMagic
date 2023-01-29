package fox.spiteful.forbidden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

import fox.spiteful.forbidden.Forbidden;

public class BlockResource extends Block {

    public BlockResource() {

        super(Material.iron);
        this.setCreativeTab(Forbidden.tab);
        setHardness(5.0F);
        setStepSound(Block.soundTypeMetal);
        this.setBlockTextureName("forbidden:starblock");
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {

        return true;
    }
}
