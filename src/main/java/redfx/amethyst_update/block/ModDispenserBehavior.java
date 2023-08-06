package redfx.amethyst_update.block;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.minecraft.block.DispenserBlock;
import redfx.amethyst_update.entity.AmethystArrowEntity;
import redfx.amethyst_update.item.ModItems;

public class ModDispenserBehavior {
    public static void registerModDispenserBehavior() {
        DispenserBlock.registerBehavior(ModItems.AMETHYST_ARROW_ITEM, new ProjectileDispenserBehavior() {
            @Override
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                AmethystArrowEntity arrowEntity = new AmethystArrowEntity(world, position.getX(), position.getY(), position.getZ());
                arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                return arrowEntity;
            }
        });
    }
}
