package com.github.kaitoyuuki.LastCall;


import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class LastCallCommands implements CommandExecutor {

	private LCMain plugin;

	public LastCallCommands(LCMain plugin) {
		this.plugin = plugin;
	}
	LastDiscs disc = new LastDiscs();
	public void countDown(final int time, final String format) {
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
					plugin.getServer().getScheduler().runTask(plugin, new Runnable() {
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
				plugin.getServer().getScheduler().runTask(plugin, new Runnable() {
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
				if (player.hasPermission("lastcall.lc")) {
					if (args.length > 1) {
						sender.sendMessage("Too many arguments!");
						return false;
					}
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("reload")) {
							plugin.getConfig();
							sender.sendMessage("LastCall has been reloaded");
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
				else {
					sender.sendMessage("You do not have permission!");
					return false;
				}
			}
			else {
				if (args.length > 1) {
					sender.sendMessage("Too many arguments!");
					return false;
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reload")) {
						plugin.getConfig();
						sender.sendMessage("LastCall has been reloaded");
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
			int time = Integer.parseInt(plugin.getConfig().getString("lastcall.time"));
			String format = plugin.getConfig().getString("lastcall.Message");
			int LastID = 0;
			if (sender instanceof Player) {
				if (args.length > 2) {
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
				for(Player target : Bukkit.getServer().getOnlinePlayers()) {
					Location loc = target.getLocation();
					target.playEffect(loc, effect, LastID);
				}
				countDown(time, format);
				return true;
			}
			else {
				if (args.length > 2) {
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
					time = Integer.parseInt(plugin.getConfig().getString("lastcall.time"));
				}
				Effect effect = Effect.RECORD_PLAY;
				for(Player target : Bukkit.getServer().getOnlinePlayers()) {
					Location loc = target.getLocation();
					target.playEffect(loc, effect, LastID);
				}
				countDown(time, format);
				return true;
			}
		}
		return false;
	}
}


