package rainprojects.chuvoz.manager;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import rainprojects.chuvoz.Chuvoz;
import rainprojects.chuvoz.entities.EPlayer;
import rainprojects.chuvoz.entities.Group;
import rainprojects.chuvoz.entities.Ranks;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager implements Listener {

    private static Map<UUID, EPlayer> playerMap = new HashMap<>();

    private static boolean exists(UUID uuid) {
        return playerMap.containsKey(uuid);
    }
    private static void createPlayer(UUID uuid) {
        if (exists(uuid)) return;

        EPlayer ePlayer = new EPlayer(0.0, 0.0, Ranks.UNRANKED, Group.MEMBER, uuid);
        playerMap.put(uuid, ePlayer);

        saveData();
    }

    public static EPlayer loadPlayerObject(String uuidString) {
        Chuvoz instance = Chuvoz.getInstance();
        if (instance.getConfig().contains("player." + uuidString)) {
            Map<String, Object> data = instance.getConfig().getConfigurationSection("player." + uuidString).getValues(false);
            return EPlayer.deserialize(data);
        }
        return null;
    }
    public static void loadData() {
        Chuvoz instance = Chuvoz.getInstance();

        if (instance.getConfig().contains("player")) {
            instance.getConfig().getConfigurationSection("player").getKeys(false).forEach(uuid -> {
                EPlayer ePlayer = loadPlayerObject(uuid);
                if (ePlayer != null) {
                    playerMap.put(UUID.fromString(uuid), ePlayer);
                    Bukkit.getConsoleSender().sendMessage("§a" + uuid + " carregado!");
                }
            });
        }
    }
    public static void saveData() {
        Chuvoz instance = Chuvoz.getInstance();

        playerMap.forEach((k,v)->{
            instance.getConfig().set("player." + k.toString(), v.serialize());
            Bukkit.getConsoleSender().sendMessage("§a" + k.toString() + " salvo!");
        });
        instance.saveConfig();
    }

    public static Map<UUID, EPlayer> getPlayerMap() {
        return playerMap;
    }

    @EventHandler
    void join(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        createPlayer(uuid);
    }

}
