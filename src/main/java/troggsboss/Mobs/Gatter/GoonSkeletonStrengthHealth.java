package troggsboss.Mobs.Gatter;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import troggsboss.Utils;

public class GoonSkeletonStrengthHealth extends EntitySkeleton {

    public GoonSkeletonStrengthHealth(Location loc){

        super(EntityTypes.SKELETON, ((CraftWorld) loc.getWorld()).getHandle());

        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setCanPickupLoot(false);
        this.setCustomName(new ChatComponentText(Utils.chat("&7Gatter Goon")));
        this.setCustomNameVisible(false);
        this.setAbsorptionHearts(40);

        this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, true));
        for(int i = 0; i < this.dropChanceHand.length; i++) {
            this.dropChanceHand[i] = 0;
        }
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(sword));
        this.addEffect(new MobEffect(MobEffects.INCREASE_DAMAGE, 1000000, 4, true, true));
    }
}
