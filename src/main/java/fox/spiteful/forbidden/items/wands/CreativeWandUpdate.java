package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;

public class CreativeWandUpdate implements IWandRodOnUpdate {

    Aspect[] primals = Aspect.getPrimalAspects().toArray(new Aspect[0]);

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        for (Aspect primal : primals) {
            if (((ItemWandCasting) itemstack.getItem()).getVis(itemstack, primal)
                    < ((ItemWandCasting) itemstack.getItem()).getMaxVis(itemstack)) {
                ((ItemWandCasting) itemstack.getItem()).addVis(
                        itemstack,
                        primal,
                        ((ItemWandCasting) itemstack.getItem()).getMaxVis(itemstack)
                                - ((ItemWandCasting) itemstack.getItem()).getVis(itemstack, primal),
                        true);
            }
        }
    }
}
