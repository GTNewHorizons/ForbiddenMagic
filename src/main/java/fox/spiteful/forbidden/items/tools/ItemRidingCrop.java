package fox.spiteful.forbidden.items.tools;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;

public class ItemRidingCrop extends ItemSword {

    public IIcon icon;

    public ItemRidingCrop(ToolMaterial mat) {
        super(mat);
        this.setCreativeTab(Forbidden.tab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("forbidden:crop");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase player) {
        stack.damageItem(1, player);
        if (victim instanceof EntityHorse || victim instanceof EntityPig)
            victim.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 5));
        else if (victim instanceof EntityPlayer || victim instanceof EntityGolem) {
            victim.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 1));
            victim.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 200, 1));
            victim.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 1));
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return this.icon;
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        return 1.0F;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.none;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 0;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player.ridingEntity != null && player.ridingEntity instanceof EntityLivingBase) {
            EntityLivingBase mount = (EntityLivingBase) player.ridingEntity;
            stack.damageItem(1, player);
            player.swingItem();
            // mount.attackEntityFrom(DamageSource.causePlayerDamage(player),
            // 4.0F);
            mount.attackEntityFrom(DamageSource.generic, 1.0F);
            mount.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 5));
        }
        return stack;
    }

    @Override
    public boolean getIsRepairable(ItemStack stack, ItemStack stack2) {
        return stack2.getItem() == Items.leather || super.getIsRepairable(stack, stack2);
    }

    @Override
    public boolean canHarvestBlock(Block block, ItemStack itemStack) {
        return false;
    }
}
