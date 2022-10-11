package me.rin.devroom.data.backup;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class DungeonUserInventoryBackup {
    private ItemStack[] inventoryContents;
    private ItemStack[] armorContents;
    public DungeonUserInventoryBackup(PlayerInventory inventory) {
        this.inventoryContents = inventory.getContents();
        this.armorContents = inventory.getArmorContents();
    }

    public void load(PlayerInventory inventory) {
        inventory.setContents(this.inventoryContents);
        inventory.setArmorContents(this.armorContents);
    }
}
