package com.github.kaitoyuuki.LastCall;

import org.bukkit.Material;
//import org.bukkit.inventory.ItemStack;

public class LastDiscs {
	
	//Song data
	Song thirteen = new Song("13", 2256, Material.GOLD_RECORD, 178);
	Song cat = new Song("cat", 2257, Material.GREEN_RECORD, 185);
	Song blocks = new Song("blocks", 2258, Material.RECORD_3, 345);
	Song chirp = new Song("chirp", 2259, Material.RECORD_4, 185);
	Song far = new Song("far", 2260, Material.RECORD_5, 174);
	Song mall = new Song("mall", 2261, Material.RECORD_6, 197);
	Song mellohi = new Song("mellohi", 2262, Material.RECORD_7, 96);
	Song stal = new Song("stal", 2263, Material.RECORD_8, 150);
	Song strad = new Song("strad", 2264, Material.RECORD_9, 188);
	Song ward = new Song("ward", 2265, Material.RECORD_10, 251);
	Song eleven = new Song("11", 2266, Material.RECORD_11, 71);
	Song wait = new Song("wait", 2267, Material.RECORD_12, 238);
	
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
		else {
			return null;
		}
	}
	public String getDiscName(Material disc){
		String discName = null;
		if (disc == Material.GREEN_RECORD) {
			discName = "cat";
			return discName;
		}
		if (disc == Material.GOLD_RECORD) {
			discName = "13";
			return discName;
		}
		if (disc == Material.RECORD_3) {
			discName = "blocks";
			return discName;
		}
		if (disc == Material.RECORD_4) {
			discName = "chirp";
			return discName;
		}
		if (disc == Material.RECORD_5) {
			discName = "far";
			return discName;
		}
		if (disc == Material.RECORD_6) {
			discName = "mall";
			return discName;
		}
		if (disc == Material.RECORD_7) {
			discName = "mellohi";
			return discName;
		}
		if (disc == Material.RECORD_8) {
			discName = "stal";
			return discName;
		}
		if (disc == Material.RECORD_9) {
			discName = "strad";
			return discName;
		}
		if (disc == Material.RECORD_10) {
			discName = "ward";
			return discName;
		}
		if (disc == Material.RECORD_11) {
			discName = "11";
			return discName;
		}
		if (disc == Material.RECORD_12) {
			discName = "wait";
			return discName;
		}
		else {
			discName = null;
			return discName;
		}
	}
	
	int getDiscID(String LastSong) {
		int discID = 0;
		try {
			discID = Integer.parseInt(LastSong);
		} catch(NumberFormatException e) {
			discID = 0;
		}
		
		if (discID == 13||discID == 2256) {
			discID =  2256;
			return discID;
		}
		else if (LastSong.equalsIgnoreCase("cat")||discID == 2257) {
			discID =  2257;
			return discID;
		}
		else if (LastSong.equalsIgnoreCase("blocks")||discID == 2258) {
			discID =  2258;
			return discID;
		}
		else if (LastSong.equalsIgnoreCase("chirp")||discID == 2259) {
			discID =  2259;
			return discID;
		}
		else if (LastSong.equalsIgnoreCase("far")||discID == 2260) {
			discID =  2260;
			return discID;
		}
		else if (LastSong.equalsIgnoreCase("mall")||discID == 2261) {
			discID =  2261;
			return discID;
		}
		else if (LastSong.equalsIgnoreCase("mellohi")||discID == 2262) {
			discID =  2262;
			return discID;
		}
		else if (LastSong.equalsIgnoreCase("stal")||discID == 2263) {
			discID =  2263;
			return discID;
		}
		else if (LastSong.equalsIgnoreCase("strad")||discID == 2264) {
			discID =  2264;
			return discID;
		}
		else if (LastSong.equalsIgnoreCase("ward")||discID == 2265) {
			discID =  2265;
			return discID;
		}
		else if (discID == 11||discID == 2266) {
			discID =  2266;
			return discID;
		}
		else if (LastSong.equalsIgnoreCase("wait")||discID == 2267) {
			discID =  2267;
			return discID;
		}
		else {
			discID = 0;
			return discID;
		}
	}
}
