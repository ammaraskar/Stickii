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
	private final StickyPlayerListener blockListener = new StickyPlayerListener(this);
	public final HashMap<Player, ArrayList<Block>> stickyUsers = new HashMap<Player, ArrayList<Block>>();
	private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
	
	
	@Override
	public void onDisable() {
		System.out.println("[StickyPlugin - Disabled!]");
		
	}

	@Override
	public void onEnable() {
		System.out.println("[StickyPlugin - Enabled!]");
		
		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_INTERACT, blockListener, Event.Priority.Normal, this);
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String commandName = cmd.getName().toLowerCase();
		if(!(sender instanceof Player)) {
			return false;
		}
		if (commandName.equals("sticky") && sender.isOp()) {
			toggleVision((Player) sender);
		} else {
			sender.sendMessage("You need to be an OP to use StickyPlugin!");
		}
		
		return true;
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
		return stickyUsers.containsKey(player);
	}

	public void toggleVision(Player player) {
		if (enabled(player)) {
			this.stickyUsers.remove(player);
			player.sendMessage("StickyPlugin - Disabled!");
		} else {
			this.stickyUsers.put(player, null);
			player.sendMessage("StickyPlugin - Enabled!");
		}
		
	}

}
