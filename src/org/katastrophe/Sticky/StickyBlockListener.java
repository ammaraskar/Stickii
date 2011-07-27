package org.katastrophe.Sticky;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.player.PlayerInteractEvent;

/*
 * Sticky Plugin
 * 
 * Kieran Breeze 2011
 *
 */

public class StickyBlockListener extends BlockListener {
	
	public static StickyPlugin plugin;
	
	public void StickyPluginBlockListener(StickyPlugin instance) {
		plugin = instance;
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		Material material = event.getMaterial();
		Block block = event.getClickedBlock();
		Player player = event.getPlayer();
		
		if (StickyPlugin.stickyUsers.containsKey(player)) {
		
			if (material.equals(Material.STICK)) {
				block.setTypeId(0);
			}
		
		}
		
	}
	
}
