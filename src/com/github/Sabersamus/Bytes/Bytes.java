package com.github.Sabersamus.Bytes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.Sabersamus.Bytes.Commands.ByteCommand;
import com.github.Sabersamus.Bytes.Listeners.PlayerJoin;

public class Bytes extends JavaPlugin 
{
	Logger log = Logger.getLogger("Minecraft");
	private final PlayerJoin playerJoin = new PlayerJoin(this);
	public FileConfiguration bytes = null;
	File bytesFile = null;
	
	
	
	@Override
	public void onDisable()
	{
		
	}
	
	@Override
	public void onEnable()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.playerJoin, this);
		this.registerCommands(this);
		loadBytes();
		Plugin bytecraft = this.getServer().getPluginManager().getPlugin("ByteCraft");
			if(bytecraft == null){
				this.log.warning("Could not find ByteCraft, try reloading it");
			}
	}

	private void registerCommands(Bytes bytes2) {
		this.getCommand("bytes").setExecutor(new ByteCommand(this));
	}
	
	public void loadBytes() {
		this.getBytes().options().copyDefaults(true);
		saveBytes();
		}

	public FileConfiguration getBytes() {
		if(bytes == null){
			reloadBytes();
		}
		return bytes;
	}

	public void reloadBytes() {
		if (bytesFile == null) {
		bytesFile = new File(getDataFolder(), "Balance.yml");
		}
		bytes = YamlConfiguration.loadConfiguration(bytesFile);

		InputStream defConfigStream = getResource("Balance.yml");
if (defConfigStream != null) {
	YamlConfiguration defConfig = YamlConfiguration
.loadConfiguration(defConfigStream);
	bytes.setDefaults(defConfig);
}
}
	

	public void saveBytes() {
		if(bytes == null || bytesFile == null){
			return;
		}
		try{
			bytes.save(bytesFile);
		
		}catch (IOException ex){
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE,
					"Error saving " + bytesFile, ex);
		}
	}
	
	/**
	 * Get the methods in the API class
	 * @return - the class API
	 */
	public API getApi()
	{
		return new API(this);
	}
}
