package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.compat.Compat;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;

public class BloodStaffUpdate implements IWandRodOnUpdate {

    Aspect[] primals = Aspect.getPrimalAspects().toArray(new Aspect[0]);

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (Compat.bm && Config.crossWand && player.ticksExisted % 25 == 0) {
            try {
                if (!checkHotbar(itemstack, player)) return;

                SoulNetworkHandler.checkAndSetItemOwner(itemstack, player);

                int cost;
                String capTag = ((ItemWandCasting) itemstack.getItem()).getCap(itemstack).getTag();
                if (capTag.equals("alchemical") || capTag.equals("blood_iron"))
                    cost = Config.bloodvis - 1;
                else cost = Config.bloodvis;

                cost = Math.max(0, cost);

                for (Aspect primal : primals) {
                    int deficit = ((ItemWandCasting) itemstack.getItem()).getMaxVis(itemstack)
                            - ((ItemWandCasting) itemstack.getItem()).getVis(itemstack, primal);
                    if (deficit > 0) {
                        deficit = Math.min(deficit, 100);
                        if (player.capabilities.isCreativeMode)
                            ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, 1, true);
                        else if (SoulNetworkHandler.syphonFromNetwork(itemstack, cost * deficit) > 0)
                            ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, 1, true);
                        else if (syphonHealth(player)) {
                            ((ItemWandCasting) itemstack.getItem()).addVis(itemstack, primal, 1, true);
                            return;
                        } else return;
                    }
                }

            } catch (Exception ignored) {}
        }
    }

    public boolean syphonHealth(EntityPlayer player) {
        if (player.getHealth() > 3) {
            player.setHealth(player.getHealth() - 3);
            return true;
        } else if (player.getHealth() > 0) {
            player.func_110142_aN().func_94547_a(new DamageSource("blooderp"), 3, 3);
            player.setHealth(0);
            player.onDeath(new DamageSource("blooderp"));
            return true;
        } else return false;
    }

    private boolean checkHotbar(ItemStack stack, EntityPlayer player) {
        for (int x = 0; x < 9; ++x) {
            ItemStack item = player.inventory.getStackInSlot(x);
            if (item == stack) return true;
        }
        return false;
    }
}
