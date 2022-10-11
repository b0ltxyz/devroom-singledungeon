package me.rin.devroom.user;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class DungeonUserManager {
    public static HashMap<UUID, DungeonUser> dungeonUsers = new HashMap<>();

    public static boolean isUserInActiveSession(UUID uuid) {
        return  dungeonUsers.get(uuid).getActiveSession() != null;
    }
    public static DungeonUser getByUid(UUID uid) {
        return dungeonUsers.get(uid);
    }


}
