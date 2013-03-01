package com.github.kaitoyuuki.LastCall;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlaylistCommands implements CommandExecutor {

	LCMain plugin;
	List<Playlist> playlists;
	LastDiscs disc;
	public PlaylistCommands(LCMain plugin) {
		this.plugin = plugin;
		playlists = plugin.getPlaylists();
		disc = new LastDiscs();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("playlist")) {
			if(args.length == 0) {
				//String[] print = new String [playlists.size()];
				String test = new String();
				//int i = 0;
				if (playlists.size() != 0) {
					for(Playlist playlist : playlists) {
						//print[i] = playlist.getName();
						test = test + playlist.getName() + " ";
						//i++;
					}
					sender.sendMessage("§dPlaylists: ");
					sender.sendMessage("");
					sender.sendMessage("     §6" + test);
					return true;
				}
				else {
					sender.sendMessage("§dThere are no playlists available.");
					return true;
				}
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help")) {
					sender.sendMessage("§5/playlist §dlist available playlists");
					sender.sendMessage("§5/playlist §9<playlist> §dshow information about the playlist");
					sender.sendMessage("§5/playlist help §9[command] §dShow help/get more detailed help");
					sender.sendMessage("§5/playlist create §9<playlist> §dCreate a new playlist named <playlist>");
					sender.sendMessage("§5/playlist delete §9<playlist> §dDelete the playlist");
					sender.sendMessage("§5/playlist modify §9<playlist> <add {song}|del {song#}> §dAdd or remove a song");
					return true;
				}
				if (args[0].equalsIgnoreCase("create")) {
					sender.sendMessage("§cYou must specify a playlist name!");
					return false;
				}
				if (args[0].equalsIgnoreCase("delete")||args[0].equalsIgnoreCase("del")) {
					sender.sendMessage("§cYou must specify a playlist name!");
					return false;
				}
				if (args[0].equalsIgnoreCase("modify")) {
					sender.sendMessage("§cYou must specify a playlist name!");
					return false;
				}
				else {
					Playlist playlist = plugin.getPlaylist(args[0]);
					if (playlist != null) {
						List<Song> songs = new ArrayList<Song>();
						songs = playlist.getSongs();
						String songInfo = new String();
						for(Song song : songs) {
							songInfo = songInfo + song.getName() + " ";
						}
						String info = String.format(
								"§9%s: §bOwner: §3%s  §bSongs: §3%s", playlist.getName(), playlist.getOwner(), songInfo);
						sender.sendMessage(info);
						return true;
					}
					else {
						return false;
					}
				}
			}
			else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("create")) {
					List<String> lists = new ArrayList<String>();
					if(disc.getSong(args[1]) != null) {
						sender.sendMessage("§5You cannot create a playlist with the same name as a song.");
						return true;
					}
					if (playlists.size() != 0) {
						for(Playlist playlist : playlists) {
							lists.add(playlist.getName());
						}
						if((lists.contains(args[1]))) {
							return false;
						}
						else {
							String owner = new String("global");
							String name = args[1];
							List<Song> songs = new ArrayList<Song>();
							if(sender instanceof Player) {
								Player player = ((Player) sender).getPlayer();
								if(player.hasPermission("lastcall.playlist.create")) {
								owner = sender.getName();
								}
								else {
									sender.sendMessage("§4You do not have permission to create playlists.");
									return false;
								}
							}
							Playlist playlist = new Playlist(owner, name, songs);
							plugin.addPlaylist(playlist);
							boolean save = playlist.saveList(plugin);
							if(save == false) {
								sender.sendMessage("§4Unable to create playlist file. Please contact the owner, or try again later.");
								return true;
							}
							sender.sendMessage("§5Playlist " + args[1] + " created. Please use /playlist modify " + args[1] + " to manage this playlist");
							return true;
						}
					}
					else {
						String owner = new String("global");
						String name = args[1];
						List<Song> songs = new ArrayList<Song>();
						if(sender instanceof Player) {
							owner = sender.getName();
						}
						Playlist playlist = new Playlist(owner, name, songs);
						plugin.addPlaylist(playlist);
						boolean save = playlist.saveList(plugin);
						if(save == false) {
							sender.sendMessage("§4Unable to create playlist file. Please contact the owner, or try again later.");
							return true;
						}
						sender.sendMessage("§5Playlist " + args[1] + " created. Please use /playlist modify " + args[1] + " to manage this playlist");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("delete")||args[0].equalsIgnoreCase("del")) {
					String owner = new String();
					if(sender instanceof Player) {
						if(!((Player) sender).getPlayer().hasPermission("lastcall.playlist.create")) {
							sender.sendMessage("§4You do not have permission to delete playlists.");
							return true;
						}
						else if (((Player) sender).getPlayer().hasPermission("lastcall.playlist.op")) {
							owner = "global";
						}
					}
					Playlist playlist = plugin.getPlaylist(args[1]);
					if(playlist == null) {
						sender.sendMessage("§5The specified playlist does not exist.");
						return true;
					}
					if(owner.equalsIgnoreCase("global") || ((Player) sender).getPlayer().getName().equalsIgnoreCase(playlist.getOwner())) {
						//TODO delete the playlist
						File LCFolder = plugin.getDataFolder();
						File folder = new File(LCFolder, "Playlists");
						File file = new File(folder, playlist.getName() + ".txt");
						if(file.exists()) {
							boolean success = file.delete();
							if(success == false) {
								sender.sendMessage("§4Unable to delete the file. Please contact the owner or try again later");
								return true;
							}
						}
						sender.sendMessage("§5Playlist successfully deleted.");
						plugin.delPlaylist(playlist);
						return true;
					}
					else {
						sender.sendMessage("§4You do not have permission to delete this playlist.");
						return true;
					}
					
				}
				if(args[0].equalsIgnoreCase("modify")) {
					sender.sendMessage("§5/playlist modify §d<playlist> <add {song}|del {song#}>");
					return true;
				}
				else {
					return false;
				}
			}
			else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("modify")) {
					sender.sendMessage("§5/playlist modify §d<playlist> <add {song}|del {song#}>");
					return true;
				}
				else {
					return false;
				}
			}
			else if(args.length == 4) {
				if(args[0].equalsIgnoreCase("modify")) {
					if(sender instanceof Player) {
						if(!(((Player) sender).getPlayer().hasPermission("lastcall.playlist.create"))) {
							sender.sendMessage("§4You do not have permission to modify playlists.");
							return true;
						}
					}
					Playlist playlist = plugin.getPlaylist(args[1]);
					if(playlist == null) {
						sender.sendMessage("§5The specified playlist does not exist.");
						return true;
					}
					else {
						String owner = new String();
						if(sender instanceof Player) {
							if(((Player) sender).getPlayer().hasPermission("lastcall.playlist.op")) {
								owner = "global";
							}
						}
						else {
							owner = "global";
						}
						if(owner.equalsIgnoreCase("global")|| ((Player) sender).getPlayer().getName().equalsIgnoreCase(playlist.getOwner())) {
							if(args[2].equalsIgnoreCase("add")) {
								Song song = disc.getSong(args[3]);
								if (song == null) {
									sender.sendMessage("§5The specified song does not exist.");
									return true;
								}
								else {
									playlist.setSong(song);
									playlist.saveList(plugin);
									sender.sendMessage("§5" + song.getName() + " was added to " + playlist.getName());
									return true;
								}
							}
							else if(args[2].equalsIgnoreCase("del")||args[2].equalsIgnoreCase("delete")) {
								int position;
								String song = new String();
								try {
									position = Integer.parseInt(args[3]) - 1;
									song = playlist.getSong(position).getName();
								} catch(Exception e) {
									sender.sendMessage("§4" + args[3] + " is not a number!");
									return true;
								}
								if (playlist.delSong(position) == false) {
									sender.sendMessage("§4" + args[3] + " is not a valid song number");
									return true;
								}
								else {
									playlist.saveList(plugin);
									sender.sendMessage("§5The song " + song + " was successfully deleted from the playlist");
									return true;
								}
							}
							else {
								sender.sendMessage("§5/playlist modify §d<playlist> <add {song}|del {song#}>");
								return true;
							}
						}
						else {
							sender.sendMessage("§4You do not have permission to modify this playlist.");
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}
