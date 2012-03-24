package com.github.Sabersamus.Bytes.Listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.Sabersamus.Bytes.Bytes;

public class ButtonListener implements Listener
{
	public static Bytes plugin;
	public ButtonListener(Bytes instance){
		plugin = instance;
	}
	
    final HashMap<Player, Long> lastClicked = new HashMap<Player, Long>();
    final long MIN_DELAY = 1500;
	
	@EventHandler
	public void onClick(PlayerInteractEvent ev){
		Action action = ev.getAction();
		Player player = ev.getPlayer();
	//	int time = 1;
		Block block = ev.getClickedBlock();
//		BlockFace block2 = block.getFace(block.);
        Long lastClick = lastClicked.get(ev.getPlayer());
        lastClicked.put(ev.getPlayer(), System.currentTimeMillis());
        if (lastClick != null && System.currentTimeMillis() - lastClick.longValue() < MIN_DELAY) {
        return;// stops here if the last click was less than DELAY milliseconds ago
        }
		if(action.equals(Action.RIGHT_CLICK_BLOCK)){
			if(block.getType() == Material.STONE){
				if(block.getRelative(0, -1, 0).getType() == Material.SPONGE){
					if(block.isBlockPowered()){
						if(plugin.getBytes().getInt(player.getName() + ".money") >= 15000){
							if(System.currentTimeMillis() < 10){
								
							}else{
								
							Bukkit.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "You have been hugged by " + player.getDisplayName());
							plugin.getBytes().set(player.getName() + ".money", (plugin.getBytes().getInt(player.getName() + ".money") - 15000));
							player.sendMessage(ChatColor.AQUA + "15000 " + ChatColor.GOLD + "bytes " + ChatColor.AQUA + " has been subtracted from your balance");
							plugin.saveBytes();
		  	  	 			int time = (int) System.currentTimeMillis();
		  	 				int seconds = ++time;
		  	 				if(seconds > 10){
		  	 						ev.setCancelled(true);
		  	 						
		  	 				}
							}
						}else{
							player.sendMessage(ChatColor.RED + "You dont have enough money to do this");
						}
					}
				}
			}
		}
	}
}
