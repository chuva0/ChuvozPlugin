package rainprojects.chuvoz;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import rainprojects.chuvoz.commands.Mina;
import rainprojects.chuvoz.entities.EPlayer;
import rainprojects.chuvoz.events.General;
import rainprojects.chuvoz.manager.PlayerManager;
import rainprojects.chuvoz.scoreboard.ScoreboardHandler;

public final class Chuvoz extends JavaPlugin {

    private static Chuvoz instance;

    @Override
    public void onEnable() {
        instance = this;
        ConfigurationSerialization.registerClass(EPlayer.class);

        PlayerManager.loadData();

        getServer().getPluginManager().registerEvents(new PlayerManager(), this);
        getServer().getPluginManager().registerEvents(new General(), this);
        getServer().getPluginManager().registerEvents(new ScoreboardHandler(), this);

        getCommand("mina").setExecutor(new Mina());

        saveDataTimed();
    }

    @Override
    public void onDisable() {
        PlayerManager.saveData();
    }

    public static Chuvoz getInstance() {
        return instance;
    }

    private void saveDataTimed() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, ()->{
            Bukkit.broadcastMessage("§f§lCHUVOZ  §7Salvado dados...");
            PlayerManager.saveData();
            Bukkit.broadcastMessage("§f§lCHUVOZ  §aDados salvos!");
        }, 20L*10, 20L*60*10);
    }
}
