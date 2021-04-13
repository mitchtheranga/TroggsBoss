package troggsboss.Mobs.Gatter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import troggsboss.Bosses.Gatter.GatterHandler;
import troggsboss.Main;
import troggsboss.Utils;

public class RegularGoonEvents implements Listener {
    public final Main plugin;

    public RegularGoonEvents(Main plugin){
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e){
        try {
            if (e.getEntity().getCustomName().equalsIgnoreCase(Utils.chat("&7Gatter Goon"))) {
                if (GatterHandler.phase == 2) {
                    GatterHandler.goonsLeftF2 -= 1;
                    GatterHandler.goonKilled();
                }
                if (GatterHandler.phase == 4) {
                    GatterHandler.goonsLeftF4 -= 1;
                    GatterHandler.goonKilled();
                }
                if (GatterHandler.phase == 6) {
                    GatterHandler.goonsLeftF6 -= 1;
                    GatterHandler.goonKilled();
                }
            }
        } catch (Exception exception) {
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        try {
            if (e.getEntity().getCustomName().equalsIgnoreCase(Utils.chat("&7Gatter Goon"))) {
                if (e.getDamager() instanceof Arrow) {
                    Arrow arrow = (Arrow) e.getDamager();
                    if (!(arrow.getShooter() instanceof Player)) {
                        e.setCancelled(true);
                    }
                }
                if (e.getDamager() instanceof Trident) {
                    Trident arrow = (Trident) e.getDamager();
                    if (!(arrow.getShooter() instanceof Player)) {
                        e.setCancelled(true);
                    }
                }
            }
        } catch (Exception exception) {
        }
    }

    @EventHandler
    public void mobDespawn(ItemDespawnEvent e){

    }
}
