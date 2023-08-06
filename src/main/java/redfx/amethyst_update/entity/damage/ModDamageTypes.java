package redfx.amethyst_update.entity.damage;

import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.level.LevelInfo;
import redfx.amethyst_update.AmethystUpdate;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> BLEEDING = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            new Identifier(AmethystUpdate.MOD_ID, "bleeding"));
}
