package me.rin.devroom.user;

import me.rin.devroom.session.DungeonSession;

import java.util.UUID;

public class DungeonUser {
    private UUID uid;
    private Integer totalKills = 0;
    private Integer totalSessions = 0;
    private DungeonSession activeSession;

    public DungeonUser(UUID uid) {
        this.uid = uid;
    }

    public DungeonSession getActiveSession() {
        return this.activeSession;
    }

    public void setActiveSession(DungeonSession activeSession) {
        this.activeSession = activeSession;
    }

    public Integer getTotalSessions() {
        return this.totalSessions;
    }

    public void setTotalSessions(Integer totalSessions) {
        this.totalSessions = totalSessions;
    }

    public Integer getTotalKills() {
        return this.totalKills;
    }

    public void setTotalKills(Integer totalKills) {
        this.totalKills = totalKills;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }
}
