package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;

public class TaintedWandUpdate implements IWandRodOnUpdate {

    Aspect[] primals = Aspect.getPrimalAspects().toArray(new Aspect[0]);

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (player.ticksExisted % 100 == 0
                && player.worldObj.getBiomeGenForCoords((int) player.posX, (int) player.posZ).biomeID
                        == thaumcraft.common.config.Config.biomeTaintID) {
            for (Aspect primal : primals) {
                if (((ItemWandCasting) itemstack.getItem()).getVis(itemstack, primal)
                        < ((ItemWandCasting) itemstack.getItem()).getMaxVis(itemstack) / 10) {
                    ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, 1, true);
                }
            }
        }
    }
}
