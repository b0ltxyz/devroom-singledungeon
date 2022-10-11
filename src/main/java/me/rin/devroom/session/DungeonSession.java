package me.rin.devroom.session;


import me.rin.devroom.data.backup.DungeonUserBackup;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

import java.util.ArrayList;
import java.util.List;


public class DungeonSession {
    private Player player;
    private DungeonUserBackup userBackup;
    private List<Zombie> sessionZombieList = new ArrayList<>();


    public DungeonSession(Player player) {
        this.player = player;
        this.userBackup = new DungeonUserBackup(player);
    }







    public Player getPlayer() {
        return this.player;
    }


    public List<Zombie> getSessionZombieList() {
        return sessionZombieList;
    }

    public void setSessionZombieList(List<Zombie> sessionZombieList) {
        this.sessionZombieList = sessionZombieList;
    }

    public DungeonUserBackup getUserBackup() {
        return this.userBackup;
    }


}
