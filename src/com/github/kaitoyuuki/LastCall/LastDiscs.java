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
		if (LastSong == "13"|LastSong == "2256") {
			discID =  2256;
			return discID;
		}
		if (LastSong.equalsIgnoreCase("cat")||LastSong == "2257") {
			discID =  2257;
			return discID;
		}
		if (LastSong.equalsIgnoreCase("blocks")||LastSong == "2258") {
			discID =  2258;
			return discID;
		}
		if (LastSong.equalsIgnoreCase("chirp")||LastSong == "2259") {
			discID =  2259;
			return discID;
		}
		if (LastSong.equalsIgnoreCase("far")||LastSong == "2260") {
			discID =  2260;
			return discID;
		}
		if (LastSong.equalsIgnoreCase("mall")||LastSong == "2261") {
			discID =  2261;
			return discID;
		}
		if (LastSong.equalsIgnoreCase("mellohi")||LastSong == "2262") {
			discID =  2262;
			return discID;
		}
		if (LastSong.equalsIgnoreCase("stal")||LastSong == "2263") {
			discID =  2263;
			return discID;
		}
		if (LastSong.equalsIgnoreCase("strad")||LastSong == "2264") {
			discID =  2264;
			return discID;
		}
		if (LastSong.equalsIgnoreCase("ward")||LastSong == "2265") {
			discID =  2265;
			return discID;
		}
		if (LastSong == "11"||LastSong == "2266") {
			discID =  2266;
			return discID;
		}
		if (LastSong.equalsIgnoreCase("wait")||LastSong == "2267") {
			discID =  2267;
			return discID;
		}
		else {
			discID = 0;
			return discID;
		}
	}
}
