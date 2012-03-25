package com.github.Sabersamus.Bytes.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.Sabersamus.Bytes.Bytes;

public class PlayerJoin implements Listener
{
	public static Bytes plugin;
	public PlayerJoin(Bytes instance){
		plugin = instance;
	}
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev)
	{
		Player player = ev.getPlayer();
		String name = player.getName();
		if(!plugin.getBytes().contains(name)){
		plugin.getBytes().set(name + ".money", 1000);
		plugin.saveBytes();
		}else{
			int x = plugin.getBytes().getInt(name + ".money");
			player.sendMessage(ChatColor.AQUA + "Your balance is " + ChatColor.GOLD + x + ChatColor.AQUA +  " bytes");
		}
		
	}
}
