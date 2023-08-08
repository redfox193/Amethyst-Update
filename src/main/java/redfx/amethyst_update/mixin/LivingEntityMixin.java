package redfx.amethyst_update.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import redfx.amethyst_update.AmethystUpdate;
import redfx.amethyst_update.effect.ModEffects;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @ModifyVariable(method = "modifyAppliedDamage", at = @At("HEAD"), ordinal = 0)
    private float AmethystUpdateDamageAmountModifying(float amount) {

        if (((LivingEntity)(Object)this).getWorld().isClient()) {
            return amount;
        }

        if ( ((LivingEntity)(Object)this).hasStatusEffect(ModEffects.SHATTERED) ){
            amount *= 2 + 0.5 * ((LivingEntity)(Object)this).getStatusEffect(ModEffects.SHATTERED).getAmplifier();
        }
        if ( ((LivingEntity)(Object)this).hasStatusEffect(ModEffects.TEMPERED) ) {
            amount /= 2 + 0.5 * ((LivingEntity)(Object)this).getStatusEffect(ModEffects.TEMPERED).getAmplifier();;
        }
        return  amount;
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void AmethystUpdateDamageModifying(DamageSource source, float amount, CallbackInfoReturnable<Boolean> r) {
        LivingEntity entity = ((LivingEntity)(Object)this);

        if (entity.getWorld().isClient()) {
            return;
        }
        Entity attacker = source.getAttacker();

        if ((attacker instanceof SkeletonEntity)
                || (attacker instanceof WitherSkeletonEntity)
                || (attacker instanceof GolemEntity)
                || (attacker instanceof SkeletonHorseEntity)
                || (attacker instanceof StrayEntity)
                || (attacker instanceof WitherEntity))
            return;

        if(entity.hasStatusEffect(ModEffects.TEMPERED) && attacker instanceof LivingEntity && !source.isIndirect()) {
            int amplifier = entity.getStatusEffect(ModEffects.TEMPERED).getAmplifier();
            StatusEffectInstance statusEffectInstance = null;
            if(amplifier == 0)
                statusEffectInstance = new StatusEffectInstance(ModEffects.BLEEDING, 50, amplifier, false, false);
            else
                statusEffectInstance = new StatusEffectInstance(ModEffects.BLEEDING, 70, amplifier, false, false);
            ((LivingEntity)attacker).addStatusEffect(statusEffectInstance);
        }
    }
}
