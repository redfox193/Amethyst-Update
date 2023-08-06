package redfx.amethyst_update.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import redfx.amethyst_update.entity.AmethystArrowEntity;

@Environment(EnvType.CLIENT)
public class AmethystArrowEntityRenderer extends ProjectileEntityRenderer<AmethystArrowEntity> {
    public static final Identifier TEXTURE = new Identifier("amethyst_update:textures/entity/amethyst_arrow.png");
    public AmethystArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(AmethystArrowEntity entity) {
        return TEXTURE;
    }
}
