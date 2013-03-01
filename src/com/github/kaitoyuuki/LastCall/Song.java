package com.github.kaitoyuuki.LastCall;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

//@SuppressWarnings("unused")
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
	public int play(LCMain plugin) {
		List<String> exempt = plugin.getConfig().getStringList("play.exempt");
		Effect effect = Effect.RECORD_PLAY;
		for(Player target : Bukkit.getServer().getOnlinePlayers()) {
			if (!(exempt.contains(target.getName()))) {
			Location loc = target.getLocation();
			World world = target.getWorld();
			world.playEffect(loc, effect, ID);
			}
		}
		return length;
	}
	public int play(Player player) {
		Location loc = player.getLocation();
		Effect effect = Effect.RECORD_PLAY;
		player.playEffect(loc, effect, ID);
		return length;
	}
	public int play(LCMain plugin, Player player) {
		List<String> exempt = plugin.getConfig().getStringList("play.exempt");
		if(!exempt.contains(player.getName())) {
			Location loc = player.getLocation();
			Effect effect = Effect.RECORD_PLAY;
			player.playEffect(loc, effect, ID);
			return length;
		}
		else {
			return 0;
		}
	}
	public int play(LCMain plugin, String group) {
		List<String> exempt = plugin.getConfig().getStringList("play.exempt");
		Effect effect = Effect.RECORD_PLAY;
		for(Player target : Bukkit.getServer().getOnlinePlayers()) {
			if (target.hasPermission("lastcall.play." + group)) {
				if(!exempt.contains(target.getName())) {
					Location loc = target.getLocation();
					target.playEffect(loc, effect, ID);
				}
			}
		}
		return length;
	}
	public int play(LCMain plugin, World world) {
		List<String> exempt = plugin.getConfig().getStringList("play.exempt");
		Effect effect = Effect.RECORD_PLAY;
		for(Player target : Bukkit.getServer().getOnlinePlayers()) {
			if(target.getWorld() == world) {
				if(!exempt.contains(target.getName())) {
					Location loc = target.getLocation();
					world.playEffect(loc, effect, ID);
				}
			}
		}
		return length;
	}
}
