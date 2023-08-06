package redfx.amethyst_update.util;


import net.minecraft.nbt.NbtCompound;

public interface IPlayerDataSaver {
    int getStaffCooldown();
    void setStaffCooldown(int i);
    NbtCompound getAmethystUpdatePersistentData();
}
