package troggsboss;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Utils {
    public static String chat(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static Integer getRandomInt(Integer max) {
        Random ran = new Random();
        return ran.nextInt(max);
    }

    public static String invis(String message){
        StringBuilder hidden = new StringBuilder();
        for(char c : message.toCharArray()){
            hidden.append(ChatColor.COLOR_CHAR + "").append(c);
        }
        return hidden.toString();
    }
}
