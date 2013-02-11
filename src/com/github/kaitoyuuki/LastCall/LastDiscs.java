package com.github.kaitoyuuki.LastCall;

import org.bukkit.Material;
//import org.bukkit.inventory.ItemStack;

public class LastDiscs {
	public String getDiscName(Material disc){
		String discName = null;
		
		return discName;
	}
	
	int getDiscID(String LastSong) {
		int discID = 0;
		if (LastSong == "13") {
			discID =  2256;
		}
		if (LastSong.equalsIgnoreCase("cat")) {
			discID =  2257;
		}
		if (LastSong.equalsIgnoreCase("blocks")) {
			discID =  2258;
		}
		if (LastSong.equalsIgnoreCase("chirp")) {
			discID =  2259;
		}
		if (LastSong.equalsIgnoreCase("far")) {
			discID =  2260;
		}
		if (LastSong.equalsIgnoreCase("mall")) {
			discID =  2261;
		}
		if (LastSong.equalsIgnoreCase("mellohi")) {
			discID =  2262;
		}
		if (LastSong.equalsIgnoreCase("stal")) {
			discID =  2263;
		}
		if (LastSong.equalsIgnoreCase("strad")) {
			discID =  2264;
		}
		if (LastSong.equalsIgnoreCase("ward")) {
			discID =  2265;
		}
		if (LastSong == "11") {
			discID =  2266;
		}
		if (LastSong.equalsIgnoreCase("wait")) {
			discID =  2267;
		}
		else {
			discID = 0;
		}
		return discID;
	}
}
