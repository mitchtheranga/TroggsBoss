package troggsboss.Mobs.Gatter.GatterBoss;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import troggsboss.Bosses.Gatter.GatterHandler;
import troggsboss.Main;
import troggsboss.Utils;

import java.util.List;

public class GatterEvents implements Listener {

    public final Main plugin;

    public GatterEvents(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        if (!(e.getEntity() instanceof WitherSkeleton)) {
            return;
        }
        if (!e.getEntity().getCustomName().contains(Utils.chat("&8&lGatter"))) {
            return;
        }
        String nameYF = e.getEntity().getCustomName();
        String nameNF = ChatColor.stripColor(e.getEntity().getCustomName());
        String nameFF = nameNF.replace("[", "");
        nameFF = nameFF.replace("]", "");
        nameFF = nameFF.replace("Gatter", "");
        nameFF = nameFF.replace(" ", "");
        GatterHandler.currentHealth = Integer.parseInt(nameFF.split("/")[0]);
        GatterHandler.maxHealth = Integer.parseInt(nameFF.split("/")[1]);
        if (e.getEntity().isInvulnerable()) {
            e.setCancelled(true);
            return;
        }
        GatterHandler.currentHealth -= e.getDamage();
        if (GatterHandler.currentHealth <= 3000 && (GatterHandler.phase == 1 || GatterHandler.phase == 2)) {
            GatterHandler.currentHealth = 3000;
        }
        if (GatterHandler.currentHealth <= 2000 && (GatterHandler.phase == 3 || GatterHandler.phase == 4)) {
            GatterHandler.currentHealth = 2000;
        }
        if (GatterHandler.currentHealth <= 1000 && (GatterHandler.phase == 5 || GatterHandler.phase == 6)) {
            GatterHandler.currentHealth = 1000;
        }
        String newName = Utils.chat("&8&lGatter &7[&f" + GatterHandler.currentHealth + "&7/&f" + GatterHandler.maxHealth + "&7]");
        e.getEntity().setCustomName(newName);
        e.setCancelled(true);
        GatterHandler.gatterHit();
        ((LivingEntity) e.getEntity()).damage(1);
        ((LivingEntity) e.getEntity()).setHealth(((LivingEntity) e.getEntity()).getMaxHealth());
    }

    @EventHandler
    public void death(EntityDeathEvent e) {
        if (!(e.getEntity() instanceof WitherSkeleton)) {
            return;
        }
        if (e.getEntity().getCustomName().contains(Utils.chat("&8&lGatter"))) {
            GatterHandler.gatter = null;
        }
    }
}
