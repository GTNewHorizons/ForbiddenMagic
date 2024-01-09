package fox.spiteful.forbidden.items.scribes;

import java.util.Iterator;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;
import thaumcraft.api.IScribeTools;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemInkwell;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.research.ResearchManager;

public class ItemCrystalwell extends ItemInkwell implements IScribeTools {

    @SideOnly(Side.CLIENT)
    public IIcon icon;

    public ItemCrystalwell() {
        maxStackSize = 1;
        canRepair = false;
        if (Config.researchDifficulty == -1) setMaxDamage(50);
        else setMaxDamage(100);
        setCreativeTab(Forbidden.tab);
        setHasSubtypes(false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("forbidden:crystalwell");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int par1) {
        return this.icon;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (Config.researchDifficulty != -1 && stack.getItemDamage() >= 100) {
            if (!world.isRemote) {
                Aspect aspect;
                short amount;
                for (Iterator count = Aspect.getPrimalAspects().iterator(); count.hasNext(); PacketHandler.INSTANCE
                        .sendTo(
                                new PacketAspectPool(
                                        aspect.getTag(),
                                        Short.valueOf(amount),
                                        Short.valueOf(
                                                Thaumcraft.proxy.playerKnowledge
                                                        .getAspectPoolFor(player.getCommandSenderName(), aspect))),
                                (EntityPlayerMP) player)) {
                    aspect = (Aspect) count.next();
                    amount = (short) (world.rand.nextInt(4) + 4);
                    Thaumcraft.proxy.playerKnowledge.addAspectPool(player.getCommandSenderName(), aspect, amount);
                    ResearchManager.scheduleSave(player);
                }
            }
            player.swingItem();
            return new ItemStack(ConfigItems.itemInkwell, 1, 100);
        } else return stack;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {

        if (world.isRemote) return;
        if (Config.researchDifficulty == -1 && entity.ticksExisted % 200 == 0 && (entity instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) entity;
            Aspect aspect = (Aspect) Aspect.getPrimalAspects().get(world.rand.nextInt(6));
            short amount = (short) (world.rand.nextInt(4) + 1);
            Thaumcraft.proxy.playerKnowledge.addAspectPool(player.getCommandSenderName(), aspect, amount);
            PacketHandler.INSTANCE.sendTo(
                    new PacketAspectPool(
                            aspect.getTag(),
                            amount,
                            Short.valueOf(
                                    Thaumcraft.proxy.playerKnowledge
                                            .getAspectPoolFor(player.getCommandSenderName(), aspect))),
                    (EntityPlayerMP) player);
            ResearchManager.scheduleSave(player);
            stack.setItemDamage(stack.getItemDamage() + 1);
            if (stack.getItemDamage() >= stack.getMaxDamage())
                ((EntityPlayer) entity).inventory.setInventorySlotContents(slot, null);
        }
    }

    /**
     * Render Pass sensitive version of hasEffect()
     */
    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack, int pass) {
        return Config.researchDifficulty != -1 && stack.getItemDamage() >= 100;
    }
}
