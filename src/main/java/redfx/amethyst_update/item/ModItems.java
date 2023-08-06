package redfx.amethyst_update.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.InstrumentTags;
import net.minecraft.util.Identifier;
import redfx.amethyst_update.AmethystUpdate;
import redfx.amethyst_update.enchantment.ModEnchantments;
import redfx.amethyst_update.potion.ModPotions;

public class ModItems {
    public static final Item AMETHYST_ARROW_ITEM = registerItem("amethyst_arrow",
            new AmethystArrowItem(new FabricItemSettings()));

    public static final Item REINFORCED_AMETHYST = registerItem("reinforced_amethyst",
            new ReinforcedAmethystItem(new FabricItemSettings().fireproof()));

    public static final Item MYSTERIOUS_STICK = registerItem("mysterious_stick",
            new MysteriousStickItem(new FabricItemSettings().fireproof()));

    public static final Item ANCIENT_STAFF = registerItem("ancient_staff",
            new AncientStaffItem(new FabricItemSettings().maxDamage(100).fireproof()));

    public static final Item AMETHYST_DUST = registerItem("amethyst_dust",
            new AmethystDustItem(new FabricItemSettings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AmethystUpdate.MOD_ID, name), item);
    }

    public static void addItemsToItemGroup() {
        addToItemGroup(ModItemGroup.AMETHYST_UPDATE, AMETHYST_ARROW_ITEM);
        addToItemGroup(ModItemGroup.AMETHYST_UPDATE, REINFORCED_AMETHYST);
        addToItemGroup(ModItemGroup.AMETHYST_UPDATE, MYSTERIOUS_STICK);
        addToItemGroup(ModItemGroup.AMETHYST_UPDATE, ANCIENT_STAFF);
        addToItemGroup(ModItemGroup.AMETHYST_UPDATE, AMETHYST_DUST);

        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(PotionUtil.setPotion(new ItemStack(Items.POTION), ModPotions.BLEEDING_POTION)));
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(PotionUtil.setPotion(new ItemStack(Items.POTION), ModPotions.LONG_BLEEDING_POTION)));
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(PotionUtil.setPotion(new ItemStack(Items.POTION), ModPotions.STRONG_BLEEDING_POTION)));

        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.BLEEDING_POTION)));
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.LONG_BLEEDING_POTION)));
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.STRONG_BLEEDING_POTION)));

        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.BLEEDING_POTION)));
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.LONG_BLEEDING_POTION)));
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.STRONG_BLEEDING_POTION)));

        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.STRONG_BLEEDING_POTION)));

        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.TEMPERED, 1))));
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.TEMPERED, ModEnchantments.TEMPERED.getMaxLevel()))));
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.SHATTERED, 1))));
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.AMETHYST_UPDATE).register(entries ->
                entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.SHATTERED, ModEnchantments.SHATTERED.getMaxLevel()))));

    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems() {
        AmethystUpdate.LOGGER.info("Registring Mod Items for " + AmethystUpdate.MOD_ID);
    }
}
