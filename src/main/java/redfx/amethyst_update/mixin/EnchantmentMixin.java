package redfx.amethyst_update.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import redfx.amethyst_update.AmethystUpdate;
import redfx.amethyst_update.item.AncientStaffItem;

@Mixin( Enchantment.class)
public class EnchantmentMixin {
    @Inject(method = "isAcceptableItem", at = @At("RETURN"), cancellable = true)
    public void isAcceptableItemMendingModifying(ItemStack stack, CallbackInfoReturnable<Boolean> r) {
        if(((Enchantment)(Object)this) instanceof MendingEnchantment && stack.getItem() instanceof AncientStaffItem) {
            r.setReturnValue(false);
        }
    }
}
