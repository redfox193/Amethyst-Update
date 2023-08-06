package redfx.amethyst_update.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import redfx.amethyst_update.AmethystUpdate;

public class ModEnchantments {
    public static Enchantment SHATTERED = register("shattered",
            new ShatteredEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));
    public static Enchantment TEMPERED = register("tempered",
            new TemperedEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(AmethystUpdate.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        AmethystUpdate.LOGGER.info("Registring Mod Enchantments for " + AmethystUpdate.MOD_ID);
    }
}
