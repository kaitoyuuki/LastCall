package com.github.kaitoyuuki.LastCall;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//@SuppressWarnings("unused")
public class MDCommands implements CommandExecutor {
	private LCMain plugin;
	LastDiscs disc;
	PlayMetrics play;
	public MDCommands(LCMain plugin) {
		this.plugin = plugin;
		disc = new LastDiscs();
		play = new PlayMetrics(plugin);
	}
	
	private void countDown(final int time, final String format, final Player player) {
		player.sendMessage("§6Now performing a test of LastCall");
		Bukkit.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				int count = 0;
				do {		
					final int timeleft = time - count;
					Bukkit.getServer().getScheduler().runTask(plugin, new Runnable() {
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
					Bukkit.getServer().getScheduler().runTask(plugin, new Runnable() {
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
				Bukkit.getServer().getScheduler().runTask(plugin, new Runnable() {
					@Override
					public void run() {
						player.sendMessage("§6Countdown has ended. This has been a test");

					}
				});

			}
		});
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("testcall")) {
			String LastSong = plugin.getConfig().getString("lastcall.Song");
			Song song = disc.getSong(LastSong);
			int time = Integer.parseInt(plugin.getConfig().getString("lastcall.time"));
			String format = plugin.getConfig().getString("lastcall.Message");
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
							
							song = disc.getSong(LastSong);
						} catch(NumberFormatException e) {
							sender.sendMessage("§cNot a valid disc!");
							return false;
						}
					}
					else if (args.length == 1) {
						song = disc.getSong(args[0]);
						if (song == null) {
							try {
								time = Integer.parseInt(args[0]);
								song = disc.getSong(LastSong);
							} catch(NumberFormatException e) {
								sender.sendMessage("§cNot a valid disc!");
								return false;
							}
						}
					}
					else if (args.length == 0) {
						song = disc.getSong(LastSong);
					}
					song.play(player);
					countDown(time, format, player);
					play.incPlays(song.getID());
					return true;
				}
			}
			else {
				sender.sendMessage("§5This command can only be used by players.");
				return true;
			}
			return false;
		}
		return false;
	}

}
