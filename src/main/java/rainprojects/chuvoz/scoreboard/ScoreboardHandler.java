package rainprojects.chuvoz.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;
import rainprojects.chuvoz.entities.EPlayer;
import rainprojects.chuvoz.manager.PlayerManager;

public class ScoreboardHandler implements Listener {

    public static void setScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        EPlayer ePlayer = PlayerManager.getEPlayer(player);

        if (manager == null) return;

        Scoreboard sb = manager.getNewScoreboard();
        Objective objective = sb.registerNewObjective("sidebar", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§f§l   CHUVOZ   ");

        objective.getScore(" ").setScore(9);
        objective.getScore(" §7Grupo:  ").setScore(8);
        objective.getScore("  " + ePlayer.getGroup().getColor() + ePlayer.getGroup().getDisplayName() + "  ").setScore(7);
        objective.getScore("  ").setScore(6);
        objective.getScore(" §7Rank:   ").setScore(5);
        objective.getScore("  " + ePlayer.getRank().getColor() + ePlayer.getRank().getDisplayName()).setScore(4);
        objective.getScore("   ").setScore(3);
        objective.getScore(" §7Money: §a$" + ePlayer.getMoney()).setScore(2);
        objective.getScore(" §7Cash: §e$" + ePlayer.getCash()).setScore(1);
        objective.getScore("").setScore(0);

        createPublicTeams(sb);
        player.setScoreboard(sb);

    }
    private static void createPublicTeams(Scoreboard sb) {
        Bukkit.getOnlinePlayers().forEach(player->{
            EPlayer ePlayer = PlayerManager.getEPlayer(player);
            String teamName = (99-ePlayer.getGroup().getWeight()) + player.getName();
            Team team;
            if (sb.getTeam(teamName) == null) {
                team = sb.registerNewTeam(teamName);
            } else {
                team = sb.getTeam(teamName);
            }
            team.setPrefix(ePlayer.getGroup().getColor()+ePlayer.getGroup().getDisplayGame() + " §f");
            team.addEntry(player.getName());
        });
    }

    @EventHandler
    void join(PlayerJoinEvent event) {
        Bukkit.getOnlinePlayers().forEach(ScoreboardHandler::setScoreboard);
    }
}
