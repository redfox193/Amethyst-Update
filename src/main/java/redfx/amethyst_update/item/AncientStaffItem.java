package redfx.amethyst_update.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import redfx.amethyst_update.effect.ModEffects;
import redfx.amethyst_update.enchantment.ModEnchantments;
import redfx.amethyst_update.util.IPlayerDataSaver;

import javax.management.MBeanAttributeInfo;
import java.util.ArrayList;
import java.util.List;

public class AncientStaffItem extends Item{

    private final int cooldownDuration = 6 * 60 * 20;
    public ArrayList<Enchantment> currentEnchantments;
    public boolean generateNewEnchantments = true;

    public AncientStaffItem(FabricItemSettings settings) {
        super(settings);
        currentEnchantments = new ArrayList<>();
        currentEnchantments.add(null);
        currentEnchantments.add(null);
        generateNewEnchantments = false;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient) {
            stack.damage(1, miner, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }

        return true;
    }

    private void usePreciousFragility(World world, PlayerEntity user, int level, ItemStack stack) {

        int duration = 5 * 60 * 20;
        int amplifier = level - 1;
        int radius = 64;

        if(level == 2)
        {
            radius = 96;
            duration = 8 * 60 * 20;
        }

        BlockPos pos = user.getBlockPos();
        Box box = new Box(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1).expand(radius).stretch(0.0, world.getHeight(), 0.0);
        List<LivingEntity> list = world.getNonSpectatingEntities(LivingEntity.class, box);
        if (list.isEmpty()) {
            return;
        }
        for (LivingEntity livingEntity : list) {
            if (livingEntity == user)
                continue;
            if (!pos.isWithinDistance(livingEntity.getBlockPos(), (double) radius))
                continue;

            if (livingEntity.hasStatusEffect(ModEffects.TEMPERED)) {
                livingEntity.removeStatusEffect(ModEffects.TEMPERED);
                livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.SHATTERED, duration / 2, amplifier, true, true));
            }
            else
                livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.SHATTERED, duration, amplifier, true, true));
            livingEntity.playSound(SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK, 1.0f, 0.5f);

            if ((livingEntity instanceof SkeletonEntity)
                    || (livingEntity instanceof WitherSkeletonEntity)
                    || (livingEntity instanceof GolemEntity)
                    || (livingEntity instanceof SkeletonHorseEntity)
                    || (livingEntity instanceof StrayEntity)
                    || (livingEntity instanceof WitherEntity))
                continue;

            livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.BLEEDING, 50, amplifier, false, false));
        }
        stack.damage(level, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        user.playSound(SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK, 1.0f, 0.5f);
    }

    private void usePreciousSharpness(PlayerEntity user, int level, ItemStack stack) {
        int duration = 5 * 60 * 20;
        int amplifier = level - 1;
        if(level == 2)
            duration = 8 * 60 * 20;

        if (user.hasStatusEffect(ModEffects.SHATTERED)) {
            user.removeStatusEffect(ModEffects.SHATTERED);
            user.addStatusEffect(new StatusEffectInstance(ModEffects.TEMPERED, duration / 2, amplifier, true, true));
        }
        else
            user.addStatusEffect(new StatusEffectInstance(ModEffects.TEMPERED, duration, amplifier, true, true));

        stack.damage(level, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        user.playSound(SoundEvents.BLOCK_AMETHYST_CLUSTER_PLACE, 2.0f, 0.5f);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        if(EnchantmentHelper.getLevel(ModEnchantments.TEMPERED, itemStack) > 0) {
            usePreciousSharpness(user, EnchantmentHelper.getLevel(ModEnchantments.TEMPERED, itemStack), itemStack);
            user.getItemCooldownManager().set(ModItems.ANCIENT_STAFF, cooldownDuration);
            ((IPlayerDataSaver)(Object)user).setStaffCooldown(cooldownDuration);
        }
        if(EnchantmentHelper.getLevel(ModEnchantments.SHATTERED, itemStack) > 0) {
            usePreciousFragility(world, user, EnchantmentHelper.getLevel(ModEnchantments.SHATTERED, itemStack), itemStack);
            user.getItemCooldownManager().set(ModItems.ANCIENT_STAFF, cooldownDuration);
            ((IPlayerDataSaver)(Object)user).setStaffCooldown(cooldownDuration);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public int getEnchantability() {
        return 1;
    }


}
