package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ManaStaffUpdate extends ManaWandUpdate {

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (!(checkHotbar(itemstack, player))) return;
        super.onUpdate(itemstack, player);
    }

    @Override
    protected int regenTimer() {
        return 40;
    }

    @Override
    protected float getCost(boolean hasVinteumCaps) {
        return hasVinteumCaps ? 0.5F : 1.0F;
    }
}
