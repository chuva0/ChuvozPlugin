package rainprojects.chuvoz.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rainprojects.chuvoz.entities.EPlayer;
import rainprojects.chuvoz.entities.Group;
import rainprojects.chuvoz.manager.PlayerManager;
import rainprojects.chuvoz.scoreboard.ScoreboardHandler;

import java.util.ArrayList;
import java.util.List;

public class GroupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        Player player = (Player) commandSender;
        EPlayer ePlayer = PlayerManager.getEPlayer(player);

        if (ePlayer.getGroup().getWeight() < Group.ADMINISTRADOR.getWeight()) {
            player.sendMessage("§cSem permissao!");
            return true;
        }
        if (args.length < 2) {
            player.sendMessage("§aUse /" + command.getName() + " (jogador) (grupo)");
            return true;
        }
        List<String> listGroups = new ArrayList<>();
        for (Group group : Group.values()) {
            listGroups.add(group.toString().toLowerCase());
        }
        if (args.length == 2) {
            String target = args[0];
            String group = args[1];

            if (Bukkit.getPlayer(target) == null) {
                player.sendMessage("§cJogador inserido esta offline ou nao existe!");
                return true;
            }
            if (!listGroups.contains(group)) {
                player.sendMessage("§cGrupo inserido nao existe!");
                return true;
            }
            Group targetGroup = Group.valueOf(group.toUpperCase());
            EPlayer eTarget = PlayerManager.getEPlayer(Bukkit.getPlayer(target));
            if (eTarget.getGroup().getWeight() >= ePlayer.getGroup().getWeight()) {
                player.sendMessage("§cVoce nao pode definir cargos de jogadores do mesmo grupo ou superior!");
                return true;
            }

            if (targetGroup.getWeight() >= ePlayer.getGroup().getWeight()) {
                player.sendMessage("§cVoce nao pode definir grupos que sao iguais ou maiores que o seu!");
                return true;
            }

            player.sendMessage("§aVoce inseriu o jogador " + target + " no grupo " + targetGroup.getColor() + targetGroup.getDisplayName());
            Bukkit.getPlayer(target).sendMessage("§aVoce foi inserido no grupo " + targetGroup.getColor() + targetGroup.getDisplayName());
            eTarget.setGroup(targetGroup);
            Bukkit.getOnlinePlayers().forEach(ScoreboardHandler::setScoreboard);
        }
        return false;
    }
}
