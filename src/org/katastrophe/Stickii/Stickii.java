package org.katastrophe.Stickii;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * Stickii
 * 
 * Kieran Breeze 2011
 *
 */

public class Stickii extends JavaPlugin {

	private final StickiiPlayerListener blockListener = new StickiiPlayerListener(this);
	public final HashMap<Player, ArrayList<Block>> stickiiUsers = new HashMap<Player, ArrayList<Block>>();
	private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
	
	
	@Override
	public void onDisable() {
		System.out.println("[Stickii] - Disabled!");
		
	}

	@Override
	public void onEnable() {
		System.out.println("[Stickii] - Enabled!");
		
		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_INTERACT, blockListener, Event.Priority.Normal, this);
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String commandName = cmd.getName().toLowerCase();
		if(!(sender instanceof Player)) {
			System.out.println("Only people in-game can use Stickii!");
			return false;
		}
		if (commandName.equals("stickii") && sender.isOp()) {
			toggleVision((Player) sender);
		} else {
			sender.sendMessage(ChatColor.AQUA + "You need to be OP to use Stickii. Sorry!");
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
		return stickiiUsers.containsKey(player);
	}

	public void toggleVision(Player player) {
		if (enabled(player)) {
			this.stickiiUsers.remove(player);
			player.sendMessage(ChatColor.AQUA + "Stickii - Disabled!");
			
			System.out.println("[Stickii] - Is now not in use by: " + player + "! :(");
		} else {
			this.stickiiUsers.put(player, null);
			player.sendMessage(ChatColor.AQUA + "Stickii - Enabled!");
			player.sendMessage(ChatColor.AQUA + "Be careful! It has alot of range!");
			
			System.out.println("[Stickii] - Is now in use by: " + player + "! :D");
		}
		
	}

}
