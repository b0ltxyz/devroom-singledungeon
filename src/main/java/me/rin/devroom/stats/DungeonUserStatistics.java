package me.rin.devroom.stats;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "Statistics")
public class DungeonUserStatistics {
    @Id
    @Column(name = "uid", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "kill_count", nullable = false)
    private int killCount;

    @Column(name = "session_count", nullable = false)
    private int sessionCount;

    @Column(name = "death_count", nullable = false)
    private int deathCount;


    public DungeonUserStatistics() {
    }

    public DungeonUserStatistics(UUID uuid) {
        this.uuid = uuid;
    }

    public int getSessionCount() {
        return sessionCount;
    }

    public void setSessionCount(int sessionCount) {
        this.sessionCount = sessionCount;
    }

    public int getDeathCount() {
        return this.deathCount;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public int getKillCount() {
        return this.killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
