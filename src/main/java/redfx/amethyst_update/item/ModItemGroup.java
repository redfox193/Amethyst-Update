package redfx.amethyst_update.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import redfx.amethyst_update.AmethystUpdate;

public class ModItemGroup {
    public static ItemGroup AMETHYST_UPDATE;

    public static void registerItemGroups() {
        AMETHYST_UPDATE = FabricItemGroup.builder(new Identifier(AmethystUpdate.MOD_ID, "amethyst_update"))
                .displayName(Text.translatable("itemgroup.amethyst_update"))
                .icon(() -> new ItemStack(ModItems.AMETHYST_DUST)).build();
    }
}
