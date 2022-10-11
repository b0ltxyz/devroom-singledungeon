package me.rin.devroom.session;

import me.rin.devroom.backup.DungeonUserBackup;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

import java.util.ArrayList;
import java.util.List;

public class DungeonSession {

    private final Player player;
    private final DungeonUserBackup userBackup;
    private final List<Zombie> sessionZombieList;

    public DungeonSession(Player player) {
        this.player = player;
        this.userBackup = new DungeonUserBackup(player);
        this.sessionZombieList = new ArrayList<>();
    }

    public Player getPlayer() {
        return this.player;
    }

    public DungeonUserBackup getUserBackup() {
        return this.userBackup;
    }

    public List<Zombie> getSessionZombieList() {
        return this.sessionZombieList;
    }
}