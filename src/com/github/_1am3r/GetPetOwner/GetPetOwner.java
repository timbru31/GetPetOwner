package com.github._1am3r.GetPetOwner;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * GetPetOwner for Bukkit
 * 
 * @author 1am3r, noroom
 */

public class GetPetOwner extends JavaPlugin implements Listener {

	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		Logger.getLogger(pdfFile.getName()).log(Level.INFO, pdfFile.getName() + " disabled!");
	}

	public void onEnable() {
		// Register our event handlers
		getServer().getPluginManager().registerEvents(this, this);

		PluginDescriptionFile pdfFile = this.getDescription();
		Logger.getLogger(pdfFile.getName()).log(Level.INFO, pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Tameable) {
			if (((Tameable) e.getRightClicked()).isTamed()) {
				AnimalTamer owner = ((Tameable) e.getRightClicked()).getOwner();
				String ownerName = ((CraftPlayer) owner).getName();
				if (!e.getPlayer().getName().equals(ownerName)) {
					e.getPlayer().sendMessage(ChatColor.GRAY + "The owner of this animal is " + ownerName + "!");
				}
			}
		}
	}
}