package troggsboss.Mobs.Gatter.GatterBoss;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import troggsboss.Utils;

public class WitherShieldMob extends EntityVex {

    public WitherShieldMob(Location loc){
        super(EntityTypes.VEX, ((CraftWorld) loc.getWorld()).getHandle());
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setCustomName(new ChatComponentText(Utils.chat("&0")));
        this.setCustomNameVisible(false);
        this.setNoAI(true);
    }

}
