package me.rin.devroom.command;

import com.hakan.core.command.executors.basecommand.BaseCommand;
import com.hakan.core.command.executors.subcommand.SubCommand;
import me.rin.devroom.session.DungeonSessionManager;
import me.rin.devroom.user.DungeonUserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@BaseCommand(
        name = "leave",
        description = "Finishes the current dungeon.",
        usage = "/leave"
)
public class LeaveCommand {

    @SubCommand(
            permission = "dungeon.leave"
    )
    public void mainCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (DungeonUserManager.isUserInActiveSession(player.getUniqueId())) {
            DungeonSessionManager.endSession(player);
        }
    }
}