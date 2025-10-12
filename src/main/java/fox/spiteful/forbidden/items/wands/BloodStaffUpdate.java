package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class BloodStaffUpdate extends BloodWandUpdate {

    @Override
    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (!(checkHotbar(itemstack, player))) return;
        super.onUpdate(itemstack, player);
    }

    @Override
    protected int regenTimer() {
        return 25;
    }

    @Override
    public boolean syphonHealth(EntityPlayer player) {
        if (player.getHealth() > 3) {
            player.setHealth(player.getHealth() - 3);
            return true;
        } else if (player.getHealth() > 0) {
            player.func_110142_aN().func_94547_a(new DamageSource("blooderp"), 3, 3);
            player.setHealth(0);
            player.onDeath(new DamageSource("blooderp"));
            return true;
        } else {
            return false;
        }
    }
}
