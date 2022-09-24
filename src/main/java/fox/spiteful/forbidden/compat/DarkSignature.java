package fox.spiteful.forbidden.compat;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.api.subtile.signature.BasicSignature;

public class DarkSignature extends BasicSignature {
    IIcon icon;

    public DarkSignature(String name) {
        super(name);
    }

    @Override
    public void registerIcons(IIconRegister reg) {
        icon = reg.registerIcon("forbidden:" + getName());
    }

    @Override
    public IIcon getIconForStack(ItemStack item) {
        return icon;
    }

    @Override
    public String getUnlocalizedNameForStack(ItemStack item) {
        return "darkflower." + getName();
    }

    @Override
    public String getUnlocalizedLoreTextForStack(ItemStack item) {
        return "tile.darkflower." + getName() + ".lore";
    }
}
