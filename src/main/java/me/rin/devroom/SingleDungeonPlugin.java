package me.rin.devroom;

import com.hakan.core.HCore;
import com.hakan.core.plugin.Plugin;
import me.rin.devroom.command.LeaveCommand;
import me.rin.devroom.command.StartCommand;
import me.rin.devroom.command.StatsCommand;
import me.rin.devroom.config.DungeonConfiguration;
import me.rin.devroom.listeners.DungeonListeners;
import me.rin.devroom.repository.DungeonRepository;
import org.bukkit.plugin.java.JavaPlugin;

@Plugin(
        name = "SingleDungeon",
        authors = "RIN",
        version = "0.0.1",
        apiVersion = "1.18"
)
public class SingleDungeonPlugin extends JavaPlugin {

    public static DungeonConfiguration CONFIG;
    public static DungeonRepository REPOSITORY;

    @Override
    public void onEnable() {
        HCore.initialize(this);

        HCore.registerCommands(
                new StartCommand(),
                new LeaveCommand(),
                new StatsCommand()
        );
        DungeonListeners.initialize();

        REPOSITORY = new DungeonRepository();
        CONFIG = HCore.loadConfig(new DungeonConfiguration());
    }
}