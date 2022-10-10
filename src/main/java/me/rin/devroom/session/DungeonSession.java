package me.rin.devroom.session;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;


public class DungeonSession {
    private Player player;
    private Integer kills = 0;
    private Location location;
    private ItemStack[] inventoryContents;
    private ItemStack[] armorContents;
    private List<Zombie> sessionZombieList = new ArrayList<>();


    public DungeonSession(Player player) {
        this.player = player;
    }

    public Integer getKills() {
        return kills;
    }

    public void addKill() {
        this.kills = this.kills+1;
    }



    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Player getPlayer() {
        return this.player;
    }


    public void backupInventory() {
        this.inventoryContents = this.player.getInventory().getContents();
        this.armorContents = this.player.getInventory().getArmorContents();
    }
    public void loadInventory() {
        this.player.getInventory().clear();
        this.player.getInventory().setContents(inventoryContents);
        this.player.getInventory().setArmorContents(armorContents);

    }

    public List<Zombie> getSessionZombieList() {
        return sessionZombieList;
    }

    public void setSessionZombieList(List<Zombie> sessionZombieList) {
        this.sessionZombieList = sessionZombieList;
    }
}
