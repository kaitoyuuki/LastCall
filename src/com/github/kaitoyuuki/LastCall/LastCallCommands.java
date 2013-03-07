package com.github.kaitoyuuki.LastCall;


import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class LastCallCommands implements CommandExecutor {

	private LCMain plugin;

	LastDiscs disc;
	PlayMetrics play;
	public LastCallCommands(LCMain plugin) {
		this.plugin = plugin;
		disc = new LastDiscs();
		play = new PlayMetrics(plugin);
	}

	public void countDown(final int time, final String format) {
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
							Bukkit.getServer().broadcastMessage(message);
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
							Bukkit.getServer().broadcastMessage(message);
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
						for (Player target : Bukkit.getOnlinePlayers()) {
							target.kickPlayer("Server is shutting down");
						}
						Bukkit.shutdown();
					}
				});

			}
		});
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("lc")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length > 1) {
					sender.sendMessage("§4Too many arguments!");
					return false;
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reload")) {
						if (player.hasPermission("lastcall.reload")) {
							plugin.reloadConfig();
							plugin.onDisable();
							plugin.onEnable();
							sender.sendMessage("§5LastCall has been reloaded");
							return true;
						}
						else {
							sender.sendMessage("§4You do not have permission!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("exempt")) {
						String name = sender.getName();
						List<String> names = plugin.getConfig().getStringList("play.exempt");
						if (names.contains(name)) {
							names.remove(name);
							plugin.getConfig().set("play.exempt", names);
							plugin.saveConfig();
							sender.sendMessage("§dYou will now hear music played by others.");
							return true;
						}
						else {
							names.add(name);
							plugin.getConfig().set("play.exempt", names);
							plugin.saveConfig();
							sender.sendMessage("§dYou will no longer hear music played by others.");
							return true;
						}
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
			else {
				if (args.length > 1) {
					sender.sendMessage("§cToo many arguments!");
					return false;
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reload")) {
						plugin.getConfig();
						sender.sendMessage("§5LastCall has been reloaded");
						return true;
					}
					else if(args[0].equalsIgnoreCase("exempt")) {
						sender.sendMessage("§dSilly console, you can't use this command!");
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("lastcall")) {
			String LastSong = plugin.getConfig().getString("lastcall.Song");
			Song song = disc.getSong(LastSong);
			int time = Integer.parseInt(plugin.getConfig().getString("lastcall.time"));
			String format = plugin.getConfig().getString("lastcall.Message");
			char code = '&';
			format = ChatColor.translateAlternateColorCodes(code, format);
			if (sender instanceof Player) {
				if (sender.hasPermission("lastcall.lastcall")) {
					if (args.length > 2) {
						sender.sendMessage("§cToo many arguments!");
						return false;
					}
					if (args.length < 0) {
						return false;
					}
					else if (args.length == 2) {
						song = disc.getSong(args[1]);
						if(song == null) {
							sender.sendMessage("§c" + args[1] + " is not a valid song!");
							return true;
						}
						try {
							time = Integer.parseInt(args[0]);
						} catch(NumberFormatException e) {
							sender.sendMessage("§c" + args[0] + " is not a valid time!");
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
								sender.sendMessage("§c" + args[0] + " is not a valid song!");
								return false;
							}
						}
					}
					else if (args.length == 0) {
						song = disc.getSong(LastSong);
					}
					play.incPlays(song.getID());
					song.play();
					countDown(time, format);
					return true;
				}
			}
			else {
				if (args.length > 2) {
					sender.sendMessage("§cToo many arguments!");
					return false;
				}
				if (args.length < 0) {
					return false;
				}
				else if (args.length == 2) {
					song = disc.getSong(args[1]);
					if(song == null) {
						sender.sendMessage("§c" + args[1] + " is not a valid song!");
						return true;
					}
					try {
						time = Integer.parseInt(args[0]);
					} catch(NumberFormatException e) {
						sender.sendMessage("§c" + args[0] + " is not a valid time!");
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
							sender.sendMessage("§c" + args[0] + " is not a valid song!");
							return false;
						}
					}
				}
				else if (args.length == 0) {
					song = disc.getSong(LastSong);
				}
				play.incPlays(song.getID());
				song.play();
				countDown(time, format);
				return true;
			}
		}
		return false;
	}
}


