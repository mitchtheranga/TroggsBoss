package troggsboss.Mobs.Gatter;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import troggsboss.Utils;

public class GoonStrayStrengthHealth extends EntitySkeletonStray{

    public GoonStrayStrengthHealth(Location loc){

        super(EntityTypes.STRAY, ((CraftWorld) loc.getWorld()).getHandle());

        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setCanPickupLoot(false);
        this.setCustomName(new ChatComponentText(Utils.chat("&7Gatter Goon")));
        this.setCustomNameVisible(false);
        this.setAbsorptionHearts(40);

        this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, true));
        for(int i = 0; i < this.dropChanceHand.length; i++) {
            this.dropChanceHand[i] = 0;
        }
        ItemStack sword = new ItemStack(Material.BOW);
        ItemMeta meta = sword.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
        sword.setItemMeta(meta);
        this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(sword));
    }
}
