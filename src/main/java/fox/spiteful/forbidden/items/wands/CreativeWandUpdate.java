package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.wands.ItemWandCasting;

public class CreativeWandUpdate extends DarkWandRodOnUpdate {

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        int maxVis = getMaxVis(itemstack);
        for (Aspect primal : primals) {
            if (((ItemWandCasting) itemstack.getItem()).getVis(itemstack, primal) < maxVis) {
                ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, maxVis, true);
            }
        }
    }
}
