package me.rin.devroom.backup;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class DungeonUserInventoryBackup {

    private final ItemStack[] inventoryContents;
    private final ItemStack[] armorContents;

    public DungeonUserInventoryBackup(PlayerInventory inventory) {
        this.inventoryContents = inventory.getContents();
        this.armorContents = inventory.getArmorContents();
    }

    public void load(PlayerInventory inventory) {
        inventory.setContents(this.inventoryContents);
        inventory.setArmorContents(this.armorContents);
    }
}