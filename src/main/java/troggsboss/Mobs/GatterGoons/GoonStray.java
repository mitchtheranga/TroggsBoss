package troggsboss.Mobs.GatterGoons;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import troggsboss.Utils;

public class GoonStray extends EntitySkeletonStray{

    public GoonStray(Location loc){

        super(EntityTypes.STRAY, ((CraftWorld) loc.getWorld()).getHandle());

        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setCanPickupLoot(false);
        this.setCustomName(new ChatComponentText(Utils.chat("&7Gatter Goon")));
        this.setCustomNameVisible(false);

        this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, true));
        for(int i = 0; i < this.dropChanceHand.length; i++) {
            this.dropChanceHand[i] = 0;
        }
        org.bukkit.inventory.ItemStack sword = new ItemStack(Material.BOW);
        this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(sword));
    }
}
