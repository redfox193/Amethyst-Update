package redfx.amethyst_update.mixin;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.screen.ingame.EnchantmentScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TeleportTarget;
import org.apache.commons.compress.harmony.pack200.NewAttributeBands;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import redfx.amethyst_update.AmethystUpdate;
import redfx.amethyst_update.enchantment.ModEnchantments;
import redfx.amethyst_update.mixin.EnchantmentScreenHandlerMixin;

import javax.swing.text.TabExpander;
import java.util.ArrayList;
import java.util.List;

@Mixin( EnchantmentScreen.class)
public class EnchantmentScreenMixin {

    private  boolean isStaffEnchantment() {
        EnchantmentScreen screen = ((EnchantmentScreen)(Object)this);
        EnchantmentScreenHandler screenHandler = screen.getScreenHandler();
        if(Registries.ENCHANTMENT.get(screenHandler.enchantmentId[1]) != null) {
            Enchantment enchantment = Registries.ENCHANTMENT.get(screenHandler.enchantmentId[1]);
            if(enchantment == ModEnchantments.SHATTERED || enchantment == ModEnchantments.TEMPERED)
                return true;
        }
        return false;
    }

    @ModifyArgs(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"))
    public void drawBackgroundModifying(Args args) {
        if(((int) args.get(4) == 239 || (int) args.get(4) == 223) && isStaffEnchantment())
            args.set(3, 32);
    }

    @ModifyVariable(method = "drawBackground", at = @At("STORE"), ordinal = 4)
    public int modifyCost(int k) {
        if(isStaffEnchantment() && k < 3)
            return 0;
        else
            return k;
    }

    @ModifyVariable(method = "render", at = @At("STORE"), ordinal = 6)
    public int modifyCostOnTooltip(int m) {
        if(isStaffEnchantment())
            return 3;
        else
            return m;
    }
}
