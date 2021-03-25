package troggsboss.Bosses;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
            if(args[0].equalsIgnoreCase("gatter")){
                new GatterStart();
            }
        }
        return false;
    }
}
