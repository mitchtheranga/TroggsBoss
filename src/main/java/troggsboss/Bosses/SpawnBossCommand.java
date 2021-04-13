package troggsboss.Bosses;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import troggsboss.Bosses.Gatter.GatterHandler;
import troggsboss.Bosses.Gatter.GatterStart;
import troggsboss.Utils;

public class SpawnBossCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("boss")){
            if(sender instanceof Player){
                if(!sender.hasPermission("troggsboss.spawn")){
                    sender.sendMessage(Utils.chat("&cYou do not have permission to run this command."));
                    return true;
                }
            }
            if(args.length <= 0){
                sender.sendMessage(Utils.chat("&cIncorrect Usage! Do /boss <Boss>."));
                return true;
            }
            if(args[0].equalsIgnoreCase("splat")){
                GatterHandler.splat();
            }
            if(args[0].equalsIgnoreCase("phase")){
                Bukkit.broadcastMessage(String.valueOf(GatterHandler.phase));
            }
            if(args[0].equalsIgnoreCase("headsonme")){

            }
            if(args[0].equalsIgnoreCase("gatter")){
                new GatterStart(((Player)sender).getWorld());
                if(args.length > 1){
                    if (args[1].equalsIgnoreCase("gear")) {
                        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
                        ItemMeta meta = sword.getItemMeta();
                        meta.addEnchant(Enchantment.DAMAGE_UNDEAD, 5, true);
                        meta.setUnbreakable(true);
                        sword.setItemMeta(meta);
                        ItemStack armor = new ItemStack(Material.NETHERITE_HELMET);
                        ItemMeta aMeta = armor.getItemMeta();
                        aMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
                        aMeta.setUnbreakable(true);
                        armor.setItemMeta(aMeta);
                        Player player = (Player) sender;
                        player.getInventory().addItem(sword);
                        player.getInventory().addItem(armor);
                        armor.setType(Material.NETHERITE_CHESTPLATE);
                        player.getInventory().addItem(armor);
                        armor.setType(Material.NETHERITE_LEGGINGS);
                        player.getInventory().addItem(armor);
                        armor.setType(Material.NETHERITE_BOOTS);
                        player.getInventory().addItem(armor);
                    }
                }
            }
        }
        return false;
    }
}
