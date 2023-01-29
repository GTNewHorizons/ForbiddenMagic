package fox.spiteful.forbidden.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionBloodSeal extends Potion {

    public PotionBloodSeal(int id) {
        super(id, true, 0xAC1919);
        this.setPotionName("potion.blood_seal");
        this.setIconIndex(4, 0);
    }

    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(DarkPotions.icons);
        return super.getStatusIconIndex();
    }
}
