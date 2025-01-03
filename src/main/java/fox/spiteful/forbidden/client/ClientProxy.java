package fox.spiteful.forbidden.client;

import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import fox.spiteful.forbidden.CommonProxy;
import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.tiles.TileEntityWrathCage;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderInfo() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWrathCage.class, new TileEntityWrathCageRenderer());
        VillagerRegistry.instance().registerVillagerSkin(
                Config.hereticID,
                new ResourceLocation("forbidden", "textures/models/heretic.png"));
    }
}
