package com.github.kaitoyuuki.LastCall;

import org.bukkit.Material;
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
}
