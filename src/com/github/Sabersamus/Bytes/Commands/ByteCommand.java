package com.github.Sabersamus.Bytes.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.Sabersamus.Bytes.Bytes;

public class ByteCommand implements CommandExecutor
{
	public static Bytes plugin;
	public ByteCommand(Bytes instance)
	{
		plugin =  instance;
	}
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String aliases,
			String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("bytes")){
		Player player = null;
		if(cs instanceof Player)
		{
			player = (Player)cs;
		}
		
		if(player == null)
			{
				cs.sendMessage("You can't use this command");
			}
		else
			{
			if(args.length !=0){
			  String name = player.getName();
              int money = plugin.getBytes().getInt(name + ".money");
			String usage = String.valueOf(args[0]);
			if(args.length == 1){
				if(usage.equalsIgnoreCase("balance")){
					player.sendMessage(ChatColor.DARK_GREEN + "You have " + ChatColor.AQUA + money + ChatColor.DARK_GREEN + " Bytes");
					return true;
				}else{
					if(usage.equalsIgnoreCase("tell")){
						Bukkit.broadcastMessage((player).getDisplayName() + ChatColor.GOLD  + " has "  + ChatColor.AQUA + money + ChatColor.DARK_GREEN + " Bytes!");
						return true;
					}else{
						return false;
					}
				}
			}else{
				if(args.length == 3){
					if(usage.equalsIgnoreCase("give")){
						Player target = Bukkit.getServer().getPlayer(args[1]);
						int value = Integer.valueOf(args[2]);
						if(target != null){
						if(value > 1000000000){
							return false;
						}else{
							
							if((plugin.getBytes().getInt(name + ".money") - (value)) < 0 ){
						
								player.sendMessage(ChatColor.RED + "You dont have that much money!");
								return true;
								
							}else{
								plugin.getBytes().set(target.getName() + ".money", (plugin.getBytes().getInt(target.getName() + ".money") + (value)));
						    plugin.getBytes().set(name + ".money", (plugin.getBytes().getInt(name + ".money") - (value) ));
					     player.sendMessage(ChatColor.LIGHT_PURPLE + "You gave " + target.getDisplayName() + ChatColor.LIGHT_PURPLE + " " + ChatColor.AQUA  + value + ChatColor.LIGHT_PURPLE + " Bytes");
					     target.sendMessage(ChatColor.LIGHT_PURPLE + "You have received" + " " + ChatColor.AQUA + value + ChatColor.LIGHT_PURPLE  + " Bytes from " + player.getDisplayName());
					     plugin.saveBytes();
					     return true;
							}
						}
						}else{
							return true;
						}
					}else{
						if(usage.equalsIgnoreCase("donate")){
							Player target = Bukkit.getServer().getPlayer(args[1]);
							int value = Integer.valueOf(args[2]);
							if(target != null){
							if(value > 1000000000){
								return false;
							}else{
								
								if((plugin.getBytes().getInt(name + ".money") - (value)) < 0 ){
							
									player.sendMessage(ChatColor.RED + "You dont have that much money!");
									return true;
									
								}else{
									plugin.getBytes().set(target.getName() + ".money", (plugin.getBytes().getInt(target.getName() + ".money") + (value)));
							    plugin.getBytes().set(name + ".money", (plugin.getBytes().getInt(name + ".money") - (value) ));
						     player.sendMessage(ChatColor.LIGHT_PURPLE + "You gave " + target.getDisplayName() + ChatColor.LIGHT_PURPLE + " " + ChatColor.AQUA  + value + ChatColor.LIGHT_PURPLE + " Bytes");
						     target.sendMessage(ChatColor.LIGHT_PURPLE + "You have received" + " " + ChatColor.AQUA + value + ChatColor.LIGHT_PURPLE  + " Bytes from somebody.");
						     plugin.saveBytes();
						     return true;
								}
							
						}
					}else{
						return true;
					}
				}else{
					if(usage.equalsIgnoreCase("info")){
						//if(player.hasPermission("bytes.info")){
							Player target = Bukkit.getPlayer(args[1]);
							if(target != null){
							String tname = target.getName();
							int info = plugin.getBytes().getInt(tname + ".money");
							player.sendMessage(target.getDisplayName() + ChatColor.GOLD + " has " + ChatColor.AQUA + info + ChatColor.GOLD + " Bytes");
							return true;
							}else{
								return true;
							}
						}else{
							return true;
						}
					//}else{
						//return true;
					}
				}
			}
			}
			}
		}
		
		return false;
	}
//}
		return false;
	}
}
