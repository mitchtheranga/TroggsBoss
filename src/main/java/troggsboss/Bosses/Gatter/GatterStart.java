package troggsboss.Bosses.Gatter;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import troggsboss.Mobs.Gatter.GatterBoss.GatterBoss;
import troggsboss.Mobs.Gatter.SnoozedGoon.SnoozedGoon;

public class GatterStart {

    public GatterStart(){
        World world = Bukkit.getWorld("world");
        Location bossLoc = new Location(world, 8.5, 53, 8.5);
        spawnSnoozed(bossLoc);
        spawnGatter(bossLoc);
    }

    public void spawnSnoozed(Location middleLoc){
        WorldServer world = ((CraftWorld) middleLoc.getWorld()).getHandle();
        Location l1 = new Location(middleLoc.getWorld(), middleLoc.getX()+8, middleLoc.getY()+0.5, middleLoc.getZ());
        Location l2 = new Location(middleLoc.getWorld(), middleLoc.getX()-8, middleLoc.getY()+0.5, middleLoc.getZ());
        Location l3 = new Location(middleLoc.getWorld(), middleLoc.getX(), middleLoc.getY()+0.5, middleLoc.getZ()+8);
        Location l4 = new Location(middleLoc.getWorld(), middleLoc.getX(), middleLoc.getY()+0.5, middleLoc.getZ()-8);
        SnoozedGoon sG1 = new SnoozedGoon(l1);
        SnoozedGoon sG2 = new SnoozedGoon(l2);
        SnoozedGoon sG3 = new SnoozedGoon(l3);
        SnoozedGoon sG4 = new SnoozedGoon(l4);
        world.addEntity(sG1);
        world.addEntity(sG2);
        world.addEntity(sG3);
        world.addEntity(sG4);

    }

    public void spawnGatter(Location middleLoc){
        WorldServer world = ((CraftWorld) middleLoc.getWorld()).getHandle();
        Location gatterStartLoc = new Location(middleLoc.getWorld(), middleLoc.getX(), middleLoc.getY() + 3, middleLoc.getZ());
        GatterBoss gatter = new GatterBoss(gatterStartLoc);
        world.addEntity(gatter);
        GatterHandler.newGatter(gatter);
    }

}
