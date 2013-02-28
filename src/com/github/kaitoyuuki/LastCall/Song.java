package com.github.kaitoyuuki.LastCall;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
public class Song {
	private final String name;
	private final int ID;
	private final Material material;
	private final int length;
	
	public Song(String name, int ID, Material material, int length) {
		this.name = name;
		this.ID = ID;
		this.material = material;
		this.length = length;
	}
	
	public String getName() {
		return name;
	}
	public int getID() {
		return ID;
	}
	public Material getMaterial() {
		return material;
	}
	public int getLength() {
		return length;
	}
	
	// play methods.
	public int play() {
		Effect effect = Effect.RECORD_PLAY;
		for(Player target : Bukkit.getServer().getOnlinePlayers()) {
			Location loc = target.getLocation();
			World world = target.getWorld();
			world.playEffect(loc, effect, ID);	
		}
		return length;
	}
	public int play(Player player) {
		Location loc = player.getLocation();
		Effect effect = Effect.RECORD_PLAY;
		player.playEffect(loc, effect, ID);
		return length;
	}
	public int play(String group) {
		Effect effect = Effect.RECORD_PLAY;
		for(Player target : Bukkit.getServer().getOnlinePlayers()) {
			if (target.hasPermission("lastcall.play." + group)) {
			Location loc = target.getLocation();
			target.playEffect(loc, effect, ID);
			}
		}
		return length;
	}
	public int play(World world) {
		Effect effect = Effect.RECORD_PLAY;
		for(Player target : Bukkit.getServer().getOnlinePlayers()) {
			if(target.getWorld() == world) {
				Location loc = target.getLocation();
				world.playEffect(loc, effect, ID);
			}
		}
		return length;
	}
}
