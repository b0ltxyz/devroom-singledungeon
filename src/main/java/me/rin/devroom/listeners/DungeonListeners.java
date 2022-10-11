package me.rin.devroom.listeners;

import com.hakan.core.HCore;
import me.rin.devroom.SingleDungeonPlugin;
import me.rin.devroom.session.DungeonSessionManager;
import me.rin.devroom.stats.DungeonUserStatistics;
import me.rin.devroom.user.DungeonUser;
import me.rin.devroom.user.DungeonUserManager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DungeonListeners {


    public static void initialize(SingleDungeonPlugin plugin) {
        HCore.registerEvent(EntityDeathEvent.class)
                .filter(event -> event.getEntity().getKiller() != null && DungeonUserManager.isUserInActiveSession(event.getEntity().getKiller().getUniqueId()) && DungeonSessionManager.zombieList.contains(event.getEntity().getEntityId()))
                .consume(event -> {
                        DungeonUser dUser =  DungeonUserManager.getByUid(event.getEntity().getKiller().getUniqueId());
                        dUser.getStatistics().setKillCount(dUser.getStatistics().getKillCount()+1);
                });


        HCore.registerEvent(PlayerQuitEvent.class)
                .consume(event -> {

                    if (DungeonUserManager.isUserInActiveSession(event.getPlayer().getUniqueId()))
                        DungeonSessionManager.endSession(event.getPlayer());

                });
        HCore.registerEvent(PlayerDeathEvent.class)
                .filter(event -> DungeonUserManager.isUserInActiveSession(event.getEntity().getUniqueId()))
                .consume(event -> {
                    event.getDrops().clear();
                });


        HCore.registerEvent(PlayerRespawnEvent.class)
                .filter(event -> DungeonUserManager.isUserInActiveSession(event.getPlayer().getUniqueId()))
                .consume(event -> {
                    DungeonUser dUser =  DungeonUserManager.getByUid(event.getPlayer().getUniqueId());
                    dUser.getStatistics().setDeathCount(dUser.getStatistics().getDeathCount()+1);
                    DungeonSessionManager.endSession(event.getPlayer());
                });


        HCore.registerEvent(EntityTargetLivingEntityEvent.class)
                .filter(event -> event.getTarget() instanceof Player && event.getEntity() instanceof Zombie && DungeonUserManager.isUserInActiveSession(event.getTarget().getUniqueId()) && !DungeonUserManager.getByUid(event.getTarget().getUniqueId()).getActiveSession().getSessionZombieList().contains(event.getEntity()))
                .consume(event -> {
                    event.setCancelled(true);
                });



        HCore.registerEvent(PlayerJoinEvent.class)
                .consume(event -> {
                    DungeonUser dUser = new DungeonUser(event.getPlayer().getUniqueId());
                });

    }
}
