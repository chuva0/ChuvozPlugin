package rainprojects.chuvoz.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import rainprojects.chuvoz.entities.EPlayer;
import rainprojects.chuvoz.manager.PlayerManager;
import rainprojects.chuvoz.scoreboard.ScoreboardHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class General implements Listener {

    @EventHandler
    void join(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        //DEBUG
        event.getPlayer().sendMessage(
                PlayerManager.getPlayerMap().get(event.getPlayer().getUniqueId()).getCash() + " " +
                        PlayerManager.getPlayerMap().get(event.getPlayer().getUniqueId()).getMoney() + " " +
                        PlayerManager.getPlayerMap().get(event.getPlayer().getUniqueId()).getRank().toString() + " " +
                        PlayerManager.getPlayerMap().get(event.getPlayer().getUniqueId()).getGroup().toString() + " "
        );
    }
    @EventHandler
    void quit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }
    @EventHandler
    void chat(AsyncPlayerChatEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        EPlayer ePlayer = PlayerManager.getPlayerMap().get(uuid);

        String newLine = ePlayer.getGroup().newLine()?" \n ":"";
        String rankPrefix = ePlayer.getRank().getPrefix()+" ";
        String groupPrefix = ePlayer.getGroup().getDisplayGame()+" ";
        String isWhite = ePlayer.getGroup().isWhite()?"§f":"§7";
        ChatColor color = ePlayer.getGroup().getColor();
        String msgColor = ePlayer.getGroup().isWhite()?event.getMessage().replace("&","§"):event.getMessage();

        event.setFormat(newLine+isWhite + rankPrefix + color + groupPrefix + isWhite + event.getPlayer().getName() + ": " + msgColor+newLine);

    }
}
