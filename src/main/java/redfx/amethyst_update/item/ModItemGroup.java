package redfx.amethyst_update.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtil;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import redfx.amethyst_update.AmethystUpdate;
import redfx.amethyst_update.enchantment.ModEnchantments;
import redfx.amethyst_update.potion.ModPotions;

public class ModItemGroup {
    public static ItemGroup AMETHYST_UPDATE = Registry.register(Registries.ITEM_GROUP, new Identifier(AmethystUpdate.MOD_ID, "amethyst_update"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.amethyst_update"))
                    .icon(() -> new ItemStack(ModItems.AMETHYST_DUST)).entries((displayContext, entries) -> {
                        entries.add(ModItems.AMETHYST_LANTERN);
                        entries.add(ModItems.AMETHYST_ARROW_ITEM);
                        entries.add(ModItems.REINFORCED_AMETHYST);
                        entries.add(ModItems.MYSTERIOUS_STICK);

                        entries.add(ModItems.ANCIENT_STAFF);
                        entries.add(ModItems.AMETHYST_DUST);
                        entries.add(PotionUtil.setPotion(new ItemStack(Items.POTION), ModPotions.BLEEDING_POTION));
                        entries.add(PotionUtil.setPotion(new ItemStack(Items.POTION), ModPotions.LONG_BLEEDING_POTION));
                        entries.add(PotionUtil.setPotion(new ItemStack(Items.POTION), ModPotions.STRONG_BLEEDING_POTION));

                        entries.add(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.BLEEDING_POTION));
                        entries.add(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.LONG_BLEEDING_POTION));
                        entries.add(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.STRONG_BLEEDING_POTION));

                        entries.add(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.BLEEDING_POTION));
                        entries.add(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.LONG_BLEEDING_POTION));
                        entries.add(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.STRONG_BLEEDING_POTION));

                        entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.TEMPERED, 1)));
                        entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.TEMPERED, ModEnchantments.TEMPERED.getMaxLevel())));

                        entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.SHATTERED, 1)));
                        entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.SHATTERED, ModEnchantments.SHATTERED.getMaxLevel())));

                    }).build());
    public static void registerItemGroups() {
        AmethystUpdate.LOGGER.info("register Mod ItemGroup fot: " + AmethystUpdate.MOD_ID);
    }
}
