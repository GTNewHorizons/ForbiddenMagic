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
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
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
    public void getSubItems(Item item, CreativeTabs xCreativeTabs, List<ItemStack> list) {
        for (int x = 0; x < types.length; x++) {
            list.add(new ItemStack(this, 1, x));
        }

        list.add(buildWand(ConfigItems.WAND_CAP_THAUMIUM, ForbiddenItems.WAND_ROD_TAINTED));
        list.add(buildWand(ConfigItems.WAND_CAP_THAUMIUM, ForbiddenItems.WAND_ROD_INFERNAL));
        list.add(buildWand(ForbiddenItems.WAND_CAP_ORICHALCUM, ForbiddenItems.WAND_ROD_NEUTRONIUM));
        list.add(buildWand(ForbiddenItems.WAND_CAP_ORICHALCUM, ForbiddenItems.STAFF_ROD_NEUTRONIUM));
        ItemStack wand = buildWand(ConfigItems.WAND_CAP_IRON, ForbiddenItems.WAND_ROD_PROFANE);
        ((ItemWandCasting) wand.getItem()).storeAllVis(
                wand,
                new AspectList().add(Aspect.FIRE, 5000).add(Aspect.WATER, 5000).add(Aspect.EARTH, 5000)
                        .add(Aspect.AIR, 5000).add(Aspect.ORDER, 5000).add(Aspect.ENTROPY, 5000));
        list.add(wand);
        if (Compat.bm && Config.crossWand) {
            list.add(buildWand(ForbiddenItems.WAND_CAP_ALCHEMICAL, ForbiddenItems.WAND_ROD_BLOOD));
            list.add(buildWand(ForbiddenItems.WAND_CAP_ALCHEMICAL, ForbiddenItems.STAFF_ROD_BLOOD));
        }
        if (Compat.am2 && Config.crossWand) {
            list.add(buildWand(ForbiddenItems.WAND_CAP_VINTEUM, ForbiddenItems.WAND_ROD_WITCHWOOD));
            list.add(buildWand(ForbiddenItems.WAND_CAP_VINTEUM, ForbiddenItems.STAFF_ROD_WITCHWOOD));
        }
        if (Compat.botan && Config.crossWand) {
            list.add(buildWand(ForbiddenItems.WAND_CAP_MANASTEEL, ForbiddenItems.WAND_ROD_LIVINGWOOD));
            list.add(buildWand(ForbiddenItems.WAND_CAP_ELEMENTIUM, ForbiddenItems.WAND_ROD_DREAMWOOD));
            list.add(buildWand(ForbiddenItems.WAND_CAP_ELEMENTIUM, ForbiddenItems.STAFF_ROD_DREAMWOOD));
            list.add(buildWand(ForbiddenItems.WAND_CAP_TERRASTEEL, ConfigItems.WAND_ROD_WOOD));
        }
        if (Compat.ee3 && Config.crossWand) {
            list.add(buildWand(ConfigItems.WAND_CAP_VOID, ForbiddenItems.WAND_ROD_EQUIVALENT));
        }
    }

    private static ItemStack buildWand(WandCap cap, WandRod rod) {
        ItemStack wand = new ItemStack(
                ConfigItems.itemWandCasting,
                1,
                Math.min(rod.getCraftCost() * cap.getCraftCost(), Short.MAX_VALUE - 1));
        ((ItemWandCasting) wand.getItem()).setCap(wand, cap);
        ((ItemWandCasting) wand.getItem()).setRod(wand, rod);
        return wand;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + types[stack.getItemDamage()];
    }
}
