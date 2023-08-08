package redfx.amethyst_update.potion;

import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import redfx.amethyst_update.AmethystUpdate;
import redfx.amethyst_update.effect.ModEffects;
import redfx.amethyst_update.item.ModItems;

public class ModPotions {
    public static Potion BLEEDING_POTION = register("bleeding_potion",
            new Potion(new StatusEffectInstance(ModEffects.BLEEDING, 20 * 20)));

    public static Potion LONG_BLEEDING_POTION = register("long_bleeding_potion",
            new Potion(new StatusEffectInstance(ModEffects.BLEEDING, 30 * 20)));

    public static Potion STRONG_BLEEDING_POTION = register("strong_bleeding_potion",
            new Potion(new StatusEffectInstance(ModEffects.BLEEDING, 20 * 20, 1)));



    private static Potion register(String name, Potion potion) {
        return Registry.register(Registries.POTION, new Identifier(AmethystUpdate.MOD_ID, name), potion);
    }

    public static void registerModPotions() {
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, ModItems.AMETHYST_DUST, ModPotions.BLEEDING_POTION);
        BrewingRecipeRegistry.registerPotionRecipe(ModPotions.BLEEDING_POTION, Items.REDSTONE, ModPotions.LONG_BLEEDING_POTION);
        BrewingRecipeRegistry.registerPotionRecipe(ModPotions.BLEEDING_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_BLEEDING_POTION);
        AmethystUpdate.LOGGER.info("Registring Mod Potions for " + AmethystUpdate.MOD_ID);
    }
}
