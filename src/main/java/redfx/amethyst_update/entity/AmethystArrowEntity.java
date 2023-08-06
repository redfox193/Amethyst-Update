package redfx.amethyst_update.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import redfx.amethyst_update.AmethystUpdate;
import redfx.amethyst_update.effect.ModEffects;
import redfx.amethyst_update.item.ModItems;

public class AmethystArrowEntity extends PersistentProjectileEntity {
    private static double damage = 1.5f;
    private static  int duration = 70;
    private static  int amplifier = 0;

    public AmethystArrowEntity(EntityType<? extends AmethystArrowEntity> entityType, World world) {
        super((EntityType<? extends PersistentProjectileEntity>)entityType, world);
        setDamage(damage);
    }

    public AmethystArrowEntity(World world, double x, double y, double z) {
        super(ModEntities.AMETHYST_ARROW_ENTITY, x, y, z, world);
        setDamage(damage);
    }

    public AmethystArrowEntity(World world, LivingEntity owner) {
        super(ModEntities.AMETHYST_ARROW_ENTITY, owner, world);
        setDamage(damage);
    }
    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.AMETHYST_ARROW_ITEM);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);

        if((target instanceof SkeletonEntity)
                || (target instanceof WitherSkeletonEntity)
                || (target instanceof GolemEntity)
                || (target instanceof SkeletonHorseEntity))
            return;

        float f = (float)this.getVelocity().length();
        int i = MathHelper.ceil(MathHelper.clamp((double)f * 2f, 0.0, 2.147483647E9));

        if(i >= 6) {
            amplifier = 1;
            duration = 70;
            int chance = random.nextInt(100);
            if(chance > 75)
                amplifier = 2;
        }
        else {
            amplifier = 0;
            duration = 50;
        }

        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(ModEffects.BLEEDING, duration, amplifier, false, false);
        target.addStatusEffect(statusEffectInstance, this.getEffectCause());
    }
}
