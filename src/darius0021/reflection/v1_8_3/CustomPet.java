package darius0021.reflection.v1_8_3;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import darius0021.BattleMobs;
import darius0021.MobStats;
import net.minecraft.server.v1_8_R3.EntityEndermite;
import net.minecraft.server.v1_8_R3.World;

public class CustomPet extends EntityEndermite {
	ArmorStand stand;
	MobStats stats;
	public CustomPet(World arg0) {
		super(arg0);
	}
	public CustomPet(World world, String type, Location loc, Player p) {
		super(world);
		stats = BattleMobs.statsai.get(type.toLowerCase());
		stand = (ArmorStand) EntityTypes.spawnEntity(EntityTypes.createEntity("armorstand", world), loc.add(stats.offX, stats.offY-1.28, stats.offZ)).getBukkitEntity();
		stand.setHelmet(stats.texture);
		stand.setVisible(false);
		stand.setGravity(false);
		stand.setMetadata("Owner", new FixedMetadataValue(BattleMobs.plugin, p.getUniqueId()));
	}
	public void update() {
		stand.teleport(this.getBukkitEntity().getLocation().add(stats.offX, stats.offY-1.28, stats.offZ));
	}
	public void removeall() {
		stand.remove();
	}
	public void updatename() {
		stand.setCustomName(this.getBukkitEntity().getCustomName());
		stand.setCustomNameVisible(true);
	}
}