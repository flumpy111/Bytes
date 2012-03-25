package com.github.Sabersamus.Bytes;

import org.bukkit.entity.Player;


public class API extends Bytes
{
	public static Bytes plugin;
	public API(Bytes instance){
		plugin = instance;
	}

	/**
	 * Gets a player balance
	 * @param p - the player
	 * @return - the players money
	 */
	public int showBytes(Player p){
		return plugin.getBytes().getInt(p.getName() + ".money");
	}
	
	/**
	 * Sets a players money to a specified value
	 * @param p the player to be chosen
	 * @param value - the value, can not be less than zero, can be 0
	 */
	public void setBytes(Player p, int value){
		if(value < 0)return;
			plugin.getBytes().set(p.getName() + ".money", value);
			plugin.saveBytes();
	}
	
	/**
	 * Removes money from a player balance
	 * @param p - the player selected
	 * @param value - the money to be removed, returns if a player doesn't have that much money
	 */
	public void substractBytes(Player p, int value){
		if(plugin.getBytes().getInt(p.getName() + ".money") - value < 0)return;
			plugin.getBytes().set(p.getName() + ".money", plugin.getBytes().getInt(p.getName() + ".money") - value);
			plugin.saveBytes();
	}
	
	/**
	 * Adds money to a player balance
	 * @param p - the player
	 * @param value - the money to be added
	 */
	public void addBytes(Player p, int value){
			plugin.getBytes().set(p.getName() + ".money", plugin.getBytes().getInt(p.getName() + ".money") + value);
			plugin.saveBytes();
	}
}
