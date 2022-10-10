package me.rin.devroom.session;

import com.hakan.core.HCore;
import me.rin.devroom.SingleDungeonPlugin;
import me.rin.devroom.config.DungeonConfiguration;
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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class DungeonSessionManager {
    public static ItemStack[] kitArmor = {new ItemStack(Material.LEATHER_BOOTS, 1), new ItemStack(Material.LEATHER_LEGGINGS, 1), new ItemStack(Material.LEATHER_CHESTPLATE, 1), new ItemStack(Material.LEATHER_HELMET, 1)};
    public static List<Integer> zombieList = new ArrayList<>();
    public static void startSession(Player player) {
        Location loc = LocationUtils.deserialize(SingleDungeonPlugin.config.dungeonLocation);
        DungeonSession session = new DungeonSession(player);
        session.setLocation(player.getLocation());
        session.backupInventory();
        DungeonUser user = new DungeonUser(player.getUniqueId());
        user.setActiveSession(session);
        player.getInventory().clear();
        player.teleport(loc);
        player.sendMessage("Dungeon will start in 5 seconds.");
        player.getInventory().setArmorContents(kitArmor);
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 5));

        zombieList
            .forEach(id -> HCore.sendPacket(player, new PacketPlayOutEntityDestroy(id)));

        for (int i = 0; i < 5; i++) {
            Zombie z = (Zombie)loc.getWorld().spawnEntity(loc.add(i, 5, 5), EntityType.ZOMBIE);
            z.setTarget(player);
            zombieList.add(z.getEntityId());
            session.getSessionZombieList().add(z);
        }
        for (UUID uid : DungeonUserManager.dungeonUsers.keySet()) {
            Player dPlayer = Bukkit.getPlayer(uid);
            dPlayer.hidePlayer(SingleDungeonPlugin.getInstance(), player);
            player.hidePlayer(SingleDungeonPlugin.getInstance(), dPlayer);
            HCore.sendPacket(player, new PacketPlayOutEntityDestroy(dPlayer.getEntityId()));
            HCore.sendPacket(dPlayer, new PacketPlayOutEntityDestroy(player.getEntityId()));
            session.getSessionZombieList()
                    .forEach(zomb -> HCore.sendPacket(dPlayer, new PacketPlayOutEntityDestroy(zomb.getEntityId())));

        }
        DungeonUserManager.dungeonUsers.put(player.getUniqueId(), user);



    }

    public static void endSession(Player player) {
        for (UUID uid : DungeonUserManager.dungeonUsers.keySet()) {
            Player dPlayer = Bukkit.getPlayer(uid);
            if (dPlayer != player) {
                dPlayer.showPlayer(SingleDungeonPlugin.getInstance(), player);
                player.showPlayer(SingleDungeonPlugin.getInstance(), dPlayer);
            }
        }
        DungeonUser user = DungeonUserManager.getByUid(player.getUniqueId());
        DungeonSession session = user.getActiveSession();
        DungeonUserManager.dungeonUsers.remove(user.getUid());
        session.loadInventory();
        player.teleport(session.getLocation());
        session.getSessionZombieList()
                .forEach(Entity::remove);
        player.sendMessage("Dungeon finished.");
        user.setTotalSessions(user.getTotalSessions()+1);
        user.setTotalKills(user.getTotalKills()+session.getKills());
        user.setActiveSession(null);
    }
}
