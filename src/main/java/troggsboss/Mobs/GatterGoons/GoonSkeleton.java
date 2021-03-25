package troggsboss.Mobs.GatterGoons;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import troggsboss.Utils;

import java.util.ArrayList;
import java.util.List;

public class GoonSkeleton extends EntitySkeleton {

    public GoonSkeleton(Location loc){

        super(EntityTypes.SKELETON, ((CraftWorld) loc.getWorld()).getHandle());

        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setCanPickupLoot(false);
        this.setCustomName(new ChatComponentText(Utils.chat("&7Gatter Goon")));
        this.setCustomNameVisible(false);

        this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, true));
        for(int i = 0; i < this.dropChanceHand.length; i++) {
            this.dropChanceHand[i] = 0;
        }
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(sword));
    }
}
