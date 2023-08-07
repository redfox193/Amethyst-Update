package redfx.amethyst_update;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redfx.amethyst_update.block.ModBlocks;
import redfx.amethyst_update.block.ModDispenserBehavior;
import redfx.amethyst_update.data.ModCustomTrades;
import redfx.amethyst_update.effect.ModEffects;
import redfx.amethyst_update.enchantment.ModEnchantments;
import redfx.amethyst_update.entity.ModEntities;
import redfx.amethyst_update.item.ModItemGroup;
import redfx.amethyst_update.item.ModItems;
import redfx.amethyst_update.data.ModLootTableModifiers;
import redfx.amethyst_update.potion.ModPotions;

public class AmethystUpdate implements ModInitializer {
	public static final String MOD_ID = "amethyst_update";
    public static final Logger LOGGER = LoggerFactory.getLogger("amethyst_update");

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();
		ModEffects.registerModEffects();
		ModDispenserBehavior.registerModDispenserBehavior();
		ModLootTableModifiers.modifyLootTables();
		ModCustomTrades.registerCustomTrades();
		ModEnchantments.registerModEnchantments();
		ModPotions.registerModPotions();

		ModItemGroup.registerItemGroups();
	}
}