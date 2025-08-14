package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.wands.ItemWandCasting;

public class InfernalWandUpdate extends DarkWandRodOnUpdate {

    static final Aspect[] primals = { Aspect.ORDER, Aspect.ENTROPY, Aspect.AIR, Aspect.EARTH, Aspect.WATER };

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (player.ticksExisted % 100 == 0) {
            int maxVis = getMaxVis(itemstack);
            if (player.worldObj.provider.dimensionId == -1) {
                for (Aspect primal : primals) {
                    if (((ItemWandCasting) itemstack.getItem()).getVis(itemstack, primal) < maxVis / 10) {
                        ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, 1, true);
                    }
                }
            }

            if (((ItemWandCasting) itemstack.getItem()).getVis(itemstack, Aspect.FIRE) < maxVis / 5) {
                ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, Aspect.FIRE, 1, true);
            }
        }

        if (player.isBurning()) player.extinguish();

        if (player.isPotionActive(Potion.wither.id)) {
            if (player.worldObj.isRemote) player.removePotionEffectClient(Potion.wither.id);
            else player.removePotionEffect(Potion.wither.id);
        }
    }
}
