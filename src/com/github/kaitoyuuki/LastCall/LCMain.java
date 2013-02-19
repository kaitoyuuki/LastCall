package com.github.kaitoyuuki.LastCall;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.kaitoyuuki.LastCall.Metrics.Graph;


public final class LCMain extends JavaPlugin {
	PlayMetrics play = new PlayMetrics();
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		getConfig();
		getCommand("play").setExecutor(new LastCallPlay(this));
		getCommand("lastcall").setExecutor(new LastCallCommands(this));
		getCommand("lc").setExecutor(new LastCallCommands(this));
		// Enabling plugin metrics
		try {
			Metrics metrics = new Metrics(this);
			Graph graph = metrics.createGraph("Percentage of discs played");
			graph.addPlotter(new Metrics.Plotter("13") {
				@Override
				public int getValue() {
					return play.getPlays(2256);
				}
			});
			graph.addPlotter(new Metrics.Plotter("cat") {
				@Override
				public int getValue() {
					return play.getPlays(2257);
				}
			});
			graph.addPlotter(new Metrics.Plotter("blocks") {
				@Override
				public int getValue() {
					return play.getPlays(2258);
				}
			});
			graph.addPlotter(new Metrics.Plotter("chirp") {
				@Override
				public int getValue() {
					return play.getPlays(2259);
				}
			});
			graph.addPlotter(new Metrics.Plotter("far") {
				@Override
				public int getValue() {
					return play.getPlays(2260);
				}
			});
			graph.addPlotter(new Metrics.Plotter("mall") {
				@Override
				public int getValue() {
					return play.getPlays(2261);
				}
			});
			graph.addPlotter(new Metrics.Plotter("mellohi") {
				@Override
				public int getValue() {
					return play.getPlays(2262);
				}
			});
			graph.addPlotter(new Metrics.Plotter("stal") {
				@Override
				public int getValue() {
					return play.getPlays(2263);
				}
			});
			graph.addPlotter(new Metrics.Plotter("strad") {
				@Override
				public int getValue() {
					return play.getPlays(2264);
				}
			});
			graph.addPlotter(new Metrics.Plotter("ward") {
				@Override
				public int getValue() {
					return play.getPlays(2265);
				}
			});
			graph.addPlotter(new Metrics.Plotter("11") {
				@Override
				public int getValue() {
					return play.getPlays(2266);
				}
			});
			graph.addPlotter(new Metrics.Plotter("wait") {
				@Override
				public int getValue() {
					return play.getPlays(2267);
				}
			});
			metrics.start();
		} catch (IOException e) {
			// Failed to submit the stats :-(
		}
	}

	@Override
	public void onDisable() {
		// TODO Insert logic to be performed when the plugin is disabled
	}

}
