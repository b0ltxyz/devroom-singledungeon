package me.rin.devroom.command;

import com.hakan.core.command.executors.basecommand.BaseCommand;
import com.hakan.core.command.executors.subcommand.SubCommand;
import me.rin.devroom.gui.StatisticsGUI;
import me.rin.devroom.stats.DungeonUserStatistics;
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
       DungeonUserStatistics statistics = DungeonUserManager.getByUid(player.getUniqueId()).getStatistics();
       player.sendMessage(String.valueOf(statistics.getSessionCount()) + " session " + String.valueOf(statistics.getDeathCount()) + " death " + String.valueOf(statistics.getKillCount()) + " kill " + String.valueOf((float) statistics.getKillCount()/ (float) statistics.getSessionCount()) + " avg kill per session");
       new StatisticsGUI(player, statistics).open();




    }








}
