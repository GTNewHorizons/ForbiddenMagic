package fox.spiteful.forbidden.items;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;
import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.compat.Compat;
import fox.spiteful.forbidden.items.baubles.ItemRingNutrition;
import fox.spiteful.forbidden.items.baubles.ItemSubCollar;
import fox.spiteful.forbidden.items.scribes.ItemBloodwell;
import fox.spiteful.forbidden.items.scribes.ItemBoundwell;
import fox.spiteful.forbidden.items.scribes.ItemCrystalwell;
import fox.spiteful.forbidden.items.scribes.ItemPrimewell;
import fox.spiteful.forbidden.items.tools.ItemBloodRapier;
import fox.spiteful.forbidden.items.tools.ItemDiabolistFork;
import fox.spiteful.forbidden.items.tools.ItemMorphAxe;
import fox.spiteful.forbidden.items.tools.ItemMorphPickaxe;
import fox.spiteful.forbidden.items.tools.ItemMorphShovel;
import fox.spiteful.forbidden.items.tools.ItemMorphSword;
import fox.spiteful.forbidden.items.tools.ItemRidingCrop;
import fox.spiteful.forbidden.items.tools.ItemSkullAxe;
import fox.spiteful.forbidden.items.tools.ItemTaintPickaxe;
import fox.spiteful.forbidden.items.tools.ItemTaintShovel;
import fox.spiteful.forbidden.items.wands.BloodStaffUpdate;
import fox.spiteful.forbidden.items.wands.BloodWandUpdate;
import fox.spiteful.forbidden.items.wands.CreativeWandUpdate;
import fox.spiteful.forbidden.items.wands.DarkWandCap;
import fox.spiteful.forbidden.items.wands.InfernalWandUpdate;
import fox.spiteful.forbidden.items.wands.ItemFocusBlink;
import fox.spiteful.forbidden.items.wands.ItemWandCaps;
import fox.spiteful.forbidden.items.wands.ItemWandCores;
import fox.spiteful.forbidden.items.wands.ManaStaffUpdate;
import fox.spiteful.forbidden.items.wands.ManaWandUpdate;
import fox.spiteful.forbidden.items.wands.ProfaneWandUpdate;
import fox.spiteful.forbidden.items.wands.TaintedWandUpdate;
import fox.spiteful.forbidden.items.wands.YandereWandUpdate;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;

public class ForbiddenItems {

    public static Item deadlyShards;
    public static Item gluttonyShard;
    public static Item skullAxe;
    public static Item arcaneCakeItem;
    public static Item taintShovel;
    public static Item wandCore;
    public static Item wandCap;
    public static Item resource;
    public static Item taintPickaxe;
    public static Item mobCrystal;
    public static Item fork;
    public static Item morphPickaxe;
    public static Item morphSword;
    public static Item morphShovel;
    public static Item morphAxe;
    public static Item crystalwell;
    public static Item primewell;
    public static Item ridingCrop;
    public static Item subCollar;
    public static Item ringFood;
    public static Item taintFruit;
    public static Item taintCoal;

    public static Item blinkFocus;

    public static Item bloodRapier;
    public static Item bloodwell;
    public static Item bloodOrb;
    public static Item boundwell;

    public static Item dragonslayer;

    public static WandRod WAND_ROD_TAINTED;
    public static WandRod WAND_ROD_INFERNAL;
    public static WandRod WAND_ROD_NEUTRONIUM;
    public static WandRod WAND_ROD_BLOOD;
    public static WandRod WAND_ROD_WITCHWOOD;
    public static WandRod WAND_ROD_LIVINGWOOD;
    public static WandRod WAND_ROD_DREAMWOOD;
    public static WandRod WAND_ROD_EQUIVALENT;
    public static WandRod WAND_ROD_PROFANE;
    public static WandRod WAND_ROD_PROFANED;
    public static StaffRod STAFF_ROD_BLOOD;
    public static StaffRod STAFF_ROD_NEUTRONIUM;
    public static StaffRod STAFF_ROD_WITCHWOOD;
    public static StaffRod STAFF_ROD_DREAMWOOD;
    public static WandCap WAND_CAP_ORICHALCUM;
    public static WandCap WAND_CAP_ALCHEMICAL;
    public static WandCap WAND_CAP_VINTEUM;
    public static WandCap WAND_CAP_MANASTEEL;
    public static WandCap WAND_CAP_ELEMENTIUM;
    public static WandCap WAND_CAP_TERRASTEEL;

