package me.rin.devroom.command;

import com.hakan.core.command.executors.basecommand.BaseCommand;
import com.hakan.core.command.executors.subcommand.SubCommand;
import me.rin.devroom.user.DungeonUserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@BaseCommand(
        name = "stats",
        description = "Opens a gui with your dungeon stats.",
        usage = "/stats"
)
public class StatsCommand{
    @SubCommand(
            permission = "dungeon.stats"
    )
    public void mainCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        player.sendMessage(String.valueOf(DungeonUserManager.getByUid(player.getUniqueId()).getTotalKills()));
        player.sendMessage(String.valueOf(DungeonUserManager.getByUid(player.getUniqueId()).getTotalSessions()));
        player.sendMessage(String.valueOf(DungeonUserManager.getByUid(player.getUniqueId()).getActiveSession().getKills()));
    }
}
