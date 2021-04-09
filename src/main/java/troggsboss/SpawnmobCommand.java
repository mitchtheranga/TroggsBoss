package troggsboss;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import troggsboss.Bosses.Gatter.GatterHandler;
import troggsboss.Mobs.Gatter.GatterBoss.GatterBoss;
import troggsboss.Mobs.Gatter.GoonSkeleton;
import troggsboss.Mobs.Gatter.GoonStray;
import troggsboss.Mobs.Gatter.SnoozedGoon.SnoozedGoon;

public class SpawnmobCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("troggsmob")){
            if(!(sender instanceof Player)){
                sender.sendMessage("You must be a player to run this command!");
                return true;
            }
            if(args.length <= 0){
                sender.sendMessage(Utils.chat("Incorrect Usage! Do /troggsmob <Mob>"));
                return true;
            }
            Player player = (Player) sender;
            WorldServer world = ((CraftWorld)player.getWorld()).getHandle();
            if(args.length == 2){
                ItemStack item = new ItemStack(Material.ACACIA_LOG);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(args[0]);
                item.setItemMeta(meta);
                player.getInventory().addItem(item);
            }
            if(args[0].equalsIgnoreCase("gatter_skeleton_goon")){
                Bukkit.broadcastMessage("Spawning Goon!");
                GoonSkeleton goonSkeleton = new GoonSkeleton(player.getLocation());
                try {
                    world.addEntity(goonSkeleton);
                } catch (Exception e) {
                    Bukkit.broadcastMessage("error spawning goon.");
                }
            }
            if(args[0].equalsIgnoreCase("gatter_stray_goon")){
                Bukkit.broadcastMessage("Spawning Goon!");
                GoonStray goonStray = new GoonStray(player.getLocation());
                try {
                    world.addEntity(goonStray);
                } catch (Exception e) {
                    Bukkit.broadcastMessage("error spawning goon.");
                }
            }
            if(args[0].equalsIgnoreCase("gatter_snoozed_goon")){
                Bukkit.broadcastMessage("Spawning Goon!");
                SnoozedGoon snoozedGoon = new SnoozedGoon(player.getLocation());
                try {
                    world.addEntity(snoozedGoon);
                } catch (Exception e) {
                    Bukkit.broadcastMessage("error spawning goon.");
                }
            }
            if(args[0].equalsIgnoreCase("gatter_boss")){
                if(GatterHandler.gatter == null){
                    Bukkit.broadcastMessage("You can not have more than gatter at a time!");
                    return true;
                }
                Bukkit.broadcastMessage("Spawning Gatter!");
                GatterBoss gatterBoss = new GatterBoss(player.getLocation());
                try {
                    world.addEntity(gatterBoss);
                } catch (Exception e) {
                    Bukkit.broadcastMessage("error spawning Gatter.");
                }
                GatterHandler.newGatter(gatterBoss);
            }
        }
        return false;
    }
}
