package com.github.kaitoyuuki.LastCall;

import org.bukkit.Material;
//import org.bukkit.inventory.ItemStack;

public class LastDiscs {
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
