package troggsboss.Mobs.Gatter.SnoozedGoon;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import troggsboss.Utils;

public class SnoozedGoon extends EntitySkeletonWither {

    public SnoozedGoon(Location loc){

        super(EntityTypes.WITHER_SKELETON, ((CraftWorld) loc.getWorld()).getHandle());

        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setCustomName(new ChatComponentText(Utils.chat("&5Resting Body")));
        this.setCustomNameVisible(true);
        this.setCanPickupLoot(false);

        this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, true));
        for(int i = 0; i < this.dropChanceHand.length; i++) {
            this.dropChanceHand[i] = 0;
        }
        org.bukkit.inventory.ItemStack sword;
        if(Utils.getRandomInt(2) == 1) {
            sword = new ItemStack(Material.BOW);
            ItemMeta meta = sword.getItemMeta();
            meta.addEnchant(Enchantment.ARROW_DAMAGE, 40, true);
            sword.setItemMeta(meta);
            this.setAbsorptionHearts(40);
        }else{
            sword = new ItemStack(Material.NETHERITE_SWORD);
            ItemMeta meta = sword.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ALL, 100, true);
            sword.setItemMeta(meta);
            this.setAbsorptionHearts(60);
        }
        this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(sword));
        this.setNoAI(true);
    }
}
