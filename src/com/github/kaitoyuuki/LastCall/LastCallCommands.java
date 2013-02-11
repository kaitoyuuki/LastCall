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
			String LastSong = plugin.getConfig().getString("lcDefault");
			int LastID = 0;
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("lastcall.lastcall")) {
					if (args.length > 1) {
						return false;
					}
					if (args.length == 1) {
						LastSong = args[0];
					}
					LastID = disc.getDiscID(LastSong);
					if (LastID == 0) {
						sender.sendMessage("Not a valid disc!");
						return false;
					}
					else {
						Effect effect = Effect.RECORD_PLAY;
						for(Player target : Bukkit.getServer().getOnlinePlayers()) {
							Location loc = target.getLocation();
							target.playEffect(loc, effect, LastID);
						}
						Bukkit.getServer().broadcastMessage("§4Server is shutting down [§625s§4]");
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								Bukkit.getServer().broadcastMessage("§4Server is shutting down [§610s§4]");	
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
										int time = 5;
										do {
											Bukkit.getServer().broadcastMessage("§4Server is shutting down [§6" + time + "s§4]");	
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
												public void run() {
												}
											}, 20L);
											time--;
										} while (time > 0);
										for (Player player : Bukkit.getOnlinePlayers()) {
											player.kickPlayer("Server is shutting down");
										}
										Bukkit.shutdown();	
									}
								}, 100L);
							}
						}, 300L);
						return true;
					}
				}
				else {
					player.sendMessage("You do not have permission to do that!");
					return false;
				}
			}
			else {
				if (args.length > 1) {
					return false;
				}
				if (args.length == 1) {
					LastSong = args[0];
				}
				LastID = disc.getDiscID(LastSong);
				if (LastID == 0) {
					sender.sendMessage("Not a valid disc!");
					return false;
				}
				else {
					Effect effect = Effect.RECORD_PLAY;
					for(Player target : Bukkit.getServer().getOnlinePlayers()) {
						Location loc = target.getLocation();
						target.playEffect(loc, effect, 2263);
					}
					Bukkit.getServer().broadcastMessage("§4Server is shutting down [§625s§4]");
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							Bukkit.getServer().broadcastMessage("§4Server is shutting down [§610s§4]");	
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									int time = 5;
									do {
										Bukkit.getServer().broadcastMessage("§4Server is shutting down [§6" + time + "s§4]");	
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
											public void run() {
											}
										}, 20L);
										time--;
									} while (time > 0);
									for (Player player : Bukkit.getOnlinePlayers()) {
										player.kickPlayer("Server is shutting down");
									}
									Bukkit.shutdown();	
								}
							}, 100L);
						}
					}, 300L);
					return true;
				}
			}
		}
		return false;
	}
}


