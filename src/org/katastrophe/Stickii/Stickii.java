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

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;

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
	private boolean UsePermissions;
	public PermissionHandler Permissions;
	private void setupPermissions() {
	    Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
	    if (this.Permissions == null) {
	        if (test != null) {
	            UsePermissions = true;
	            this.Permissions = ((Permissions) test).getHandler();
	            System.out.println("[Stickii] - Permissions system detected!");
	        } else {
	        	System.out.println("[Stickii] - Permission system not detected, defaulting to OP");
	            UsePermissions = false;
	        }
	    }
	}

    public boolean canUse(Player p) {
        if (UsePermissions) {
            return this.Permissions.has(p, "stickii.use");
        }
        return p.isOp();
    }
	
	@Override
	public void onDisable() {
		System.out.println(ChatColor.AQUA + "[Stickii] - Disabled!");
	}

	@Override
	public void onEnable() {
		setupPermissions();
		
		System.out.println(ChatColor.AQUA + "[Stickii] - Enabled!");
		System.out.println(ChatColor.AQUA + "[Stickii] - Permissions Node:" + ChatColor.GREEN + "sticky.use ");
		
		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_INTERACT, blockListener, Event.Priority.Normal, this);
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String commandName = cmd.getName().toLowerCase();
		Player player = (Player) sender;
		if(!(sender instanceof Player)) {
			System.out.println(ChatColor.AQUA + "Only people in-game can use Stickii!");
			return false;
		}
		if(canUse(player)) {
			
			if (commandName.equals("stickii")) {
				toggleVision((Player) sender);
			} else {
				//do nothing
			}
			
		} else {
			sender.sendMessage(ChatColor.AQUA + "You do not have the correct permissions to use Stickii. Sorry!");
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
		Player playername = (Player) player;
		String Displayname = playername.getDisplayName();
		
		if (enabled(player)) {
			this.stickiiUsers.remove(player);
			player.sendMessage(ChatColor.AQUA + "Stickii - Disabled!");
			
			System.out.println(ChatColor.AQUA + "[Stickii] - Is now not in use by: " + Displayname + "! :(");
		} else {
			this.stickiiUsers.put(player, null);
			player.sendMessage(ChatColor.AQUA + "Stickii - Enabled!");
			player.sendMessage(ChatColor.AQUA + "Be careful! It has alot of range!");
			
			System.out.println(ChatColor.AQUA + "[Stickii] - Is now in use by: " + Displayname + "! :D");
		}
		
	}

}
