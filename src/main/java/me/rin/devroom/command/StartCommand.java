package me.rin.devroom.command;

import com.hakan.core.command.executors.basecommand.BaseCommand;
import com.hakan.core.command.executors.subcommand.SubCommand;
import me.rin.devroom.session.DungeonSessionManager;
import me.rin.devroom.user.DungeonUserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@BaseCommand(
        name = "start",
        description = "Teleports you to a dungeon",
        usage = "/start"
)
public class StartCommand {

    @SubCommand(
            permission = "dungeon.start"
    )
    public void mainCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (!DungeonUserManager.isUserInActiveSession(player.getUniqueId())) {
            DungeonSessionManager.startSession(player, DungeonUserManager.getByUID(player.getUniqueId()));
        }
    }
}