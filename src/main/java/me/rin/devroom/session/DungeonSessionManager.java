package me.rin.devroom.session;

import com.hakan.core.HCore;
import me.rin.devroom.user.DungeonUser;
import me.rin.devroom.user.DungeonUserManager;
import me.rin.devroom.util.LocationUtils;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static me.rin.devroom.SingleDungeonPlugin.CONFIG;
import static me.rin.devroom.SingleDungeonPlugin.REPOSITORY;

public class DungeonSessionManager {

    public static ItemStack[] kitArmor = {new ItemStack(Material.LEATHER_BOOTS, 1), new ItemStack(Material.LEATHER_LEGGINGS, 1), new ItemStack(Material.LEATHER_CHESTPLATE, 1), new ItemStack(Material.LEATHER_HELMET, 1)};
    public static List<Integer> zombieList = new ArrayList<>();

    public static void startSession(Player player, DungeonUser user) {
        Location loc = LocationUtils.deserialize(CONFIG.dungeonLocation);
        DungeonSession session = new DungeonSession(player);

        player.sendMessage("Dungeon will start in 5 seconds.");

        HCore.syncScheduler().after(5, TimeUnit.SECONDS).run(() -> {
            player.sendMessage("Dungeon is starting...");

            user.setActiveSession(session);

            player.getInventory().clear();
            player.teleport(loc);
            player.getInventory().setArmorContents(kitArmor);
            player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
            player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 5));

            zombieList.forEach(id -> HCore.sendPacket(player, new PacketPlayOutEntityDestroy(id)));

            CONFIG.mobSpawnLocations
                    .forEach(id -> {
                        Zombie z = (Zombie) LocationUtils.deserialize(id).getWorld()
                                .spawnEntity(LocationUtils.deserialize(id), EntityType.ZOMBIE);
                        z.setTarget(player);

                        zombieList.add(z.getEntityId());
                        session.getSessionZombieList().add(z);
                    });

            for (UUID uid : DungeonUserManager.getContent().keySet()) {
                Player dPlayer = Bukkit.getPlayer(uid);

                if (dPlayer != null && !dPlayer.equals(player) && DungeonUserManager.isUserInActiveSession(uid)) {
                    dPlayer.hidePlayer(player);
                    player.hidePlayer(dPlayer);

                    session.getSessionZombieList()
                            .forEach(zombie -> HCore.sendPacket(dPlayer, new PacketPlayOutEntityDestroy(zombie.getEntityId())));
                }
            }
        });
    }

    public static void endSession(Player player) {
        for (UUID uid : DungeonUserManager.getContent().keySet()) {
            Player dPlayer = Bukkit.getPlayer(uid);
            if (dPlayer != null && dPlayer != player && DungeonUserManager.isUserInActiveSession(uid)) {
                dPlayer.showPlayer(player);
                player.showPlayer(dPlayer);
            }
        }

        DungeonUser user = DungeonUserManager.getByUID(player.getUniqueId());
        DungeonSession session = user.getActiveSession();
        session.getUserBackup().load();
        session.getSessionZombieList()
                .forEach(Entity::remove);

        user.getStatistics().setSessionCount(user.getStatistics().getSessionCount() + 1);
        user.setActiveSession(null);

        REPOSITORY.save(user.getStatistics());

        player.sendMessage("Dungeon finished.");
    }
}
