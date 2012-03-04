package com.github._1am3r.GetWolfOwner;

import java.util.logging.Logger;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * GetWolfOwner for Bukkit
 * 
 * @author 1am3r, noroom
 */

public class GetWolfOwner extends JavaPlugin implements Listener {
	public static final Logger log = Logger.getLogger("Minecraft");

	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " disabled!");
	}

	public void onEnable() {
		// Register our event handlers
		this.getServer().getPluginManager().registerEvents(this, this);

		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof Wolf && event.getPlayer().hasPermission("getwolfowner.getowner")) {
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
		}
	}
}