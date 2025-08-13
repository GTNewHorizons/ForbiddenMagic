package fox.spiteful.forbidden.items.wands;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.Forbidden;
import fox.spiteful.forbidden.compat.Compat;
import fox.spiteful.forbidden.items.ForbiddenItems;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemWandCores extends Item {

    public final String[] types = { "tainted", "infernal", "soul", "blood", "witchwood", "profane", "blood_inert",
            "livingwood", "livingwood_inert", "blood_staff", "witchwood_staff", "dreamwood", "dreamwood_inert",
            "dreamwood_staff", "equivalent" };
    public IIcon[] icon;

    public ItemWandCores() {
        this.setMaxStackSize(64);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(Forbidden.tab);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir) {
        icon = new IIcon[types.length];
        for (int x = 0; x < types.length; x++) this.icon[x] = ir.registerIcon("forbidden:wand_rod_" + types[x]);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int meta) {
        return this.icon[meta];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs xCreativeTabs, List list) {
        for (int x = 0; x < types.length; x++) {
            list.add(new ItemStack(this, 1, x));
        }

        ItemStack wand = new ItemStack(ConfigItems.itemWandCasting, 1, 72);
        ((ItemWandCasting) wand.getItem()).setCap(wand, ConfigItems.WAND_CAP_THAUMIUM);
        ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.WAND_ROD_TAINTED);
        list.add(wand);
        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 72);
        ((ItemWandCasting) wand.getItem()).setCap(wand, ConfigItems.WAND_CAP_THAUMIUM);
        ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.WAND_ROD_INFERNAL);
        list.add(wand);
        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 2000);
        ((ItemWandCasting) wand.getItem()).setCap(wand, ForbiddenItems.WAND_CAP_ORICHALCUM);
        ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.WAND_ROD_NEUTRONIUM);
        list.add(wand);
        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 2000);
        ((ItemWandCasting) wand.getItem()).setCap(wand, ForbiddenItems.WAND_CAP_ORICHALCUM);
        ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.STAFF_ROD_NEUTRONIUM);
        list.add(wand);
        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 36);
        ((ItemWandCasting) wand.getItem()).setCap(wand, ConfigItems.WAND_CAP_IRON);
        ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.WAND_ROD_PROFANE);
        ((ItemWandCasting) wand.getItem()).storeAllVis(
                wand,
                new AspectList().add(Aspect.FIRE, 5000).add(Aspect.WATER, 5000).add(Aspect.EARTH, 5000)
                        .add(Aspect.AIR, 5000).add(Aspect.ORDER, 5000).add(Aspect.ENTROPY, 5000));
        list.add(wand);
        if (Compat.bm && Config.crossWand) {
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 84);
            ((ItemWandCasting) wand.getItem()).setCap(wand, ForbiddenItems.WAND_CAP_ALCHEMICAL);
            ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.WAND_ROD_BLOOD);
            list.add(wand);
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 168);
            ((ItemWandCasting) wand.getItem()).setCap(wand, ForbiddenItems.WAND_CAP_ALCHEMICAL);
            ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.STAFF_ROD_BLOOD);
            list.add(wand);
        }
        if (Compat.am2 && Config.crossWand) {
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 72);
            ((ItemWandCasting) wand.getItem()).setCap(wand, ForbiddenItems.WAND_CAP_VINTEUM);
            ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.WAND_ROD_WITCHWOOD);
            list.add(wand);
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 144);
            ((ItemWandCasting) wand.getItem()).setCap(wand, ForbiddenItems.WAND_CAP_VINTEUM);
            ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.STAFF_ROD_WITCHWOOD);
            list.add(wand);
        }
        if (Compat.botan && Config.crossWand) {
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 84);
            ((ItemWandCasting) wand.getItem()).setCap(wand, ForbiddenItems.WAND_CAP_MANASTEEL);
            ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.WAND_ROD_LIVINGWOOD);
            list.add(wand);
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 84);
            ((ItemWandCasting) wand.getItem()).setCap(wand, ForbiddenItems.WAND_CAP_ELEMENTIUM);
            ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.WAND_ROD_DREAMWOOD);
            list.add(wand);
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 144);
            ((ItemWandCasting) wand.getItem()).setCap(wand, ForbiddenItems.WAND_CAP_ELEMENTIUM);
            ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.STAFF_ROD_DREAMWOOD);
            list.add(wand);
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 1);
            ((ItemWandCasting) wand.getItem()).setCap(wand, ForbiddenItems.WAND_CAP_TERRASTEEL);
            ((ItemWandCasting) wand.getItem()).setRod(wand, ConfigItems.WAND_ROD_WOOD);
            list.add(wand);
        }
        if (Compat.ee3 && Config.crossWand) {
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 84);
            ((ItemWandCasting) wand.getItem()).setCap(wand, ConfigItems.WAND_CAP_VOID);
            ((ItemWandCasting) wand.getItem()).setRod(wand, ForbiddenItems.WAND_ROD_EQUIVALENT);
        }
        list.add(wand);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + types[stack.getItemDamage()];
    }
}
