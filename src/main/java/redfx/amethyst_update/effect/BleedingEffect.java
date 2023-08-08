package redfx.amethyst_update.effect;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.registry.DynamicRegistryManager;
import redfx.amethyst_update.AmethystUpdate;
import redfx.amethyst_update.entity.damage.ModDamageSources;
import redfx.amethyst_update.entity.damage.ModDamageTypes;

public class BleedingEffect extends StatusEffect {
    private static float damage = 1.25f;
    protected BleedingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);

        if (entity.getWorld().isClient()) {
            return;
        }

        if((entity instanceof SkeletonEntity)
                || (entity instanceof WitherSkeletonEntity)
                || (entity instanceof GolemEntity)
                || (entity instanceof SkeletonHorseEntity)
                || (entity instanceof WitherEntity))
            return;

        DynamicRegistryManager registry = entity.getWorld().getRegistryManager();
        entity.damage(ModDamageSources.bleeding(registry), damage + 0.25f * amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier)
    {
        boolean res = super.canApplyUpdateEffect(duration, amplifier);
        if(res == true)
            return true;

        if(duration % 20 == 0)
            return true;
        else
            return false;
    }
}
