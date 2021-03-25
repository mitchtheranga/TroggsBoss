package troggsboss.Bosses.Gatter;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import troggsboss.Mobs.GatterGoons.SnoozedGoon.SnoozedGoon;

public class GatterStart {

    public GatterStart(){
        World world = Bukkit.getWorld("world");
        Location bossLoc = new Location(world, 8.5, 53, 8.5);
        spawnSnoozed(bossLoc);
    }

    public void spawnSnoozed(Location middleLoc){
        WorldServer world = ((CraftWorld) middleLoc.getWorld()).getHandle();
        Location l1 = new Location(middleLoc.getWorld(), middleLoc.getX()+8, middleLoc.getY()+0.5, middleLoc.getZ());
        Location l2 = new Location(middleLoc.getWorld(), middleLoc.getX()-8, middleLoc.getY()+0.5, middleLoc.getZ());
        Location l3 = new Location(middleLoc.getWorld(), middleLoc.getX(), middleLoc.getY()+0.5, middleLoc.getZ()+8);
        Location l4 = new Location(middleLoc.getWorld(), middleLoc.getX(), middleLoc.getY()+0.5, middleLoc.getZ()-8);
        SnoozedGoon g1 = new SnoozedGoon(l1);
        SnoozedGoon g2 = new SnoozedGoon(l2);
        SnoozedGoon g3 = new SnoozedGoon(l3);
        SnoozedGoon g4 = new SnoozedGoon(l4);
        world.addEntity(g1);
        world.addEntity(g2);
        world.addEntity(g3);
        world.addEntity(g4);
    }

}
