package org.katastrophe.Sticky;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * Sticky Plugin
 * 
 * Kieran Breeze 2011
 *
 */

public class StickyPlugin extends JavaPlugin {

	PluginDescriptionFile info = this.getDescription();
	
	private final StickyBlockListener blockListener = new StickyBlockListener();
	public final static HashMap<Player, ArrayList<Block>> stickyUsers = new HashMap();
	private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
	
	
	@Override
	public void onDisable() {
		System.out.println("[StickyPlugin - Disabled!]");
		
	}

	@Override
	public void onEnable() {
		
		
		System.out.println("[StickyPlugin - Enabled!]");
		
		getServer().getPluginManager().registerEvent(Event.Type.BLOCK_PLACE, blockListener, Event.Priority.Normal, this);
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args, Player player) {
		String commandName = cmd.getName().toLowerCase();
		if (commandName.equals("sticky") && sender.isOp()) {
			toggleVision((Player) sender);
		} else {
			player.sendMessage("You need to be opped to use this plugin! Sorry!");
		}
		
		
		return false;
	}
	
	public boolean isDebugging(final Player player) {
		if (debugees.containsKey(player)) {
			return debugees.get(player);
		} else {
			return false;
		}
	}
	
	public void setDebugging(final Player player, final boolean value){
		debugees.put(player, value);
	}
	
	public boolean enabled(Player player) {
		return StickyPlugin.stickyUsers.containsKey(player);
	}

	public void toggleVision(Player player) {
		if (enabled(player)) {
			StickyPlugin.stickyUsers.remove(player);
			player.sendMessage("StickyPlugin - Disabled!]");
		} else {
			StickyPlugin.stickyUsers.put(player, null);
			player.sendMessage("StickyPlugin - Enabled!]");
		}
		
	}

}
