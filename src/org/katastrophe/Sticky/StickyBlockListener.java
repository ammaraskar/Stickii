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
	
	public StickyPlugin plugin;
	
	public StickyBlockListener(StickyPlugin instance) {
		plugin = instance;
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		Material material = event.getPlayer().getItemInHand().getType();
		Block block = event.getClickedBlock();
		Player player = event.getPlayer();
		
		
		if (this.plugin.stickyUsers.containsKey(player)) {
		
			if (material.equals(Material.STICK)) {
				if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				{
					block.setTypeId(0);
				}
			}
		
		}
		
	}
	
}
