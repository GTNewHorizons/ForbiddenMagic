package fox.spiteful.forbidden.items.baubles;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.expanded.BaubleItemHelper;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.Forbidden;
import fox.spiteful.forbidden.compat.Compat;
import fox.spiteful.forbidden.items.ForbiddenItems;
import thaumcraft.api.IRunicArmor;
import vazkii.botania.api.item.ICosmeticAttachable;

@Optional.Interface(iface = "vazkii.botania.api.item.ICosmeticAttachable", modid = "Botania")
public class ItemConsumptionCincture extends Item implements IRunicArmor, IBauble, ICosmeticAttachable {

    IIcon icon;

    public ItemConsumptionCincture() {
        super();
        maxStackSize = 1;
        setCreativeTab(Forbidden.tab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        icon = ir.registerIcon("forbidden:consumption_cincture");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return icon;
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.BELT;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}

    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}

    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}

    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }

    @Override
    public ItemStack getCosmeticItem(ItemStack stack) {
        if (stack == null || stack.getTagCompound() == null) return null;
        if (!stack.getTagCompound().hasKey("cosmeticItem")) return null;
        NBTTagCompound cosmetic = stack.getTagCompound().getCompoundTag("cosmeticItem");
        return ItemStack.loadItemStackFromNBT(cosmetic);
    }

    @Override
    public void setCosmeticItem(ItemStack stack, ItemStack cosmetic) {
        if (stack == null) return;
        NBTTagCompound cmp = new NBTTagCompound();
        if (cosmetic != null) cosmetic.writeToNBT(cmp);
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }

        tag.setTag("cosmeticItem", cmp);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean adv) {
        if (Compat.baublesExpanded) {
            BaubleItemHelper.addSlotInformation(list, new String[] { "belt" });
        }
        if (Compat.botan && GuiScreen.isShiftKeyDown() && getCosmeticItem(stack) != null) list.add(
                String.format(
                        StatCollector.translateToLocal("botaniamisc.hasCosmetic"),
                        getCosmeticItem(stack).getDisplayName()).replace("&", "§"));
    }

    public static boolean wearingBelt(EntityPlayer player) {
        if (!Config.consumptionCincture) return false;
        IInventory baubles = BaublesApi.getBaubles(player);
        for (int i = 0; i < baubles.getSizeInventory(); i++) {
            ItemStack bauble = baubles.getStackInSlot(i);
            if (bauble != null && bauble.getItem() == ForbiddenItems.consumptionCincture) return true;
        }
        return false;
    }
}
