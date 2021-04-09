package troggsboss.Mobs.Gatter.SnoozedGoon;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import troggsboss.Main;
import troggsboss.Utils;

import java.util.ArrayList;
import java.util.List;

public class SnoozedGoonEvents implements Listener {
    public final Main plugin;

    public SnoozedGoonEvents(Main plugin){
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if(e.getEntity().getCustomName().equalsIgnoreCase(Utils.chat("&5Resting Body"))){
            if(e.getDamager().getType().equals(EntityType.PLAYER)){
                e.getEntity().setCustomName(Utils.chat("&cWoke Body"));
                ((LivingEntity)e.getEntity()).setAI(true);
                e.setDamage(1);
            }else{
                if(e.getDamager() instanceof Arrow){
                    Arrow arrow = (Arrow) e.getDamager();
                    if(arrow.getShooter() instanceof Player) {
                        e.getEntity().setCustomName(Utils.chat("&cWoke Body"));
                        ((LivingEntity) e.getEntity()).setAI(true);
                        e.setDamage(1);
                    }
                }
                if(e.getDamager() instanceof Trident) {
                    Trident arrow = (Trident) e.getDamager();
                    if (arrow.getShooter() instanceof Player) {
                        e.getEntity().setCustomName(Utils.chat("&cWoke Body"));
                        ((LivingEntity) e.getEntity()).setAI(true);
                        e.setDamage(1);
                    }
                }
            }
        }
        if(e.getEntity().getCustomName().equalsIgnoreCase(Utils.chat("&cWoke Body"))){
            if(e.getDamager() instanceof Arrow){
                Arrow arrow = (Arrow) e.getDamager();
                if(!(arrow.getShooter() instanceof Player)) {
                    e.setCancelled(true);
                }
            }
            if(e.getDamager() instanceof Trident) {
                Trident arrow = (Trident) e.getDamager();
                if(!(arrow.getShooter() instanceof Player)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e){
        Entity entity = e.getEntity();
        if(entity.getCustomName().equalsIgnoreCase(Utils.chat("&cWoke Body")) || entity.getCustomName().equalsIgnoreCase(Utils.chat("&5Resting Body"))){
            if(Utils.getRandomInt(2) == 0){
                Bukkit.broadcastMessage("drop");
                ItemStack item = new ItemStack(Material.WITHER_ROSE);
                ItemMeta meta = item.getItemMeta();
                meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                meta.setDisplayName(Utils.chat("&8Soul of the Asleep"));
                List<String> lore = new ArrayList<>();
                lore.add(Utils.chat("&7The soul of the asleep"));
                lore.add(Utils.chat("&7in its pure flower form."));
                meta.setLore(lore);
                item.setItemMeta(meta);
                entity.getWorld().dropItem(entity.getLocation(), item);
            }
            e.getDrops().clear();
        }
        if(entity.getCustomName().equalsIgnoreCase(Utils.chat("&7Gatter Goon"))){
            e.getDrops().clear();
        }
    }
}
