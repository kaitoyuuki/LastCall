package com.github.kaitoyuuki.LastCall;

import org.bukkit.Material;

import com.github.kaitoyuuki.LastCall.Metrics;

@SuppressWarnings("unused")
public class PlayMetrics {
	
	public int plays;
	
	public int thirteen;
	public int cat;
	public int blocks;
	public int chirp;
	public int far;
	public int mall;
	public int mellohi;
	public int stal;
	public int strad;
	public int ward;
	public int eleven;
	public int wait;
	
	public int getPlays(int discID) {
		if (discID == 2256) {
			plays = cat;
		}
		if (discID == 2257) {
			plays = thirteen;
		}
		if (discID == 2258) {
			plays = blocks;
		}
		if (discID == 2259) {
			plays = chirp;
		}
		if (discID == 2260) {
			plays = far;
		}
		if (discID == 2261) {
			plays = mall;
		}
		if (discID == 2262) {
			plays = mellohi;
		}
		if (discID == 2263) {
			plays = stal;
		}
		if (discID == 2264) {
			plays = strad;
		}
		if (discID == 2265) {
			plays = ward;
		}
		if (discID == 2266) {
			plays = eleven;
		}
		if (discID == 2267) {
			plays = wait;
		}
		return plays;
	}
	public void incPlays(int discID) {
		if (discID == 2256) {
			++cat;
		}
		if (discID == 2257) {
			++thirteen;
		}
		if (discID == 2258) {
			++blocks;
		}
		if (discID == 2259) {
			++chirp;
		}
		if (discID == 2260) {
			++far;
		}
		if (discID == 2261) {
			++mall;
		}
		if (discID == 2262) {
			++mellohi;
		}
		if (discID == 2263) {
			++stal;
		}
		if (discID == 2264) {
			++strad;
		}
		if (discID == 2265) {
			++ward;
		}
		if (discID == 2266) {
			++eleven;
		}
		if (discID == 2267) {
			++wait;
		}
	}
}
