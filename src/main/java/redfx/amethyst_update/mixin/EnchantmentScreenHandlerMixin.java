package redfx.amethyst_update.mixin;

import com.google.common.collect.Lists;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import redfx.amethyst_update.AmethystUpdate;
import redfx.amethyst_update.enchantment.ModEnchantments;
import redfx.amethyst_update.item.AncientStaffItem;

import java.util.ArrayList;
import java.util.List;

@Mixin( EnchantmentScreenHandler.class)
public class EnchantmentScreenHandlerMixin {
    private ArrayList<EnchantmentLevelEntry> amethystUpdateLeadEnchantment;
    private boolean onCreateLeadEnchantment;

    private boolean isStaff = false;

    @Inject(method = "onContentChanged", at = @At("HEAD"))
    public void onContentChangedModifying(Inventory inventory, CallbackInfo info) {
        onCreateLeadEnchantment = true;
        amethystUpdateLeadEnchantment = new ArrayList<>();
        ItemStack itemStack = inventory.getStack(0);
        if (!itemStack.isEmpty() && itemStack.getItem() instanceof AncientStaffItem)
            isStaff = true;
        else
            isStaff = false;
    }

    @Inject(method = "onButtonClick", at = @At("HEAD"))
    public void onButtonClickModifying(PlayerEntity player, int id, CallbackInfoReturnable<Boolean> r) {
        onCreateLeadEnchantment = false;
    }

    @ModifyVariable(method = "onButtonClick", at = @At("STORE"), ordinal = 1)
    public int modifyCost(int i) {
        if (isStaff)
            return 3;
        return i;
    }

    @Inject(method = "generateEnchantments", at = @At("RETURN"), cancellable = true)
    private void generateEnchantmentsModifying(ItemStack stack, int slot, int level, CallbackInfoReturnable< List<EnchantmentLevelEntry> > r) {
        EnchantmentScreenHandler handler = ((EnchantmentScreenHandler)(Object)this);
        int seed = handler.getSeed();
        int chance = Math.abs(handler.getSeed()) % 100;
        if(stack.getItem() instanceof AncientStaffItem) {
            ArrayList<EnchantmentLevelEntry> list = Lists.newArrayList();
            Random random = Random.create();
            if(onCreateLeadEnchantment) {
                if(slot == 0) {
                    if (chance > 15 && chance < 95) {
                        amethystUpdateLeadEnchantment.add(slot, new EnchantmentLevelEntry(ModEnchantments.TEMPERED, 1));
                        list.add(amethystUpdateLeadEnchantment.get(slot));
                    } else {
                        amethystUpdateLeadEnchantment.add(slot, new EnchantmentLevelEntry(ModEnchantments.SHATTERED, 1));
                        list.add(amethystUpdateLeadEnchantment.get(slot));
                    }
                }
                else {
                    if (chance < 80) {
                        amethystUpdateLeadEnchantment.add(slot, new EnchantmentLevelEntry(ModEnchantments.TEMPERED, 2));
                        list.add(amethystUpdateLeadEnchantment.get(slot));
                    } else {
                        amethystUpdateLeadEnchantment.add(slot, new EnchantmentLevelEntry(ModEnchantments.SHATTERED, 2));
                        list.add(amethystUpdateLeadEnchantment.get(slot));
                    }
                }
            }
            else {
                list.add(amethystUpdateLeadEnchantment.get(slot));
                int chance2 = random.nextInt(100);
                AmethystUpdate.LOGGER.info(String.valueOf(chance2));
                if(chance2 >= 50) {
                    if(chance2 % 3 == 0 && slot == 1)
                        list.add(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 3));
                    else if(chance2 % 2 == 0)
                        list.add(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 2));
                    else
                        list.add(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 1));
                }
            }
            r.setReturnValue(list);
        }
    }
}
