package redfx.amethyst_update;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.AmethystBlock;
import net.minecraft.client.render.RenderLayer;
import redfx.amethyst_update.block.ModBlocks;
import redfx.amethyst_update.client.render.entity.AmethystArrowEntityRenderer;
import redfx.amethyst_update.entity.ModEntities;

@Environment(EnvType.CLIENT)
public class AmethystUpdateClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.AMETHYST_ARROW_ENTITY, AmethystArrowEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AMETHYST_LANTERN, RenderLayer.getCutout());
    }
}
