package com.github.kaitoyuuki.LastCall;



public class PlayMetrics {
	private LCMain plugin;

	public PlayMetrics(LCMain plugin) {
		this.plugin = plugin;
	}
	
	public void incPlays(int discID) {
		if (discID == 2256) {
			plugin.cat.increment();
		}
		if (discID == 2257) {
			plugin.thirteen.increment();
		}
		if (discID == 2258) {
			plugin.blocks.increment();
		}
		if (discID == 2259) {
			plugin.chirp.increment();
		}
		if (discID == 2260) {
			plugin.far.increment();
		}
		if (discID == 2261) {
			plugin.mall.increment();
		}
		if (discID == 2262) {
			plugin.mellohi.increment();
		}
		if (discID == 2263) {
			plugin.stal.increment();
		}
		if (discID == 2264) {
			plugin.strad.increment();
		}
		if (discID == 2265) {
			plugin.ward.increment();
		}
		if (discID == 2266) {
			plugin.eleven.increment();
		}
		if (discID == 2267) {
			plugin.wait.increment();
		}
	}
}
