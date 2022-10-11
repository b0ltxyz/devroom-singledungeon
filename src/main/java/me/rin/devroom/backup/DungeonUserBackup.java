package me.rin.devroom.backup;

import com.hakan.core.utils.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DungeonUserBackup {

    private final UUID uid;
    private final Location location;
    private final int food;
    private final float exp;
    private final double health;
    private final DungeonUserInventoryBackup inventory;

    public DungeonUserBackup(Player player) {
        this.uid = player.getUniqueId();
        this.location = player.getLocation();
        this.food = player.getFoodLevel();
        this.exp = player.getExp();
        this.health = player.getHealth();
        this.inventory = new DungeonUserInventoryBackup(player.getInventory());
    }

    public void load() {
        Player player = Bukkit.getPlayer(this.uid);
        Validate.notNull(player, "player cannot be null!");


        player.setExp(this.exp);
        player.setHealth(this.health);
        player.setFoodLevel(this.food);
        player.teleport(this.location);

        this.inventory.load(player.getInventory());
    }
}