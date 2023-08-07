package redfx.amethyst_update.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import redfx.amethyst_update.AmethystUpdate;
import redfx.amethyst_update.item.AncientStaffItem;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "calculateRequiredExperienceLevel", at = @At("RETURN"), cancellable = true)
    private static void calculateRequiredExperienceLevelModifying(Random random, int slotIndex, int bookshelfCount, ItemStack stack, CallbackInfoReturnable<Integer> r) {

        if(stack.getItem() instanceof AncientStaffItem) {
            if(bookshelfCount < 15) {
                r.setReturnValue(0);
                return;
            }
            switch (slotIndex) {
                case 0:
                    r.setReturnValue(30);
                    break;
                case 1:
                    r.setReturnValue(32);
                    break;
                case 2:
                    r.setReturnValue(0);
                    break;
            }
        }
    }
}