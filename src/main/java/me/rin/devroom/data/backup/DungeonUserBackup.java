package me.rin.devroom.data.backup;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DungeonUserBackup {

    private UUID uid;
    private Location location;
    private int food;
    private float exp;
    private double health;
    private DungeonUserInventoryBackup inventory;

    public DungeonUserBackup(Player player) {
        this.uid = player.getUniqueId();
        this.location = player.getLocation();
        this.food = player.getFoodLevel();
        this.exp = player.getExp();
        this.health = player.getHealth();
        this.inventory = new DungeonUserInventoryBackup(player.getInventory());

    }


    public void load() {
        final Player player = Bukkit.getPlayer(this.uid);
        this.inventory.load(player.getInventory());
        player.setExp(this.exp);
        player.setHealth(this.health);
        player.setFoodLevel(this.food);
        player.teleport(this.location);

    }
}
