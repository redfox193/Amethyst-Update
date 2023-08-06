package redfx.amethyst_update.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import redfx.amethyst_update.AmethystUpdate;
import redfx.amethyst_update.item.ModItems;
import redfx.amethyst_update.util.IPlayerDataSaver;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements IPlayerDataSaver {
    private final static String AMETHYST_UPDATE_PLAYER_DATA_KEY = "amethyst_update.player_data";
    private int AmethystUpdateStaffCooldown = 0;
    private boolean AmethystUpdateSetOnEntry = true;

    private NbtCompound AmethystUpdatePersistentData;
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstructor(World world, BlockPos pos, float yaw, GameProfile gameProfile, CallbackInfo ci) {
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void staffCooldownTick(CallbackInfo info) {
        if (!((PlayerEntity)(Object)this).getWorld().isClient()) {

            --this.AmethystUpdateStaffCooldown;
            if (AmethystUpdateStaffCooldown <= 0) {
                AmethystUpdateStaffCooldown = 0;
            }
            if (AmethystUpdateSetOnEntry && AmethystUpdateStaffCooldown > 0) {
                ((PlayerEntity) (Object) this).getItemCooldownManager().set(ModItems.ANCIENT_STAFF, AmethystUpdateStaffCooldown);
                AmethystUpdateSetOnEntry = false;
            }
        }
    }

    @Override
    public int getStaffCooldown() {
        return AmethystUpdateStaffCooldown;
    }

    @Override
    public void setStaffCooldown(int i) {
        AmethystUpdateStaffCooldown = i;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void onWriteCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
        nbt.put(AMETHYST_UPDATE_PLAYER_DATA_KEY, getAmethystUpdatePersistentData());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void onReadCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains(AMETHYST_UPDATE_PLAYER_DATA_KEY, 10)) {
            AmethystUpdatePersistentData = nbt.getCompound(AMETHYST_UPDATE_PLAYER_DATA_KEY);
            if(AmethystUpdatePersistentData.contains("cooldown")) {
                AmethystUpdateStaffCooldown = AmethystUpdatePersistentData.getInt("cooldown");
            }
            else {
                AmethystUpdatePersistentData.putInt("cooldown", AmethystUpdateStaffCooldown);
            }
        } else {
            AmethystUpdatePersistentData = getAmethystUpdatePersistentData();
        }
    }

    @Override
    public NbtCompound getAmethystUpdatePersistentData() {
        if (AmethystUpdatePersistentData == null)
            AmethystUpdatePersistentData = new NbtCompound();
        AmethystUpdatePersistentData.putInt("cooldown", AmethystUpdateStaffCooldown);

        return AmethystUpdatePersistentData;
    }

}
