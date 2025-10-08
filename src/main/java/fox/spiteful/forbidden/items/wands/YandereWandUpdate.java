package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.compat.Compat;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.wands.ItemWandCasting;
import vazkii.botania.api.mana.ManaItemHandler;

public class YandereWandUpdate extends DarkWandRodOnUpdate {

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (!Compat.botan || !Config.crossWand
                || player.ticksExisted % regenTimer() != 0
                || !checkHotbar(itemstack, player)) {
            return;
        }
        try {
            int cost;
            String capTag = ((ItemWandCasting) itemstack.getItem()).getCap(itemstack).getTag();
            cost = capTag.equals("manasteel") || capTag.equals("elementium") ? Config.manavis - 2 : Config.manavis;

            cost = Math.max(0, cost);

            int maxVis = getMaxVis(itemstack);
            for (Aspect primal : primals) {
                int deficit = maxVis - ((ItemWandCasting) itemstack.getItem()).getVis(itemstack, primal);
                if (deficit <= 0) {
                    continue;
                }
                deficit = Math.min(deficit, 100);
                if (ManaItemHandler.requestManaExact(itemstack, player, cost * deficit, true))
                    ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, 1, true);
            }
        } catch (Exception ignored) {}
    }

    @Override
    protected int regenTimer() {
        return 40;
    }
}
