package troggsboss.Mobs.Gatter.GatterBoss;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
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
        if (!(e.getEntity() instanceof WitherSkeleton)) {
            return;
        }
        if (!e.getEntity().getCustomName().contains(Utils.chat("&8&lGatter"))) {
            return;
        }
        if (e.getDamager() instanceof Player || e.getDamager() instanceof Trident || e.getDamager() instanceof Arrow) {
            e.setCancelled(true);
            if(GatterHandler.gatterShield.size() > 0){
                Bukkit.broadcastMessage(Utils.chat("&8&lGatter&7: &cYOU CAN'T HURT ME! I'VE STILL GOT &4" + GatterHandler.gatterShield.size() + " SOULS &cPROTECTING ME!"));
                return;
            }
            if (e.getDamager() instanceof Trident) {
                Trident arrow = (Trident) e.getDamager();
                if (!(arrow.getShooter() instanceof Player)) {
                    return;
                }
            }
            if (e.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) e.getDamager();
                arrow.remove();
                if (!(arrow.getShooter() instanceof Player)) {
                    return;
                }
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
                GatterHandler.phase2();
            }
            if (GatterHandler.currentHealth <= 2000 && (GatterHandler.phase == 3 || GatterHandler.phase == 4)) {
                GatterHandler.currentHealth = 2000;
                GatterHandler.phase4();
            }
            if (GatterHandler.currentHealth <= 1000 && (GatterHandler.phase == 5 || GatterHandler.phase == 6)) {
                GatterHandler.currentHealth = 1000;
                GatterHandler.phase6();
            }
            if (GatterHandler.currentHealth <= 0) {
                GatterHandler.currentHealth = 0;
                GatterHandler.death();
            }
            String newName = Utils.chat("&8&lGatter &7[&f" + GatterHandler.currentHealth + "&7/&f" + GatterHandler.maxHealth + "&7]");
            e.getEntity().setCustomName(newName);
            e.setCancelled(true);
            ((LivingEntity) e.getEntity()).damage(1);
            ((LivingEntity) e.getEntity()).setHealth(((LivingEntity) e.getEntity()).getMaxHealth());
        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void shieldHit(EntityDamageByEntityEvent e){
        if(e.getEntity().getType().equals(EntityType.VEX)){
            if(e.getEntity().getCustomName().equalsIgnoreCase(Utils.chat("&0"))){
                if (e.getDamager() instanceof Player || e.getDamager() instanceof Trident || e.getDamager() instanceof Arrow) {
                    Player player = null;
                    if (e.getDamager() instanceof Trident) {
                        Trident arrow = (Trident) e.getDamager();
                        if (!(arrow.getShooter() instanceof Player)) {
                            return;
                        }
                        player = (Player) arrow.getShooter();
                    }
                    if (e.getDamager() instanceof Arrow) {
                        Arrow arrow = (Arrow) e.getDamager();
                        arrow.remove();
                        if (!(arrow.getShooter() instanceof Player)) {
                            return;
                        }
                        player = (Player) arrow.getShooter();
                    }
                    if (e.getDamager() instanceof Player){
                        player = (Player) e.getDamager();
                    }
                    GatterHandler.gatterShield.remove(e.getEntity());
                    e.getEntity().remove();

                }else{
                    e.setCancelled(true);
                }
            }
        }
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
