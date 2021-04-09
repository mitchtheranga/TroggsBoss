package troggsboss.Bosses.Gatter;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import troggsboss.Mobs.Gatter.GatterBoss.GatterBoss;
import troggsboss.Utils;

import java.util.List;

public class GatterHandler {
    public static LivingEntity gatter = null;
    public static Integer phase;
    public static int currentHealth;
    public static int maxHealth;

    public static void newGatter(GatterBoss boss){
        gatter = (LivingEntity) boss.getBukkitEntity();
        phase = 1;
    }

    public static void gatterHit(){
        if(currentHealth <= 3000 && (phase == 1 || phase == 2)){
            gatter.setInvulnerable(true);
            String newName = Utils.chat("&8&lGatter &7[&f" + 3000 + "&7/&f" + maxHealth + "&7]");
            gatter.setCustomName(newName);
        }
        if(currentHealth <= 2000 && (phase == 3 || phase == 4)){
            gatter.setInvulnerable(true);
            String newName = Utils.chat("&8&lGatter &7[&f" + 2000 + "&7/&f" + maxHealth + "&7]");
            gatter.setCustomName(newName);
        }
        if(currentHealth <= 1000 && (phase == 5 || phase == 6)){
            gatter.setInvulnerable(true);
            String newName = Utils.chat("&8&lGatter &7[&f" + 1000 + "&7/&f" + maxHealth + "&7]");
            gatter.setCustomName(newName);
        }
        if(currentHealth > 3000){
            phase1();
            return;
        }
        if(currentHealth == 3000 && phase != 3){
            phase2();
            return;
        }
        if(currentHealth > 2000){
            phase3();
            return;
        }
        if(currentHealth == 2000 && phase != 5){
            phase4();
            return;
        }
        if(currentHealth > 1000){
            phase5();
            return;
        }
        if(currentHealth == 1000 && phase != 7){
            phase6();
            return;
        }
        if(currentHealth > 0){
            phase7();
            return;
        }
    }

    public static void phase1(){
        phase = 1;
    }

    public static void phase2(){
        phase = 2;
    }

    public static void phase3(){
        phase = 3;
    }

    public static void phase4(){
        phase = 4;
    }

    public static void phase5(){
        phase = 5;
    }

    public static void phase6(){
        phase = 6;
    }

    public static void phase7(){
        phase = 7;
    }

}
