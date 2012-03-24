package com.github.Sabersamus.Bytes;

public class API extends Bytes
{
	public static Bytes plugin;

	public int showBytes(String name){
		if(plugin.getBytes().contains(name)){
			int x = plugin.getBytes().getInt(name + ".money");
			return x;
		}else{
		return 0;
		}
	}
	
	public void setBytes(String name, int value){
		if(plugin.getBytes().contains(name)){
			plugin.getBytes().set(name + ".money", value);
			plugin.saveBytes();
		}
	}
	
	public void substractBytes(String name, int value){
		if(plugin.getBytes().contains(name)){
			plugin.getBytes().set(name + ".money", plugin.getBytes().getInt(name + ".money") - value);
			plugin.saveBytes();
		}
	}
	
	public void addBytes(String name, int value){
		if(plugin.getBytes().contains(name)){
			plugin.getBytes().set(name + ".money", plugin.getBytes().getInt(name + ".money") + value);
			plugin.saveBytes();
		}
	}
}
