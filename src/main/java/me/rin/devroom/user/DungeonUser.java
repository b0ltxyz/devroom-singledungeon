package me.rin.devroom.user;

import me.rin.devroom.SingleDungeonPlugin;
import me.rin.devroom.data.backup.DungeonUserBackup;
import me.rin.devroom.session.DungeonSession;
import me.rin.devroom.stats.DungeonUserStatistics;
import org.bukkit.entity.Player;


import java.util.UUID;

public class DungeonUser {

    private UUID uid;
    private DungeonSession activeSession;
    private DungeonUserStatistics statistics;


    public DungeonUser(UUID uid) {
        this.uid = uid;
        DungeonUserManager.dungeonUsers.put(uid, this);
        this.statistics = SingleDungeonPlugin.getInstance().getDungeonRepository().findByUID(uid).orElseGet(() -> new DungeonUserStatistics(uid));
    }


    public DungeonSession getActiveSession() {
        return this.activeSession;
    }

    public void setActiveSession(DungeonSession activeSession) {
        this.activeSession = activeSession;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public DungeonUserStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(DungeonUserStatistics statistics) {
        this.statistics = statistics;
    }
}