    public static void addItems() {
        deadlyShards = new ItemDeadlyShard().setUnlocalizedName("NetherShard");
        GameRegistry.registerItem(deadlyShards, "NetherShard");

        gluttonyShard = new ItemGluttonyShard().setUnlocalizedName("GluttonyShard");
        GameRegistry.registerItem(gluttonyShard, "GluttonyShard");

        taintShovel = new ItemTaintShovel(ThaumcraftApi.toolMatElemental).setUnlocalizedName("TaintShovel");
        GameRegistry.registerItem(taintShovel, "TaintShovel");

        taintPickaxe = new ItemTaintPickaxe(ThaumcraftApi.toolMatElemental).setUnlocalizedName("TaintPickaxe");
        GameRegistry.registerItem(taintPickaxe, "TaintPickaxe");

        arcaneCakeItem = new ItemArcaneCake().setUnlocalizedName("ArcaneCake");
        GameRegistry.registerItem(arcaneCakeItem, "ArcaneCakeItem");

        skullAxe = new ItemSkullAxe(ThaumcraftApi.toolMatElemental).setUnlocalizedName("SkullAxe");
        GameRegistry.registerItem(skullAxe, "SkullAxe");

        morphPickaxe = new ItemMorphPickaxe(ThaumcraftApi.toolMatElemental).setUnlocalizedName("MorphPickaxe");
        GameRegistry.registerItem(morphPickaxe, "MorphPickaxe");

        morphSword = new ItemMorphSword(ThaumcraftApi.toolMatElemental).setUnlocalizedName("MorphSword");
        GameRegistry.registerItem(morphSword, "MorphSword");

        morphShovel = new ItemMorphShovel(ThaumcraftApi.toolMatElemental).setUnlocalizedName("MorphShovel");
        GameRegistry.registerItem(morphShovel, "MorphShovel");

        morphAxe = new ItemMorphAxe(ThaumcraftApi.toolMatElemental).setUnlocalizedName("MorphAxe");
        GameRegistry.registerItem(morphAxe, "MorphAxe");

        if (Config.wrathCage) {
            mobCrystal = new ItemMobCrystal().setUnlocalizedName("MobCrystal");
            GameRegistry.registerItem(mobCrystal, "MobCrystal");
        }

        fork = new ItemDiabolistFork(ThaumcraftApi.toolMatThaumium).setUnlocalizedName("DiabolistFork");
        if (Config.spork) fork.setUnlocalizedName("DiabolistSpork");
        GameRegistry.registerItem(fork, "DiabolistFork");

        wandCore = new ItemWandCores().setUnlocalizedName("WandCores");
        GameRegistry.registerItem(wandCore, "WandCores");
        wandCap = new ItemWandCaps().setUnlocalizedName("WandCaps");
        GameRegistry.registerItem(wandCap, "WandCaps");
        WAND_ROD_TAINTED = new WandRod(
                "tainted",
                Config.taintedCoreCap,
                new ItemStack(wandCore, 1, 0),
                12,
                new TaintedWandUpdate(),
                new ResourceLocation("forbidden", "textures/models/wand_rod_tainted.png"));
        WAND_ROD_INFERNAL = new WandRod(
                "infernal",
                Config.infernalCoreCap,
                new ItemStack(wandCore, 1, 1),
                12,
                new InfernalWandUpdate(),
                new ResourceLocation("forbidden", "textures/models/wand_rod_infernal.png"));
        WAND_ROD_NEUTRONIUM = new WandRod(
                "neutronium",
                9001,
                new ItemStack(Blocks.bedrock, 1),
                1000,
                new CreativeWandUpdate(),
                new ResourceLocation("forbidden", "textures/models/wand_rod_neutronium.png"));
        WAND_ROD_NEUTRONIUM.setGlowing(true);
        WAND_ROD_BLOOD = new WandRod(
                "blood",
                Config.bloodCoreCap,
                new ItemStack(wandCore, 1, 3),
                12,
                new BloodWandUpdate(),
                new ResourceLocation("forbidden", "textures/models/wand_rod_blood.png"));
        WAND_ROD_WITCHWOOD = new WandRod(
                "witchwood",
                Config.witchwoodCoreCap,
                new ItemStack(wandCore, 1, 4),
                12,
                new ManaWandUpdate(),
                new ResourceLocation("forbidden", "textures/models/wand_rod_witchwood.png"));
        WAND_ROD_LIVINGWOOD = new WandRod(
                "livingwood",
                Config.livingwoodCoreCap,
                new ItemStack(wandCore, 1, 7),
                12,
                new YandereWandUpdate(),
                new ResourceLocation("forbidden", "textures/models/wand_rod_livingwood.png"));
        WAND_ROD_DREAMWOOD = new WandRod(
                "dreamwood",
                Config.dreamwoodCoreCap,
                new ItemStack(wandCore, 1, 11),
                12,
                new YandereWandUpdate(),
                new ResourceLocation("forbidden", "textures/models/wand_rod_dreamwood.png"));
        WAND_ROD_EQUIVALENT = new WandRod(
                "equivalent",
                100,
                new ItemStack(wandCore, 1, 14),
                12,
                new ResourceLocation("forbidden", "textures/models/wand_rod_equivalent.png"));
        WAND_ROD_PROFANE = new WandRod(
                "profane",
                Config.profaneCoreCap,
                new ItemStack(wandCore, 1, 5),
                12,
                new ProfaneWandUpdate(),
                new ResourceLocation("forbidden", "textures/models/wand_rod_profane.png"));
        WAND_ROD_PROFANED = new WandRod(
                "profaned",
                Config.profaneCoreCap,
                new ItemStack(Blocks.bedrock, 1),
                12,
                new ResourceLocation("forbidden", "textures/models/wand_rod_profaned.png"));
        STAFF_ROD_BLOOD = new StaffRod(
                "blood",
                Config.bloodStaffCap,
                new ItemStack(wandCore, 1, 9),
                24,
                new BloodStaffUpdate(),
                new ResourceLocation("forbidden", "textures/models/wand_rod_blood.png"));
        STAFF_ROD_NEUTRONIUM = new StaffRod(
                "neutronium",
                9002,
                new ItemStack(Blocks.bedrock, 1),
                1000,
                new CreativeWandUpdate(),
                new ResourceLocation("forbidden", "textures/models/wand_rod_neutronium.png"));
        STAFF_ROD_NEUTRONIUM.setGlowing(true);
        STAFF_ROD_WITCHWOOD = new StaffRod(
                "witchwood",
                Config.witchwoodStaffCap,
                new ItemStack(wandCore, 1, 10),
                24,
                new ManaStaffUpdate(),
                new ResourceLocation("forbidden", "textures/models/wand_rod_witchwood.png"));
        STAFF_ROD_DREAMWOOD = new StaffRod(
                "dreamwood",
                Config.dreamwoodStaffCap,
                new ItemStack(wandCore, 1, 13),
                25,
                new YandereWandUpdate(),
                new ResourceLocation("forbidden", "textures/models/wand_rod_dreamwood.png"));
        STAFF_ROD_DREAMWOOD.setRunes(true);
        WAND_CAP_ORICHALCUM = new DarkWandCap(
                "orichalcum",
                0.0F,
                new ItemStack(Blocks.command_block, 1),
                1000,
                new ResourceLocation("forbidden", "textures/models/wand_cap_orichalcum.png"));
        WAND_CAP_ALCHEMICAL = new DarkWandCap(
                "alchemical",
                (float) (Config.alchemicalDiscount) / 100F,
                Arrays.asList(new Aspect[] { Aspect.WATER }),
                (float) (Config.alchemicalDiscount) - .10F,
                new ItemStack(wandCap, 1, 0),
                7,
                new ResourceLocation("forbidden", "textures/models/wand_cap_alchemical.png"));
        WAND_CAP_VINTEUM = new DarkWandCap(
                "vinteum",
                (float) (Config.vinteumDiscount),
                new ItemStack(wandCap, 1, 1),
                6,
                new ResourceLocation("forbidden", "textures/models/wand_cap_vinteum.png"));
        WAND_CAP_MANASTEEL = new DarkWandCap(
                "manasteel",
                (float) (Config.manasteelDiscount),
                new ItemStack(wandCap, 1, 3),
                6,
                new ResourceLocation("forbidden", "textures/models/wand_cap_manasteel.png"));
        WAND_CAP_ELEMENTIUM = new DarkWandCap(
                "elementium",
                (float) (Config.elementiumDiscount),
                new ItemStack(wandCap, 1, 5),
                9,
                new ResourceLocation("forbidden", "textures/models/wand_cap_elementium.png"));
        WAND_CAP_TERRASTEEL = new DarkWandCap(
                "terrasteel",
                (float) (Config.terrasteelDiscount),
                new ItemStack(wandCap, 1, 2),
                1,
                new ResourceLocation("forbidden", "textures/models/wand_cap_terrasteel.png"));

        resource = new ItemResource().setUnlocalizedName("FMResource");
        GameRegistry.registerItem(resource, "FMResource");
        OreDictionary.registerOre("nuggetEmerald", new ItemStack(resource, 1, 0));
        OreDictionary.registerOre("dyeBlack", new ItemStack(resource, 1, 1));

        if (Compat.bm) {
            try {
                bloodwell = new ItemBloodwell().setUnlocalizedName("Bloodwell");
                GameRegistry.registerItem(bloodwell, "Bloodwell");

                bloodOrb = new ItemDivineOrb().setUnlocalizedName("EldritchOrb");
                GameRegistry.registerItem(bloodOrb, "EldritchOrb");

                boundwell = new ItemBoundwell().setUnlocalizedName("Boundwell");
                GameRegistry.registerItem(boundwell, "Boundwell");

                bloodRapier = new ItemBloodRapier().setUnlocalizedName("BloodRapier");
                GameRegistry.registerItem(bloodRapier, "BloodRapier");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        crystalwell = new ItemCrystalwell().setUnlocalizedName("Crystalwell");
        GameRegistry.registerItem(crystalwell, "Crystalwell");

        primewell = new ItemPrimewell().setUnlocalizedName("Primewell");
        GameRegistry.registerItem(primewell, "Primewell");

        ridingCrop = new ItemRidingCrop(ToolMaterial.WOOD).setUnlocalizedName("RidingCrop");
        GameRegistry.registerItem(ridingCrop, "RidingCrop");

        if (!Config.noLust) {
            subCollar = new ItemSubCollar().setUnlocalizedName("SubCollar");
            GameRegistry.registerItem(subCollar, "SubCollar");
        }

        ringFood = new ItemRingNutrition().setUnlocalizedName("RingNutrition");
        GameRegistry.registerItem(ringFood, "RingNutrition");

        taintFruit = new ItemFruitTainted().setUnlocalizedName("TaintFruit");
        GameRegistry.registerItem(taintFruit, "TaintFruit");

        taintCoal = new ItemTaintCoal().setUnlocalizedName("TaintCoal");
        GameRegistry.registerItem(taintCoal, "TaintCoal");

        blinkFocus = new ItemFocusBlink().setUnlocalizedName("BlinkFocus");
        GameRegistry.registerItem(blinkFocus, "BlinkFocus");
    }

    public static FocusUpgradeType getUpgrade(int id, ResourceLocation icon, String name, String text,
            AspectList aspects) {
        if (id >= FocusUpgradeType.types.length) {
            FocusUpgradeType[] temp = new FocusUpgradeType[id + 1];
            System.arraycopy(FocusUpgradeType.types, 0, temp, 0, FocusUpgradeType.types.length);
            FocusUpgradeType.types = temp;
        }
        return new FocusUpgradeType(id, icon, name, text, aspects);
    }

    public static boolean isEffective(ItemStack stack, Block block, int meta, Material[] materials) {
        if (ForgeHooks.isToolEffective(stack, block, meta)) {
            return true;
        } else {
            Material material = block.getMaterial();
            for (Material m : materials) if (m == material) return true;
        }
        return false;
    }
}
