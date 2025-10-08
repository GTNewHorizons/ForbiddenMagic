package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import am2.api.ArsMagicaApi;
import am2.api.IExtendedProperties;
import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.compat.Compat;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ManaWandUpdate extends DarkWandRodOnUpdate {

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (!Compat.am2 || !Config.crossWand || player.ticksExisted % regenTimer() != 0) {
            return;
        }

        try {
            IExtendedProperties prop = ArsMagicaApi.instance.getExtendedProperties(player);
            if (prop == null || prop.getCurrentMana() <= 0) return;

            float cost = getCost(((ItemWandCasting) itemstack.getItem()).getCap(itemstack).getTag().equals("vinteum"));
            int maxVis = getMaxVis(itemstack);
            for (Aspect primal : primals) {
                int deficit = maxVis - ((ItemWandCasting) itemstack.getItem()).getVis(itemstack, primal);
                if (deficit <= 0) {
                    continue;
                }
                deficit = Math.min(deficit, 100);
                if (prop.getCurrentMana() > cost * deficit) {
                    prop.setCurrentMana(prop.getCurrentMana() - cost * deficit);
                    ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, 1, true);
                }
            }
        } catch (Exception ignored) {}
    }

    protected float getCost(boolean vinteumCaps) {
        return vinteumCaps ? 0.8F : 1.4F;
    }
}
