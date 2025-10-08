package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;

public abstract class DarkWandRodOnUpdate implements IWandRodOnUpdate {

    static final Aspect[] primals = Aspect.getPrimalAspects().toArray(new Aspect[0]);

    protected static int getMaxVis(ItemStack itemstack) {
        return ((ItemWandCasting) itemstack.getItem()).getMaxVis(itemstack);
    }

    protected boolean checkHotbar(ItemStack stack, EntityPlayer player) {
        for (int x = 0; x < 9; ++x) {
            ItemStack item = player.inventory.getStackInSlot(x);
            if (item == stack) return true;
        }
        return false;
    }

    /**
     * The number of ticks between when the wand core regenerates vis
     */
    protected int regenTimer() {
        return 100;
    }
}
