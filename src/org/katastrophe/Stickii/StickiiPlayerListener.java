package org.katastrophe.Stickii;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

/*
 * Stickii
 * 
 * Kieran Breeze 2011
 *
 */

public class StickiiPlayerListener extends PlayerListener {
	
	public Stickii plugin;
	
	public StickiiPlayerListener(Stickii instance) {
		plugin = instance;
	}
	
	@Override
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		Material material = event.getPlayer().getItemInHand().getType();
		Player player = event.getPlayer();
		Block block = player.getTargetBlock(null, 50);
		Location location = block.getLocation();
		Integer blockid = block.getTypeId();
		World world = location.getWorld();
		ItemStack item = new ItemStack(blockid, 1);
		
		if (this.plugin.stickiiUsers.containsKey(player)) {
		
			if (material.equals(Material.STICK)) {
				
				if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				{
					block.setTypeId(0);
				} 
				else if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) 
				{
					world.dropItem(location, item);
					block.setTypeId(0);
				}
				
				
			} else {
				// do nothing
			}
			
		} else {
			//do nothing
		}
		
	}
	
}
