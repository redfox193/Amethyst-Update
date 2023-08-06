package redfx.amethyst_update.data;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import redfx.amethyst_update.item.ModItems;

public class ModCustomTrades {
    public static void registerCustomTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(2,
                factories -> factories.add((entity, random) -> new TradeOffer(
                        new ItemStack(Items.EMERALD, 32),
                        new ItemStack(ModItems.MYSTERIOUS_STICK, 1),
                        1, 0, 0)));
    }
}
