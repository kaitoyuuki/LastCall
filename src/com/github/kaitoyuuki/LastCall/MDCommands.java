package com.github.kaitoyuuki.LastCall;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.kaitoyuuki.LastCall.*;

@SuppressWarnings("unused")
public class MDCommands implements CommandExecutor {
	private LCMain plugin;
	LastDiscs disc;
	PlayMetrics play;
	public MDCommands(LCMain plugin) {
		this.plugin = plugin;
		disc = new LastDiscs();
		play = new PlayMetrics(plugin);
	}	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("testcall")) {
			String LastSong = plugin.getConfig().getString("lastcall.Song");
			int time = Integer.parseInt(plugin.getConfig().getString("lastcall.time"));
			String format = plugin.getConfig().getString("lastcall.Message");
			int LastID = 0;
			if (sender instanceof Player) {
				if (sender.hasPermission("lastcall.testcall")) {
					Player player = (Player) sender;
					if (args.length > 2) {
						return false;
					}
					if (args.length < 0) {
						return false;
					}
					else if (args.length == 2) {
						LastSong = args[1];
						try {
							time = Integer.parseInt(args[0]);
							LastID = disc.getDiscID(LastSong);
						} catch(NumberFormatException e) {
							sender.sendMessage("Not a valid disc!");
							return false;
						}
					}
					else if (args.length == 1) {
						LastID = disc.getDiscID(args[0]);
						if (LastID == 0) {
							try {
								time = Integer.parseInt(args[0]);
								LastID = disc.getDiscID(LastSong);
							} catch(NumberFormatException e) {
								sender.sendMessage("Not a valid disc!");
								return false;
							}
						}
					}
					else if (args.length == 0) {
						LastID = disc.getDiscID(LastSong);
					}
					Effect effect = Effect.RECORD_PLAY;
					Location loc = player.getLocation();
					player.playEffect(loc, effect, LastID);
					countDown(time, format, player);
					play.incPlays(LastID);
					return true;
				}
			}
			else {

				return true;
			}
			return false;
		}
		return false;
	}
	private void countDown(final int time, final String format, final Player player) {
		player.sendMessage("Now performing a test of LastCall");
		plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				int count = 0;
				do {		
					final int timeleft = time - count;
					plugin.getServer().getScheduler().runTask(plugin, new Runnable() {
						@Override
						public void run() {
							String sTime = Integer.toString(timeleft);
							String message = format.replaceAll("<time>", sTime);
							player.sendMessage(message);
						}
					});
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					count = count + 10;
				} while (time - count > 19);
				do {
					final int timeleft = time - count;
					plugin.getServer().getScheduler().runTask(plugin, new Runnable() {
						@Override
						public void run() {
							String sTime = Integer.toString(timeleft);
							String message = format.replaceAll("<time>", sTime);
							player.sendMessage(message);
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					count++;
				} while (time - count >= 1);
				plugin.getServer().getScheduler().runTask(plugin, new Runnable() {
					@Override
					public void run() {
						player.sendMessage("§6Countdown has ended. This has been a test");

					}
				});

			}
		});
	}
}
