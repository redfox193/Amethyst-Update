package redfx.amethyst_update.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import redfx.amethyst_update.enchantment.ModEnchantments;
import redfx.amethyst_update.util.IPlayerDataSaver;

public class AmethystDustItem extends Item {

    public AmethystDustItem(Settings settings) {
        super(settings);
    }
}
