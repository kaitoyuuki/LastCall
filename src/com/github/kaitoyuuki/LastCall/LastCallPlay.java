package com.github.kaitoyuuki.LastCall;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class LastCallPlay implements CommandExecutor {

	//@SuppressWarnings("unused")

	private LCMain plugin;
	LastDiscs disc;
	PlayMetrics play;
	List<String> exempt;
	public LastCallPlay(LCMain plugin) {
		this.plugin = plugin;
		play = new PlayMetrics(plugin);
		disc = new LastDiscs();
		exempt = plugin.getConfig().getStringList("play.exempt");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("play")) {
			if (args.length > 2) {
				sender.sendMessage("§cToo many arguments!");
				return false;
			}
			if (!(sender instanceof Player)) {
				if (args.length < 1) {
					sender.sendMessage("§d/play [player] <song|playlist>");
					return false;
				}
				if (args.length == 1) {
					Song song = disc.getSong(args[0]);
					if (song == null) { 
						Playlist playlist = plugin.getPlaylist(args[0]);
						if (playlist != null) {
							playlist.play(plugin);
							sender.sendMessage("§5Now playing " + playlist.getName() + " for all players.");
							for(Song psong : playlist.getSongs()) {
								play.incPlays(psong.getID());
							}
							return true;
						}
						sender.sendMessage("§cNot a valid song/playlist!");
						return false;
					}
					else {
						song.play(plugin);
						String discName = song.getName();
						sender.sendMessage("§5Now playing " + discName);
						play.incPlays(song.getID());
						return true;
					}
				}
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("all")) {
						Playlist playlist = plugin.getPlaylist(args[1]);
						if(playlist == null) {
							Song song = disc.getSong(args[1]);
							if(song == null) {
								sender.sendMessage("§c" + args[1] + " is not a valid playlist/song.");
								return true;
							}
							else {
								song.play(plugin);
								int ID = song.getID();
								play.incPlays(ID);
								sender.sendMessage("§5Now playing " + song.getName() + " for all players");
								return true;
							}
						}
						else {
							playlist.play(plugin);
							for (Song song : playlist.getSongs()) {
								play.incPlays(song.getID());
							}
							sender.sendMessage("§5Now playing " + playlist.getName() + " for all players");
							return true;
						}
					}
					Player target = (Bukkit.getServer().getPlayer(args[0]));
					if (target == null) {
						sender.sendMessage("§c" + args[0] + " is not online!");
						return false;
					}
					else {
						Song song = disc.getSong(args[1]);
						if (song == null) {
							Playlist playlist = plugin.getPlaylist(args[1]);
							if (playlist == null) {
								sender.sendMessage("§cNot a valid song/playlist!");
								return false;
							}
							else {
								playlist.play(plugin, target);
								sender.sendMessage("§dNow playing " + playlist.getName() + " for " + target.getName());
								for (Song psong : playlist.getSongs()) {
									play.incPlays(psong.getID());
								}
								return true;
							}
						}
						else {
							String discName = song.getName();
							song.play(plugin, target);
							sender.sendMessage("§5Now playing " + discName + " for " + args[0]);
							play.incPlays(song.getID());
							return true;
						}
					}
				}
			}
			else if (sender instanceof Player) {
				Player player = (Player) sender;
				if (!(player.hasPermission("lastcall.play"))) {
					sender.sendMessage("§4You do not have permission to use /play");
					return true;
				}
				else if (player.hasPermission("lastcall.play")) {
					if (args.length == 0) {
						int discID = player.getItemInHand().getType().getId();
						String stringID = new String("" + discID);
						if ((discID > 2255) && (discID < 2268)) {
							Song song = disc.getSong(stringID);
							String discName = song.getName();
							song.play(plugin, player);
							sender.sendMessage("§5Now playing " + discName);
							play.incPlays(discID);
							return true;
						}
						else {
							sender.sendMessage("§cThat is not a music disc!");
							return false;
						}
					}
					if (args.length == 1) {
						Player target = (Player) sender;
						Song song = disc.getSong(args[0]);
						if (song == null) {
							Playlist playlist = plugin.getPlaylist(args[0]);
							if(playlist == null) {
								sender.sendMessage("§cNot a valid song/playlist!");
								return false;
							}
							else {
								playlist.play(player);
								for (Song psong : playlist.getSongs()) {
									play.incPlays(psong.getID());
								}
								sender.sendMessage("§dNow playing " + playlist.getName() + "");
								return true;
							}
						}
						else {
							String discName = song.getName();
							song.play(plugin, target);
							sender.sendMessage("§dNow playing " + discName);
							play.incPlays(song.getID());
							return true;
						}
					}
					if (args.length == 2) {
						if (args[0].equalsIgnoreCase("all")) {
							if (sender.hasPermission("lastcall.playall")) {
								Playlist playlist = plugin.getPlaylist(args[1]);
								if(playlist == null) {
									Song song = disc.getSong(args[1]);
									if(song == null) {
										sender.sendMessage("§c" + args[1] + " is not a valid playlist/song.");
										return true;
									}
									else {
										song.play(plugin);
										sender.sendMessage("");
										int ID = song.getID();
										play.incPlays(ID);
										sender.sendMessage("§5Now playing " + song.getName() + " for all players");
										return true;
									}
								}
								else {
									playlist.play(plugin);
									for (Song song : playlist.getSongs()) {
										play.incPlays(song.getID());
									}
									sender.sendMessage("§5Now playing " + playlist.getName() + " for all players");
									return true;
								}
							}
						}
						else {
							sender.sendMessage("§4You do not have permission!");
							return false;
						}
					}
					Player target = (Bukkit.getServer().getPlayer(args[0]));
					if (target == null) {
						sender.sendMessage("§c" + args[0] + " is not online!");
						return false;
					}
					else {
						if (target == player) {
							Song song = disc.getSong(args[1]);
							if (song == null) {
								Playlist playlist = plugin.getPlaylist(args[1]);
								if (playlist == null) {
									sender.sendMessage("§cNot a valid song/playlist!");
									return false;
								}
								else {
									playlist.play(target);
									sender.sendMessage("§dNow playing " + playlist.getName());
									for (Song psong : playlist.getSongs()) {
										play.incPlays(psong.getID());
									}
									return true;
								}
							}
							else {
								String discName = song.getName();
								song.play(target);
								sender.sendMessage("§dNow playing " + discName);
								play.incPlays(song.getID());
								return true;
							}
						}
						else if (target != player && player.hasPermission("lastcall.play.others")) {

							Song song = disc.getSong(args[1]);
							if (song == null) {
								Playlist playlist = plugin.getPlaylist(args[1]);
								if (playlist == null) {
									sender.sendMessage("§cNot a valid song/playlist!");
									return false;
								}
								else {
									playlist.play(plugin, target);
									sender.sendMessage("§dNow playing " + playlist.getName() + " for " + target.getName());
									for (Song psong : playlist.getSongs()) {
										play.incPlays(psong.getID());
									}
									return true;
								}
							}
							else {
								String discName = song.getName();
								song.play(plugin, target);
								sender.sendMessage("§dNow playing " + discName + " for " + args[0]);
								play.incPlays(song.getID());
								return true;
							}
						}
						else {
							sender.sendMessage("§4You do not have permission to use /play on other players");
							return true;
						}
					}
				}
			}
		}
		else {
			sender.sendMessage("How can you be a player but not a player?!");
			return true;
		}
		return false;
	}
}
