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
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof Tameable && event.getPlayer().hasPermission("getwolfowner.getowner")) {
			if (event.getRightClicked() instanceof Wolf) {
				Wolf clickedWolf = (Wolf) event.getRightClicked();
				if (clickedWolf.isTamed()) {
					AnimalTamer wolfOwner = clickedWolf.getOwner();
					if (wolfOwner instanceof Player) {
						Player owner = (Player) clickedWolf.getOwner();
						if (!event.getPlayer().equals(owner)) {
							event.getPlayer().sendMessage("§7The owner of this wolf is " + owner.getName() + ", Health: " + clickedWolf.getHealth());
						}
					} else if (wolfOwner instanceof OfflinePlayer) {
						OfflinePlayer owner = (OfflinePlayer) clickedWolf.getOwner();
						event.getPlayer().sendMessage("§7The owner of this wolf is " + owner.getName() + " (Offline)" + ", Health: " + clickedWolf.getHealth());
					}
				}
			} else if (event.getRightClicked() instanceof Ocelot) {
				Ocelot clickedCat = (Ocelot) event.getRightClicked();
				if (clickedCat.isTamed()) {
					AnimalTamer wolfOwner = clickedCat.getOwner();
					if (wolfOwner instanceof Player) {
						Player owner = (Player) clickedCat.getOwner();
						if (!event.getPlayer().equals(owner)) {
							event.getPlayer().sendMessage("§7The owner of this cat is " + owner.getName() + ", Health: " + clickedCat.getHealth());
						}
					} else if (wolfOwner instanceof OfflinePlayer) {
						OfflinePlayer owner = (OfflinePlayer) clickedCat.getOwner();
						event.getPlayer().sendMessage("§7The owner of this cat is " + owner.getName() + " (Offline)" + ", Health: " + clickedCat.getHealth());
					}
				}
			}
		}
	}
}