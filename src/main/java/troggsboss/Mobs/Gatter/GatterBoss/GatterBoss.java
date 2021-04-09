package troggsboss.Mobs.Gatter.GatterBoss;

import net.minecraft.server.v1_16_R3.*;
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

public class GatterBoss extends EntitySkeletonWither {

    public GatterBoss(Location loc){
        super(EntityTypes.WITHER_SKELETON, ((CraftWorld) loc.getWorld()).getHandle());

        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setCustomName(new ChatComponentText(Utils.chat("&8&lGatter &7[&f4000&7/&f4000&7]")));
        this.setCustomNameVisible(true);
        this.setCanPickupLoot(false);

        this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, true));
        for(int i = 0; i < this.dropChanceHand.length; i++) {
            this.dropChanceHand[i] = 0;
        }
        org.bukkit.inventory.ItemStack sword;
        sword = new ItemStack(Material.NETHERITE_HOE);
        this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(sword));
        ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta helmM = (LeatherArmorMeta) helm.getItemMeta();
        LeatherArmorMeta chestM = (LeatherArmorMeta) chest.getItemMeta();
        LeatherArmorMeta legsM = (LeatherArmorMeta) legs.getItemMeta();
        LeatherArmorMeta bootsM = (LeatherArmorMeta) boots.getItemMeta();
        helmM.setColor(Color.BLACK);
        chestM.setColor(Color.BLACK);
        legsM.setColor(Color.BLACK);
        bootsM.setColor(Color.BLACK);
        helm.setItemMeta(helmM);
        chest.setItemMeta(chestM);
        legs.setItemMeta(legsM);
        boots.setItemMeta(bootsM);
        this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(helm));
        this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(chest));
        this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(legs));
        this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(boots));
    }
}
