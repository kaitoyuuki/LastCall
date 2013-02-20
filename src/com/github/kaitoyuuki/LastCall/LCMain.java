package com.github.kaitoyuuki.LastCall;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import com.turt2live.metrics.LastCall.EMetrics;
import com.turt2live.metrics.LastCall.tracker.BasicTracker;


public final class LCMain extends JavaPlugin {
	public BasicTracker cat, thirteen, blocks, chirp, far, mall, mellohi, stal, strad, ward, eleven, wait;
	
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		getConfig();
		getCommand("play").setExecutor(new LastCallPlay(this));
		getCommand("lastcall").setExecutor(new LastCallCommands(this));
		getCommand("lc").setExecutor(new LastCallCommands(this));
		// Enabling MetricsExtension
		try{
			String graph = "Percentage of discs played";
			EMetrics metrics = new EMetrics(this);
			
			thirteen = EMetrics.createBasicTracker(graph, "13");
			cat = EMetrics.createBasicTracker(graph, "cat");
			blocks = EMetrics.createBasicTracker(graph, "blocks");
			chirp = EMetrics.createBasicTracker(graph, "chirp");
			far = EMetrics.createBasicTracker(graph, "far");
			mall = EMetrics.createBasicTracker(graph, "mall");
			mellohi = EMetrics.createBasicTracker(graph, "mellohi");
			stal = EMetrics.createBasicTracker(graph, "stal");
			strad = EMetrics.createBasicTracker(graph, "strad");
			ward = EMetrics.createBasicTracker(graph, "ward");
			eleven = EMetrics.createBasicTracker(graph, "eleven");
			wait = EMetrics.createBasicTracker(graph, "wait");
			
			metrics.addTracker(thirteen);
			metrics.addTracker(cat);
			metrics.addTracker(blocks);
			metrics.addTracker(chirp);
			metrics.addTracker(far);
			metrics.addTracker(mall);
			metrics.addTracker(mellohi);
			metrics.addTracker(stal);
			metrics.addTracker(strad);
			metrics.addTracker(ward);
			metrics.addTracker(eleven);
			metrics.addTracker(wait);
			metrics.startMetrics();
			
		}catch(IOException e){
			// you broke something, didn't you?
		}
	}

	@Override
	public void onDisable() {
		// TODO Insert logic to be performed when the plugin is disabled
	}

}
