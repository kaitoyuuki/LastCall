package com.github.kaitoyuuki.LastCall;

import org.bukkit.Material;
//import org.bukkit.inventory.ItemStack;

public class LastDiscs {
	
	
	//Song data
	//null song for initializing song variables or stopping playback, if I can figure out how to do that
	final static Song stop = new Song("stop", 0, Material.AIR, 0);
	
	final static Song thirteen = new Song("13", 2256, Material.GOLD_RECORD, 178);
	final static Song cat = new Song("cat", 2257, Material.GREEN_RECORD, 185);
	final static Song blocks = new Song("blocks", 2258, Material.RECORD_3, 345);
	final static Song chirp = new Song("chirp", 2259, Material.RECORD_4, 185);
	final static Song far = new Song("far", 2260, Material.RECORD_5, 174);
	final static Song mall = new Song("mall", 2261, Material.RECORD_6, 197);
	final static Song mellohi = new Song("mellohi", 2262, Material.RECORD_7, 96);
	final static Song stal = new Song("stal", 2263, Material.RECORD_8, 150);
	final static Song strad = new Song("strad", 2264, Material.RECORD_9, 188);
	final static Song ward = new Song("ward", 2265, Material.RECORD_10, 251);
	final static Song eleven = new Song("11", 2266, Material.RECORD_11, 71);
	final static Song wait = new Song("wait", 2267, Material.RECORD_12, 238);
	

	public Song getSong(String name) {
		int discID = 0;
		try {
			discID = Integer.parseInt(name);
		} catch(NumberFormatException e) {
			discID = 0;
		}
		
		if (discID == 13||discID == 2256) {
			return thirteen;
		}
		else if (name.equalsIgnoreCase("cat")||discID == 2257) {
			return cat;
		}
		else if (name.equalsIgnoreCase("blocks")||discID == 2258) {
			return blocks;
		}
		else if (name.equalsIgnoreCase("chirp")||discID == 2259) {
			return chirp;
		}
		else if (name.equalsIgnoreCase("far")||discID == 2260) {
			return far;
		}
		else if (name.equalsIgnoreCase("mall")||discID == 2261) {
			return mall;
		}
		else if (name.equalsIgnoreCase("mellohi")||discID == 2262) {
			return mellohi;
		}
		else if (name.equalsIgnoreCase("stal")||discID == 2263) {
			return stal;
		}
		else if (name.equalsIgnoreCase("strad")||discID == 2264) {
			return strad;
		}
		else if (name.equalsIgnoreCase("ward")||discID == 2265) {
			return ward;
		}
		else if (discID == 11||discID == 2266) {
			return eleven;
		}
		else if (name.equalsIgnoreCase("wait")||discID == 2267) {
			return wait;
		}
		else if (name.equalsIgnoreCase("stop")) {
			return stop;
		}
		else {
			return null;
		}
	}
}
