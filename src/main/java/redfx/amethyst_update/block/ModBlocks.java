package redfx.amethyst_update.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
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
            new LanternBlock(AbstractBlock.Settings.of(Material.METAL)
                    .strength(3.5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance(state -> 10)
                    .nonOpaque()),
            ModItemGroup.AMETHYST_UPDATE);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(AmethystUpdate.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        Item item = Registry.register(Registries.ITEM, new Identifier(AmethystUpdate.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return item;
    }

    public static void registerModBlocks() {
        AmethystUpdate.LOGGER.info("Registering ModBlocks for " + AmethystUpdate.MOD_ID);
    }
}
