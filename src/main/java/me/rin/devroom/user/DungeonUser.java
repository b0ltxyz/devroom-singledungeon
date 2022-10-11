package me.rin.devroom.user;

import me.rin.devroom.session.DungeonSession;
import me.rin.devroom.stats.DungeonUserStatistics;

import java.util.UUID;

import static me.rin.devroom.SingleDungeonPlugin.REPOSITORY;

public class DungeonUser {

    private final UUID uid;
    private DungeonSession activeSession;
    private DungeonUserStatistics statistics;

    public DungeonUser(UUID uid) {
        this.uid = uid;
        this.statistics = REPOSITORY.findByUID(uid)
                .orElseGet(() -> new DungeonUserStatistics(uid));
    }

    public UUID getUID() {
        return this.uid;
    }

    public DungeonSession getActiveSession() {
        return this.activeSession;
    }

    public DungeonUserStatistics getStatistics() {
        return this.statistics;
    }

    public void setActiveSession(DungeonSession activeSession) {
        this.activeSession = activeSession;
    }

    public void setStatistics(DungeonUserStatistics statistics) {
        this.statistics = statistics;
    }
}