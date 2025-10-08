package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.wands.ItemWandCasting;

public class TaintedWandUpdate extends DarkWandRodOnUpdate {

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (player.ticksExisted % regenTimer() != 0 || player.worldObj.getBiomeGenForCoords(
                MathHelper.floor_double(player.posX),
                MathHelper.floor_double(player.posZ)).biomeID != thaumcraft.common.config.Config.biomeTaintID) {
            return;
        }
        int maxVis = getMaxVis(itemstack);
        for (Aspect primal : primals) {
            if (((ItemWandCasting) itemstack.getItem()).getVis(itemstack, primal) < maxVis / 10) {
                ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, 1, true);
            }
        }
    }
}
