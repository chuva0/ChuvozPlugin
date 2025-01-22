package rainprojects.chuvoz.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rainprojects.chuvoz.manager.PlayerManager;
import rainprojects.chuvoz.scoreboard.ScoreboardHandler;

public class Mina implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length ==0) {
                player.sendMessage("§aArea de mineracao!");
                ScoreboardHandler.updateSidebar
                        (player, ScoreboardHandler.mineracaoScore(
                                PlayerManager.getPlayerMap().get(player.getUniqueId())));
            }
        }
        return false;
    }
}
