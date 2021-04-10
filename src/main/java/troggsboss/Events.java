package troggsboss;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events implements Listener {
    public final Main plugin;

    public Events(Main plugin){
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void itemDrop(EntityDeathEvent e){
        if(e.getEntity().getCustomName().contains(Utils.chat("&7Soul Groper"))){
            e.getDrops().clear();
            if(Utils.getRandomInt(4) == 0){
                ItemStack item = new ItemStack(Material.WITHER_ROSE, 1);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(Utils.chat("&8Gropers Rose"));
                item.setItemMeta(meta);
                e.getDrops().add(item);
            }
        }
    }

    @EventHandler
    public void arrowLand(ProjectileHitEvent e){
        e.getEntity().remove();
    }
}
