package me.rin.devroom;

import com.hakan.core.HCore;
import com.hakan.core.plugin.Plugin;
import me.rin.devroom.command.StartCommand;
import me.rin.devroom.command.StatsCommand;
import me.rin.devroom.command.LeaveCommand;
import me.rin.devroom.config.DungeonConfiguration;
import me.rin.devroom.data.repository.DungeonRepository;
import me.rin.devroom.listeners.DungeonListeners;
import org.bukkit.plugin.java.JavaPlugin;

@Plugin(
        name = "SingleDungeon",
        authors = "RIN",
        version = "0.0.1",
        apiVersion = "1.18"
)


public class SingleDungeonPlugin extends JavaPlugin {
    private static SingleDungeonPlugin instance;
    private DungeonRepository dungeonRepository;
    public static DungeonConfiguration config;
    public static SingleDungeonPlugin getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        instance = this;
        HCore.initialize(this);
        config = HCore.loadConfig(new DungeonConfiguration());
        HCore.registerCommands(new StartCommand());
        HCore.registerCommands(new LeaveCommand());
        HCore.registerCommands(new StatsCommand());
        DungeonListeners.initialize(this);
        this.dungeonRepository = new DungeonRepository();

    }

    public DungeonRepository getDungeonRepository() {
        return this.dungeonRepository;
    }
}