package redfx.amethyst_update.entity.damage;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;

public class ModDamageSources {
    public static DamageSource bleeding(DynamicRegistryManager registryManager)
    {
        return new DamageSource(registryManager.get(RegistryKeys.DAMAGE_TYPE).entryOf(ModDamageTypes.BLEEDING));
    }
}
