package redfx.amethyst_update.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import redfx.amethyst_update.AmethystUpdate;

public class ModEntities {

    public static EntityType<AmethystArrowEntity> AMETHYST_ARROW_ENTITY = registerEntityType("amethyst_arrow",
            createArrowEntityType(AmethystArrowEntity::new));

    private static <T extends Entity> EntityType<T> registerEntityType(String name, EntityType<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(AmethystUpdate.MOD_ID, name), entityType);
    }

    private static <T extends Entity> EntityType<T> createArrowEntityType(EntityType.EntityFactory<T> factory) {
        return FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory).dimensions(EntityDimensions.fixed(0.5f,
                0.5f)).trackRangeBlocks(4).trackedUpdateRate(20).build();
    }

    public static void registerModEntities() {
        AmethystUpdate.LOGGER.info("Registring Mod Entities for " + AmethystUpdate.MOD_ID);
    }
}
