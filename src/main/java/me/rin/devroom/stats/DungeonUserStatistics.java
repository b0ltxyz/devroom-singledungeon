package me.rin.devroom.stats;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "Statistics")
public class DungeonUserStatistics {

    @Id
    @Column(name = "uid", nullable = false, unique = true)
    private UUID uid;

    @Column(name = "kill_count", nullable = false)
    private int killCount;

    @Column(name = "session_count", nullable = false)
    private int sessionCount;

    @Column(name = "death_count", nullable = false)
    private int deathCount;

    protected DungeonUserStatistics() {

    }

    public DungeonUserStatistics(UUID uid) {
        this.uid = uid;
    }

    public UUID getUID() {
        return this.uid;
    }

    public int getKillCount() {
        return this.killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public int getSessionCount() {
        return this.sessionCount;
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
}