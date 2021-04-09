package troggsboss;

import org.bukkit.plugin.java.JavaPlugin;
import troggsboss.Bosses.SpawnBossCommand;
import troggsboss.Mobs.Gatter.GatterBoss.GatterEvents;
import troggsboss.Mobs.Gatter.SnoozedGoon.SnoozedGoonEvents;

public final class Main extends JavaPlugin {

    public Main plugin = this;

    @Override
    public void onEnable() {
        getCommand("troggsmob").setExecutor(new SpawnmobCommand());
        getCommand("boss").setExecutor(new SpawnBossCommand());
        new SnoozedGoonEvents(this);
        new SpawnMobEvents(this);
        new GatterEvents(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
