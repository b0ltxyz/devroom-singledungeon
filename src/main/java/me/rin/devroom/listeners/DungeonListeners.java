package me.rin.devroom.listeners;

import com.hakan.core.HCore;
import me.rin.devroom.SingleDungeonPlugin;
import me.rin.devroom.session.DungeonSessionManager;
import me.rin.devroom.user.DungeonUserManager;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DungeonListeners {


    public static void initialize(SingleDungeonPlugin plugin) {
        HCore.registerEvent(EntityDeathEvent.class)
                .filter(event -> event.getEntity().getKiller() != null && DungeonUserManager.isUserInActiveSession(event.getEntity().getKiller().getUniqueId()) && DungeonSessionManager.zombieList.contains(event.getEntity().getEntityId()))
                .consume(event -> {
                        DungeonUserManager.getByUid(event.getEntity().getKiller().getUniqueId()).getActiveSession().addKill();
                        DungeonSessionManager.zombieList.remove(event.getEntity().getEntityId());
                });


        HCore.registerEvent(PlayerQuitEvent.class)
                .filter(event -> DungeonUserManager.isUserInActiveSession(event.getPlayer().getUniqueId()))
                .consume(event -> {
                    DungeonSessionManager.endSession(event.getPlayer());
                });


        HCore.registerEvent(PlayerRespawnEvent.class)
                .filter(event -> DungeonUserManager.isUserInActiveSession(event.getPlayer().getUniqueId()))
                .consume(event -> {
                    DungeonSessionManager.endSession(event.getPlayer());
                });
    }
}
