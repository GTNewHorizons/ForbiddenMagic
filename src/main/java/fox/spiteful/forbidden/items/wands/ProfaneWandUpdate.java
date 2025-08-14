package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import fox.spiteful.forbidden.items.ForbiddenItems;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ProfaneWandUpdate extends DarkWandRodOnUpdate {

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (player.ticksExisted % 20 != 0) {
            return;
        }
        NBTTagCompound tag = itemstack.getTagCompound();
        int contract = tag.getInteger("contract");
        if (!tag.hasKey("contract")) {
            Thaumcraft.proxy.getResearchManager().completeResearch(player, "ROD_profane");
            contract = 25000;
        } else if (contract <= 0) {
            return;
        }

        int maxVis = getMaxVis(itemstack);
        for (Aspect primal : primals) {
            int deficit = maxVis - ((ItemWandCasting) itemstack.getItem()).getVis(itemstack, primal);
            if (deficit <= 0) {
                continue;
            }
            int restore = Math.min(contract, deficit);
            ((ItemWandCasting) itemstack.getItem()).addRealVis(itemstack, primal, restore, true);
            contract -= restore;
            if (player.worldObj.rand.nextInt(2501) < restore) Thaumcraft.addStickyWarpToPlayer(player, 1);
            if (contract <= 0) break;
        }

        if (contract <= 0) {
            ((ItemWandCasting) itemstack.getItem()).setRod(itemstack, ForbiddenItems.WAND_ROD_PROFANED);
            Thaumcraft.addStickyWarpToPlayer(player, 1);
            player.worldObj.spawnParticle(
                    "largeexplode",
                    player.posX,
                    player.posY + (double) (player.height / 2.0F),
                    player.posZ,
                    0.0D,
                    0.0D,
                    0.0D);
        }

        tag.setInteger("contract", contract);
        itemstack.setTagCompound(tag);
    }
}
