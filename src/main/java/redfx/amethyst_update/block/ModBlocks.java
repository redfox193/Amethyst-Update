package redfx.amethyst_update.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import redfx.amethyst_update.AmethystUpdate;
import redfx.amethyst_update.item.ModItemGroup;

public class ModBlocks {
    public static final Block AMETHYST_LANTERN = registerBlock("amethyst_lantern",
            new LanternBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.IRON_GRAY)
                    .solid()
                    .requiresTool()
                    .strength(3.5F)
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance((state) -> {
                        return 10;
                    })
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY)));

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(AmethystUpdate.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        AmethystUpdate.LOGGER.info("Registering ModBlocks for " + AmethystUpdate.MOD_ID);
    }
}
