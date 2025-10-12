package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.compat.Compat;
import fox.spiteful.forbidden.items.ForbiddenItems;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.WandCap;
import thaumcraft.common.items.wands.ItemWandCasting;

public class BloodWandUpdate extends DarkWandRodOnUpdate {

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (!Compat.bm || !Config.crossWand || player.ticksExisted % regenTimer() != 0) {
            return;
        }
        try {
            SoulNetworkHandler.checkAndSetItemOwner(itemstack, player);

            WandCap cap = ((ItemWandCasting) itemstack.getItem()).getCap(itemstack);
            // "blood_iron" is the Blood Iron Infused Cap from Blood Arsenal
            int cost = cap == ForbiddenItems.WAND_CAP_ALCHEMICAL || cap.getTag().equals("blood_iron")
                    ? Config.bloodvis - 1
                    : Config.bloodvis;

            cost = Math.max(0, cost);

            int maxVis = getMaxVis(itemstack);
            for (Aspect primal : primals) {
                int deficit = maxVis - ((ItemWandCasting) itemstack.getItem()).getVis(itemstack, primal);
                if (deficit <= 0) {
                    continue;
                }
                deficit = Math.min(deficit, 100);
                if (player.capabilities.isCreativeMode)
                    ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, 1, true);
                else if (SoulNetworkHandler.syphonFromNetwork(itemstack, cost * deficit) > 0)
                    ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, 1, true);
                else if (syphonHealth(player)) {
                    ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, 1, true);
                    return;
                } else {
                    return;
                }
            }

        } catch (Exception ignored) {}
    }

    public boolean syphonHealth(EntityPlayer player) {
        if (player.getHealth() > 6) {
            player.setHealth(player.getHealth() - 3);
            return true;
        } else {
            return false;
        }
    }
}
