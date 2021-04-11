package troggsboss.Bosses.Gatter;


import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import troggsboss.Main;
import troggsboss.Mobs.Gatter.GatterBoss.GatterBoss;
import troggsboss.Mobs.Gatter.GoonSkeleton;
import troggsboss.Mobs.Gatter.GoonSkeletonStrength;
import troggsboss.Mobs.Gatter.GoonStray;
import troggsboss.Mobs.Gatter.GoonStrayStrength;
import troggsboss.Utils;

import java.util.ArrayList;
import java.util.List;

public class GatterHandler {
    public static LivingEntity gatter = null;
    public static Integer phase;
    public static int currentHealth;
    public static int maxHealth;
    public static boolean lightningLastAttack = false;
    public static int goonsLeftF2, goonsLeftF4, goonsLeftF6;

    public static void newGatter(GatterBoss boss){
        gatter = (LivingEntity) boss.getBukkitEntity();
        phase = 1;
        String nameNF = ChatColor.stripColor(gatter.getCustomName());
        String nameFF = nameNF.replace("[", "");
        nameFF = nameFF.replace("]", "");
        nameFF = nameFF.replace("Gatter", "");
        nameFF = nameFF.replace(" ", "");
        currentHealth = Integer.parseInt(nameFF.split("/")[0]);
        maxHealth = Integer.parseInt(nameFF.split("/")[1]);
        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                phaseCheck();
            }
        }, 300L);
        updateActionBar();
    }

    public static void gatterHit(){
        if(currentHealth > 3000){
            phase1();
            return;
        }
        if(currentHealth == 3000 && phase == 1){
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

    public static void phaseCheck(){
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                if(!gatter.isDead()) {
                    phaseCheck();
                }
            }
        }, 500L);
        if(phase == 1){
            int attacknumb = Utils.getRandomInt(5);
            if(attacknumb == 1 && !lightningLastAttack) {
                lightningAttack(3);
            }else{
                lightningLastAttack = false;
            }
        }
        if(phase == 3){
            int attacknumb = Utils.getRandomInt(5);
            if(attacknumb == 1 && !lightningLastAttack) {
                lightningAttack(5);
            }else{
                lightningLastAttack = false;
            }
            if(attacknumb >= 2) {
                witherBombAttack();
            }
        }
        if(phase == 5){
            int attacknumb = Utils.getRandomInt(10);
            if(attacknumb == 1 && !lightningLastAttack) {
                lightningAttack(7);
            }else{
                lightningLastAttack = false;
            }
            if(attacknumb >= 2 && attacknumb <= 7) {
                witherBombAttack();
            }
            if(attacknumb > 8) {
                gatter.setAI(false);
                Location gatterLoc = gatter.getLocation();
                gatter.teleport(new Location(gatter.getWorld(), 8.5, 56, 8.5));
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.broadcastMessage(Utils.chat("&8&lGatter&7: &cYOU CAN HIDE BUT YOU CANT HEAL!"));
                    }
                }, 30L);
                Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.broadcastMessage(Utils.chat("&8&lGatter&7: &cFEEL THE CORRUPT DAMAGE OF MY WITHER BOMBS!"));
                    }
                }, 70L);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        for (Block block : Utils.getNearbyBlocks(gatter.getLocation(), 50)) {
                            if (block.getType().equals(Material.CRIMSON_NYLIUM)) {
                                if(Utils.getRandomInt(3) == 1){
                                    block.setType(Material.BLACKSTONE);
                                }
                            }
                        }
                        witherBombDamage();
                    }
                }, 110L);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        gatter.teleport(gatterLoc);
                        gatter.setAI(true);
                    }
                }, 135L);
            }
        }
    }

    public static void goonKilled(){
        if(goonsLeftF2 <= 0 && phase == 2){

            phase3();
            return;
        }
        if(goonsLeftF4 <= 0 && phase == 4){
            phase5();
            return;
        }
        if(goonsLeftF6 <= 0 && phase == 6){
            phase7();
            return;
        }
    }

    public static void phase1(){
        phase = 1;
        gatter.setInvulnerable(false);
    }

    public static void phase2(){
        gatter.setAI(false);
        gatter.teleport(new Location(gatter.getWorld(), 8.5, 56, 8.5, 0, 0));
        phase = 2;
        gatter.setInvulnerable(true);
        goonsLeftF2 = 0;
        while(goonsLeftF2 < 50) {
            for (Block block : Utils.getNearbyBlocks(gatter.getLocation(), 50)) {
                if(block.getType().equals(Material.CRIMSON_NYLIUM)) {
                    if (Utils.getRandomInt(24) == 1) {
                        if (goonsLeftF2 < 50) {
                            WorldServer world = ((CraftWorld) gatter.getWorld()).getHandle();
                            Location loc = new Location(block.getWorld(), block.getX() + .5, block.getY() + 1, block.getZ() + .5);
                            Location loc2 = new Location(block.getWorld(), block.getX() + .5, block.getY() + 2, block.getZ() + .5);
                            if(loc.getBlock().getType().equals(Material.AIR) && loc2.getBlock().getType().equals(Material.AIR)) {
                                if (Utils.getRandomInt(2) == 1) {
                                    GoonSkeleton goon = new GoonSkeleton(loc);
                                    world.addEntity(goon);
                                } else {
                                    GoonStray goon = new GoonStray(loc);
                                    world.addEntity(goon);
                                }
                                goonsLeftF2 += 1;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void phase3(){
        lightningLastAttack = false;
        phase = 3;
        gatter.setInvulnerable(false);
        gatter.setAI(true);
        gatter.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 4, false, false));
    }

    public static void phase4(){
        phase = 4;
        gatter.setInvulnerable(true);
        for (Block block : Utils.getNearbyBlocks(gatter.getLocation(), 50)) {
            if (block.getType().equals(Material.BLACKSTONE)) {
                block.setType(Material.CRIMSON_NYLIUM);
            }
        }
        gatter.setAI(false);
        gatter.teleport(new Location(gatter.getWorld(), 8.5, 56, 8.5, 0, 0));
        goonsLeftF4 = 0;
        while(goonsLeftF4 < 100) {
            for (Block block : Utils.getNearbyBlocks(gatter.getLocation(), 50)) {
                if(block.getType().equals(Material.CRIMSON_NYLIUM)) {
                    if (Utils.getRandomInt(12) == 1) {
                        if (goonsLeftF4 < 100) {
                            WorldServer world = ((CraftWorld) gatter.getWorld()).getHandle();
                            Location loc = new Location(block.getWorld(), block.getX() + .5, block.getY() + 1, block.getZ() + .5);
                            Location loc2 = new Location(block.getWorld(), block.getX() + .5, block.getY() + 2, block.getZ() + .5);
                            if(loc.getBlock().getType().equals(Material.AIR) && loc2.getBlock().getType().equals(Material.AIR)) {
                                if (Utils.getRandomInt(2) == 1) {
                                    GoonSkeletonStrength goon = new GoonSkeletonStrength(loc);
                                    world.addEntity(goon);
                                } else {
                                    GoonStrayStrength goon = new GoonStrayStrength(loc);
                                    world.addEntity(goon);
                                }
                                goonsLeftF4 += 1;
                            }
                        }
                    }
                }
            }
        }

    }
    public static void phase5(){
        phase = 5;
        gatter.setInvulnerable(false);
        gatter.setAI(true);
        gatter.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 6, false, false));
        ItemStack item = new org.bukkit.inventory.ItemStack(Material.NETHERITE_HOE);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
        item.setItemMeta(meta);
        gatter.getEquipment().setItemInMainHand(item);
    }
    public static void phase6(){
        phase = 6;
        gatter.setInvulnerable(true);
        for (Block block : Utils.getNearbyBlocks(gatter.getLocation(), 50)) {
            if (block.getType().equals(Material.BLACKSTONE)) {
                block.setType(Material.CRIMSON_NYLIUM);
            }
        }
    }
    public static void phase7(){
        phase = 7;
        gatter.setInvulnerable(false);
    }

    public static void damageGatter(int amount) {
        String nameNF = ChatColor.stripColor(gatter.getCustomName());
        String nameFF = nameNF.replace("[", "");
        nameFF = nameFF.replace("]", "");
        nameFF = nameFF.replace("Gatter", "");
        nameFF = nameFF.replace(" ", "");
        GatterHandler.currentHealth = Integer.parseInt(nameFF.split("/")[0]);
        GatterHandler.maxHealth = Integer.parseInt(nameFF.split("/")[1]);
        currentHealth -= amount;
        String newName = Utils.chat("&8&lGatter &7[&f" + GatterHandler.currentHealth + "&7/&f" + GatterHandler.maxHealth + "&7]");
        gatter.setCustomName(newName);
    }

    public static void witherBombDamage(){
        BoundingBox box = new BoundingBox(-23, 73, -23, 23, 40, 23);
        for(Entity entity : gatter.getWorld().getNearbyEntities(box)){
            if(entity.getType().equals(EntityType.PLAYER)){
                Block block = new Location(entity.getLocation().getWorld(), entity.getLocation().getX(), entity.getLocation().getY() - 1, entity.getLocation().getZ()).getBlock();
                if(block.getType().toString().equalsIgnoreCase("BLACKSTONE")){
                    if(phase == 3) {
                        ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 30, 3));
                    }else if(phase == 5) {
                        ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 30, 5));
                    }
                    else if(phase == 7) {
                        ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 30, 7));
                    }
                }
            }
        }
        if((phase == 3 || phase == 5 || phase == 7) && !gatter.isDead()){
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    witherBombDamage();
                }
            }, 20L);
        }
    }

    public static void changeGatterColor(Color color){
        ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta helmM = (LeatherArmorMeta) helm.getItemMeta();
        LeatherArmorMeta chestM = (LeatherArmorMeta) chest.getItemMeta();
        LeatherArmorMeta legsM = (LeatherArmorMeta) legs.getItemMeta();
        LeatherArmorMeta bootsM = (LeatherArmorMeta) boots.getItemMeta();
        helmM.setColor(color);
        helmM.setUnbreakable(true);
        chestM.setColor(color);
        chestM.setUnbreakable(true);
        legsM.setColor(color);
        legsM.setUnbreakable(true);
        bootsM.setColor(color);
        bootsM.setUnbreakable(true);
        helm.setItemMeta(helmM);
        chest.setItemMeta(chestM);
        legs.setItemMeta(legsM);
        boots.setItemMeta(bootsM);
        gatter.getEquipment().setHelmet(helm);
        gatter.getEquipment().setChestplate(chest);
        gatter.getEquipment().setLeggings(legs);
        gatter.getEquipment().setBoots(boots);
    }
    public static void changeGatterHelmet(Color color){
        ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmM = (LeatherArmorMeta) helm.getItemMeta();
        helmM.setColor(color);
        helmM.setUnbreakable(true);
        helm.setItemMeta(helmM);
        gatter.getEquipment().setHelmet(helm);
    }
    public static void changeGatterChest(Color color){
        ItemStack helm = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta helmM = (LeatherArmorMeta) helm.getItemMeta();
        helmM.setColor(color);
        helmM.setUnbreakable(true);
        helm.setItemMeta(helmM);
        gatter.getEquipment().setChestplate(helm);
    }
    public static void changeGatterLegs(Color color){
        ItemStack helm = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta helmM = (LeatherArmorMeta) helm.getItemMeta();
        helmM.setColor(color);
        helmM.setUnbreakable(true);
        helm.setItemMeta(helmM);
        gatter.getEquipment().setLeggings(helm);
    }
    public static void changeGatterBoots(Color color){
        ItemStack helm = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta helmM = (LeatherArmorMeta) helm.getItemMeta();
        helmM.setColor(color);
        helmM.setUnbreakable(true);
        helm.setItemMeta(helmM);
        gatter.getEquipment().setBoots(helm);
    }

    public static void lightningAttack(int damage){
        lightningLastAttack = true;
        gatter.setAI(false);
        gatter.teleport(new Location(gatter.getWorld(), 8.5, 56, 8.5));
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(Utils.chat("&8&lGatter&7: &cYOU THINK THIS HURTS ME!"));
                changeGatterColor(Color.fromRGB(0, 155, 171));
            }
        }, 30L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(Utils.chat("&8&lGatter&7: &cFEEL THE WRATH OF MY LIGHTNING!"));
                changeGatterColor(Color.fromRGB(0, 180, 199));
            }
        }, 70L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                BoundingBox box = new BoundingBox(-23, 73, -23, 23, 40, 23);
                changeGatterColor(Color.fromRGB(0, 229, 255));
                for (Entity entity : gatter.getWorld().getNearbyEntities(box)) {
                    if (entity.getType().equals(EntityType.PLAYER)) {
                        Player striked = (Player) entity;
                        striked.getWorld().strikeLightningEffect(striked.getLocation());
                        ((LivingEntity) entity).damage(damage);
                        entity.setFireTicks(100);
                        ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 1, true, true));
                    }
                }
            }

        }, 110L);
        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                changeGatterColor(Color.fromRGB(0, 229, 255));
                gatter.setGlowing(true);
                Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        changeGatterColor(Color.fromRGB(0, 0, 0));
                        gatter.setGlowing(false);
                    }
                }, 5L);
                Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        changeGatterColor(Color.fromRGB(0, 229, 255));
                        gatter.setGlowing(true);
                    }
                }, 10L);
                Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        changeGatterColor(Color.fromRGB(0, 0, 0));
                        gatter.setGlowing(false);
                    }
                }, 15L);
                Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        changeGatterColor(Color.fromRGB(0, 229, 255));
                        gatter.setGlowing(true);
                    }
                }, 20L);
                Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        changeGatterColor(Color.fromRGB(0, 0, 0));
                        gatter.setGlowing(false);
                    }
                }, 25L);
            }
        }, 110L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                List<Entity> playersInBoss = new ArrayList<>();
                BoundingBox box = new BoundingBox(-23, 73, -23, 23, 40, 23);
                for(Entity e : gatter.getWorld().getNearbyEntities(box)){
                    if(e.getType().equals(EntityType.PLAYER)){
                        playersInBoss.add(e);
                    }
                }
                gatter.teleport(playersInBoss.get(Utils.getRandomInt(playersInBoss.size())));
                gatter.setAI(true);
            }
        }, 200L);
    }
    public static void witherBombAttack(){
        gatter.setAI(false);
        gatter.teleport(new Location(gatter.getWorld(), 8.5, 56, 8.5));
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(Utils.chat("&8&lGatter&7: &cYOU CAN HIDE BUT YOU CANT HEAL!"));
            }
        }, 30L);
        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(Utils.chat("&8&lGatter&7: &cFEEL THE CORRUPT DAMAGE OF MY WITHER BOMBS!"));
            }
        }, 70L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                for (Block block : Utils.getNearbyBlocks(gatter.getLocation(), 50)) {
                    if (block.getType().equals(Material.CRIMSON_NYLIUM)) {
                        if(Utils.getRandomInt(3) == 1){
                            block.setType(Material.BLACKSTONE);
                        }
                    }
                }
                witherBombDamage();
            }
        }, 110L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                List<Entity> playersInBoss = new ArrayList<>();
                BoundingBox box = new BoundingBox(-23, 73, -23, 23, 40, 23);
                for(Entity e : gatter.getWorld().getNearbyEntities(box)){
                    if(e.getType().equals(EntityType.PLAYER)){
                        playersInBoss.add(e);
                    }
                }
                gatter.teleport(playersInBoss.get(Utils.getRandomInt(playersInBoss.size())));
                gatter.setAI(true);
            }
        }, 135L);
    }

    public static void updateActionBar(){
        String extras = "";
        if(phase == 2){
            extras = Utils.chat(" &8| &4&lGoons Left: &c" + goonsLeftF2);
        }
        if(phase == 4){
            extras = Utils.chat(" &8| &4&lGoons Left: &c" + goonsLeftF4);
        }
        if(phase == 6){
            extras = Utils.chat(" &8| &4&/lGoons Left: &c" + goonsLeftF6);
        }
        BoundingBox box = new BoundingBox(-23, 73, -23, 23, 40, 23);
        for(Entity e : gatter.getWorld().getNearbyEntities(box)) {
            if(e.getType().equals(EntityType.PLAYER)) {
                Player player = (Player) e;
                String message = Utils.chat("&4&lGatter: &c" + currentHealth + "/" + maxHealth + extras);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Utils.chat(message)));
            }
        }
        if(!gatter.isDead()){
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    updateActionBar();
                }
            }, 5L);
        }
    }
}
