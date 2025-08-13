package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import am2.api.ArsMagicaApi;
import am2.api.IExtendedProperties;
import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.compat.Compat;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ManaStaffUpdate implements IWandRodOnUpdate {

    Aspect[] primals = Aspect.getPrimalAspects().toArray(new Aspect[0]);

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (Compat.am2 && Config.crossWand) {
            if (player.ticksExisted % 40 == 0) {

                try {
                    IExtendedProperties prop = ArsMagicaApi.instance.getExtendedProperties(player);

                    float cost;
                    if (((ItemWandCasting) itemstack.getItem()).getCap(itemstack).getTag().equals("vinteum"))
                        cost = 0.5F;
                    else cost = 1.0F;

                    if (prop == null || prop.getCurrentMana() <= 0) return;

                    for (Aspect primal : primals) {
                        int deficit = ((ItemWandCasting) itemstack.getItem()).getMaxVis(itemstack)
                                - ((ItemWandCasting) itemstack.getItem()).getVis(itemstack, primal);
                        if (deficit > 0) {
                            deficit = Math.min(deficit, 100);
                            if (prop.getCurrentMana() > cost * deficit) {
                                prop.setCurrentMana(prop.getCurrentMana() - cost * deficit);
                                ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, 1, true);
                            }
                        }
                    }
                } catch (Throwable ignored) {}
            }
        }
    }
}
