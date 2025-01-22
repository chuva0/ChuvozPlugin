package rainprojects.chuvoz.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import rainprojects.chuvoz.entities.EPlayer;
import rainprojects.chuvoz.entities.Group;
import rainprojects.chuvoz.manager.PlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreboardHandler implements Listener {

    private static Objective sidebar;
    private static int i;

    public static List<String> mainScore(EPlayer p) {
        List<String> list = new ArrayList<>();

        list.add("   ");
        list.add(" §7Cash:  ");
        list.add("   §e" + p.getCash() + "$  ");
        list.add(" §7Money:  ");
        list.add("   §a" + p.getMoney() + "$  ");
        list.add("  ");
        list.add(" §7Rank:  ");
        list.add("   §f" + p.getRank().getDisplayName() + "  ");
        list.add(" §7Grupo:  ");
        list.add("   §f" + p.getGroup().getDisplayName() + "  ");
        list.add("");

        return list;
    }
    public static List<String> mineracaoScore(EPlayer p) {
        List<String> list = new ArrayList<>();

        list.add("    §aMineracao...    ");
        list.add("   ");
        list.add(" §7Blocos:  ");
        list.add("   §e0");
        list.add("");

        return list;
    }
    public static void addScoreboard(Player player) {
        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        Group group = PlayerManager.getPlayerMap().get(player.getUniqueId()).getGroup();

        String teamName =
                group.getWeight() + player.getName();

        Team team = scoreboard.getTeam(teamName);
        if (team == null) {
            team = scoreboard.registerNewTeam(teamName);
        }
        assert team != null;
        String isWhite = group.isWhite()?"§f":"§7";
        team.setPrefix(group.getColor()+""+group.getDisplayGame() + " " + isWhite);
        team.getEntries().forEach(team::removeEntry);
        team.addEntry(player.getName());

        sidebar = scoreboard.getObjective("sidebar");
        if (sidebar == null) {
            sidebar = scoreboard.registerNewObjective("sidebar", "dummy");
            sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
            sidebar.setDisplayName("§f§lCHUVOZ");
        } else {
            for (String entry : scoreboard.getEntries()) {
                scoreboard.resetScores(entry);
            }
        }

        EPlayer p = PlayerManager.getPlayerMap().get(player.getUniqueId());

        updateSidebar(player, mainScore(PlayerManager.getPlayerMap().get(player.getUniqueId())));

        player.setScoreboard(scoreboard);
    }
    public static void updateSidebar(Player player, List<String> score) {
        sidebar = player.getScoreboard().getObjective("sidebar");
        sidebar.unregister();

        sidebar = player.getScoreboard().registerNewObjective("sidebar", "dummy");
        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        sidebar.setDisplayName("§f§lCHUVOZ");

        i = score.size();

        score.forEach(msg->{
            sidebar.getScore(msg).setScore(i);
            i--;
        });
    }

    @EventHandler
    void quit(PlayerQuitEvent event) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        String teamName = PlayerManager.getPlayerMap().get(event.getPlayer().getUniqueId()).getGroup().getWeight() + event.getPlayer().getName();

        Team team = scoreboard.getTeam(teamName);
        if (team != null) {
            team.unregister();
        }
    }
    @EventHandler
    void join(PlayerJoinEvent event) {
        addScoreboard(event.getPlayer());
    }
}
