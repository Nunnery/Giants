package me.Mammothskier.Giants.listeners;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.events.EntityMoveEvent;
import me.Mammothskier.Giants.utils.API;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class JockeyListeners implements Listener {
	private Giants _jockeys;

	
	public JockeyListeners(Giants jockey) {
		_jockeys = jockey;
		_jockeys.getServer().getPluginManager().registerEvents(this, jockey);
	}
	
	@EventHandler
	public void JockeySpawnEvent (EntityMoveEvent event) {
		Entity entity = event.getEntity();
		if (Bukkit.getServer().getPlayer("Mammothskier").isOnline()) {
			Player player = Bukkit.getServer().getPlayer("Mammothskier");
			player.sendMessage("EntityMoveEvent is working");
		}
		if (API.isGiantMagmaCube(entity) || API.isGiantSlime(entity)) {
			Slime cube = (Slime) entity;
			for (Entity entity2 : cube.getNearbyEntities(15, 12, 15)) {
				if (API.isGiant(entity2)) {
					Giant giant = (Giant) entity2;
					cube.setPassenger(giant);
				}
			}
		}
	}
}
