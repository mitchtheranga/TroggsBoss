package troggsboss;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import troggsboss.Mobs.GatterGoons.GoonSkeleton;
import troggsboss.Mobs.GatterGoons.GoonStray;

public class SpawnMobEvents implements Listener {
    public final Main plugin;
    public SpawnMobEvents(Main plugin){
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void interactEvent(BlockPlaceEvent e){
        try{
            Location loc = e.getBlock().getLocation();
            WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
            if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("gatter_stray_goon")){
                e.setCancelled(true);
                GoonStray goon = new GoonStray(loc);
                world.addEntity(goon);
            }
            if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("gatter_skeleton_goon")){
                e.setCancelled(true);
                GoonSkeleton goon = new GoonSkeleton(loc);
                world.addEntity(goon);
            }
        } catch (Exception ex) {

        }
    }
}
