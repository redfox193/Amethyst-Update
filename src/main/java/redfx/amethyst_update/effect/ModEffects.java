package redfx.amethyst_update.effect;


import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import redfx.amethyst_update.AmethystUpdate;

public class ModEffects {
    public static StatusEffect BLEEDING = Registry.register(Registries.STATUS_EFFECT, new Identifier(AmethystUpdate.MOD_ID, "bleeding"),
            new BleedingEffect(StatusEffectCategory.HARMFUL, 0xB38EF3));
    public static StatusEffect SHATTERED = Registry.register(Registries.STATUS_EFFECT, new Identifier(AmethystUpdate.MOD_ID, "shattered"),
            new ShatteredEffect(StatusEffectCategory.HARMFUL, 0x6F4FAB));
    public static StatusEffect TEMPERED = Registry.register(Registries.STATUS_EFFECT, new Identifier(AmethystUpdate.MOD_ID, "tempered"),
            new TemperedEffect(StatusEffectCategory.BENEFICIAL, 0x6F4FAB));

    public static void registerModEffects() {
        AmethystUpdate.LOGGER.info("Registring Mod Effects for " + AmethystUpdate.MOD_ID);
    }
}
