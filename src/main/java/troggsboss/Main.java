package troggsboss;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import troggsboss.Bosses.Gatter.GatterHandler;
import troggsboss.Bosses.SpawnBossCommand;
import troggsboss.Mobs.Gatter.GatterBoss.GatterEvents;
import troggsboss.Mobs.Gatter.RegularGoonEvents;
import troggsboss.Mobs.Gatter.SnoozedGoon.SnoozedGoonEvents;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {
        getCommand("troggsmob").setExecutor(new SpawnmobCommand());
        getCommand("boss").setExecutor(new SpawnBossCommand());
        new SnoozedGoonEvents(this);
        new SpawnMobEvents(this);
        new GatterEvents(this);
        new RegularGoonEvents(this);
        plugin = this;
        GatterHandler.witherBlocks = getWitherableBlocks();
        GatterHandler.noAttackPhases.add(2);
        GatterHandler.noAttackPhases.add(4);
        GatterHandler.noAttackPhases.add(6);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public List<Block> getWitherableBlocks(){
        List<Block> blist = new ArrayList<>();
        List<Integer> yvalues = new ArrayList<>();
        yvalues.add(95);
        yvalues.add(96);
        yvalues.add(98);
        yvalues.add(99);
        yvalues.add(105);
        yvalues.add(104);
        Location middleLoc = new Location(Bukkit.getWorld("arena"), 0.5, 100, 0.5);
        for(Block block : Utils.getNearbyBlocks(middleLoc, 50)){
            if((block.getType().equals(Material.CRIMSON_NYLIUM) || block.getType().equals(Material.BLACKSTONE)) && yvalues.contains(block.getY())){
                blist.add(block);
            }
        }
        return blist;
    }
}
