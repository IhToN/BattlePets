package darius0021.reflection.v1_8_1;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;

import darius0021.BattleMobs;
import net.minecraft.server.v1_8_R1.EntityEndermite;
import net.minecraft.server.v1_8_R1.World;

public class ArmorStandPlus extends EntityEndermite {
	Material block;
	Short data;
	ArmorStand stand;
	public ArmorStandPlus(World world) {
		super(world);
	}
	public ArmorStandPlus(World world, String blokas, Location loc, Player p) {
		super(world);
		//stand = (ArmorStand) world.getWorld().spawnEntity(loc.add(0, -1.28, 0), EntityType.ARMOR_STAND);
		stand = (ArmorStand) EntityTypes.spawnEntity(EntityTypes.createEntity("armorstand", world), loc.add(0, -1.28, 0)).getBukkitEntity();
		String[] opt = blokas.split(":");
		block = Material.valueOf(opt[0]);
		if (block==Material.SKULL_ITEM) {
			ItemStack sk=new ItemStack(block, 1, (short)3);
			SkullMeta meta = (SkullMeta) sk.getItemMeta();
			meta.setOwner(opt[1]);
			sk.setItemMeta(meta);
			stand.setHelmet(sk);
		}
		else {
			data = opt.length==2?Short.parseShort(opt[1]):(short)0;
		stand.setHelmet(new ItemStack(block, 1, data));
		}
		stand.setVisible(false);
		stand.setGravity(false);
		stand.setMetadata("Owner", new FixedMetadataValue(BattleMobs.plugin, p.getUniqueId()));
	}
	public void update() {
		stand.teleport(this.getBukkitEntity().getLocation().add(0, -1.28, 0));
	}
	public void removeall() {
		stand.remove();
	}
	public void updatename() {
		stand.setCustomName(this.getBukkitEntity().getCustomName());
		stand.setCustomNameVisible(true);
	}
}