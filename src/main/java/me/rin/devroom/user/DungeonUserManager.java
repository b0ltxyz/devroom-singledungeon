package me.rin.devroom.user;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DungeonUserManager {

    private static final Map<UUID, DungeonUser> dungeonUsers = new HashMap<>();

    public static Map<UUID, DungeonUser> getContent() {
        return dungeonUsers;
    }

    public static boolean isUserInActiveSession(UUID uuid) {
        return getByUID(uuid).getActiveSession() != null;
    }

    public static DungeonUser getByUID(UUID uid) {
        return dungeonUsers.get(uid);
    }

    public static DungeonUser create(Player player) {
        return DungeonUserManager.create(player.getUniqueId());
    }

    public static DungeonUser create(UUID uid) {
        DungeonUser user = new DungeonUser(uid);
        DungeonUserManager.dungeonUsers.put(uid, user);

        return user;
    }
}