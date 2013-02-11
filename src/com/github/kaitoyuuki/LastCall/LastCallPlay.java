package com.github.kaitoyuuki.LastCall;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//import com.github.kaitoyuuki.LastCall.LastDiscs;

public class LastCallPlay extends com.github.kaitoyuuki.LastCall.LastDiscs implements CommandExecutor {

	@SuppressWarnings("unused")
	private LCMain plugin;

	public LastCallPlay(LCMain plugin) {
		this.plugin = plugin;
	}
	public void playDisc(final int discID, final String player) {

	}
	LastDiscs disc = new LastDiscs();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("play")) {
			if (args.length > 2) {
				sender.sendMessage("Too many arguments!");
				return false;
			}
			if (!(sender instanceof Player)) {
				if (args.length < 1) {
					sender.sendMessage("/play [player] <discID>");
					return false;
				}
				if (args.length == 1) {
					int discID = 0;
					//String disc = args[0];
					// cat 2257
					String PlaySong = args[0];
					discID = disc.getDiscID(PlaySong);
					if (discID != 0) { 
						Effect effect = Effect.RECORD_PLAY;
						for(Player target : Bukkit.getServer().getOnlinePlayers()) {
							Location loc = target.getLocation();
							target.playEffect(loc, effect, discID);	
						}
						sender.sendMessage("Now playing " + PlaySong + " for all users");

						return true;
					}
					// not a valid disc
					else {
						sender.sendMessage("Not a valid disc!");
						return false;
					}
				}
				if (args.length == 2) {
					Player target = (Bukkit.getServer().getPlayer(args[0]));
					if (target == null) {
						sender.sendMessage(args[0] + " is not online!");
						return false;
					}
					else {
						//String disc = args[1];
						// cat 2257
						if (args[1].equalsIgnoreCase("cat")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2257);
							sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
							return true;
						}
						// 13 2256
						if (args[1].equals("13")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2256);
							sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
							return true;
						}
						// blocks 2258
						if (args[1].equalsIgnoreCase("blocks")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2258);
							sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
							return true;
						}
						// chirp 2259
						if (args[1].equalsIgnoreCase("chirp")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2259);
							sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
							return true;
						}
						// far 2260
						if (args[1].equalsIgnoreCase("far")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2260);
							sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
							return true;
						}
						// mall 2261
						if (args[1].equalsIgnoreCase("mall")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2261);
							sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
							return true;
						}
						// mellohi 2262
						if (args[1].equalsIgnoreCase("mellohi")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2262);
							sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
							return true;
						}							
						// stal 2263
						if (args[1].equalsIgnoreCase("stal")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2263);
							sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
							return true;
						}
						// strad 2264
						if (args[1].equalsIgnoreCase("strad")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2264);
							sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
							return true;
						}
						// ward 2265
						if (args[1].equalsIgnoreCase("ward")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2265);
							sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
							return true;
						}
						// 11 2266
						if (args[1].equals("11")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2267);
							sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
							return true;
						}
						// wait 2267
						if (args[1].equalsIgnoreCase("wait")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2268);
							sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
							return true;
						}
						else {
							sender.sendMessage("Not a valid disc!");
							return false;
						}
					}
				}
			}
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("lastcall.play")) {
					if (args.length == 0) {
						int discID = player.getItemInHand().getType().getId();
						if ((discID > 2255) && (discID < 2268)) {
							Location loc = player.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							Material disc = player.getItemInHand().getType();
							player.playEffect(loc, effect, discID);
							sender.sendMessage("Now playing " + disc);
							return true;
						}
						else {
							sender.sendMessage("That is not a music disc!");
							return false;
						}
					}
					if (args.length == 1) {
						Player target = (Player) sender;
						//String disc = args[1];
						// cat 2257
						if (args[0].equalsIgnoreCase("cat")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2257);
							sender.sendMessage("Now playing " + args[0]);
							return true;
						}
						// 13 2256
						if (args[0].equals("13")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2256);
							sender.sendMessage("Now playing " + args[0]);
							return true;
						}
						// blocks 2258
						if (args[0].equalsIgnoreCase("blocks")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2258);
							sender.sendMessage("Now playing " + args[0]);
							return true;
						}
						// chirp 2259
						if (args[0].equalsIgnoreCase("chirp")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2259);
							sender.sendMessage("Now playing " + args[0]);
							return true;
						}
						// far 2260
						if (args[0].equalsIgnoreCase("far")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2260);
							sender.sendMessage("Now playing " + args[0]);
							return true;
						}
						// mall 2261
						if (args[0].equalsIgnoreCase("mall")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2261);
							sender.sendMessage("Now playing " + args[0]);
							return true;
						}
						// mellohi 2262
						if (args[0].equalsIgnoreCase("mellohi")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2262);
							sender.sendMessage("Now playing " + args[0]);
							return true;
						}							
						// stal 2263
						if (args[0].equalsIgnoreCase("stal")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2263);
							sender.sendMessage("Now playing " + args[0]);
							return true;
						}
						// strad 2264
						if (args[0].equalsIgnoreCase("strad")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2264);
							sender.sendMessage("Now playing " + args[0]);
							return true;
						}
						// ward 2265
						if (args[0].equalsIgnoreCase("ward")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2265);
							sender.sendMessage("Now playing " + args[0]);
							return true;
						}
						// 11 2266
						if (args[0].equals("11")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2267);
							sender.sendMessage("Now playing " + args[0]);
							return true;
						}
						// wait 2267
						if (args[0].equalsIgnoreCase("wait")) {
							Location loc = target.getLocation();
							Effect effect = Effect.RECORD_PLAY;
							target.playEffect(loc, effect, 2268);
							sender.sendMessage("Now playing " + args[0]);
							return true;
						}
						// not a valid disc
						else {
							sender.sendMessage("Not a valid disc!");
							return false;
						}
					}
					if (args.length == 2) {
						Player target = (Bukkit.getServer().getPlayer(args[0]));
						if (target == null) {
							sender.sendMessage(args[0] + " is not online!");
							return false;
						}
						else {
							if (target != player && player.hasPermission("lastcall.play.others") || target == player) {
								//String disc = args[1];
								// cat 2257
								if (args[1].equalsIgnoreCase("cat")) {
									Location loc = target.getLocation();
									Effect effect = Effect.RECORD_PLAY;
									target.playEffect(loc, effect, 2257);
									sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
									return true;
								}
								// 13 2256
								if (args[1].equals("13")) {
									Location loc = target.getLocation();
									Effect effect = Effect.RECORD_PLAY;
									target.playEffect(loc, effect, 2256);
									sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
									return true;
								}
								// blocks 2258
								if (args[1].equalsIgnoreCase("blocks")) {
									Location loc = target.getLocation();
									Effect effect = Effect.RECORD_PLAY;
									target.playEffect(loc, effect, 2258);
									sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
									return true;
								}
								// chirp 2259
								if (args[1].equalsIgnoreCase("chirp")) {
									Location loc = target.getLocation();
									Effect effect = Effect.RECORD_PLAY;
									target.playEffect(loc, effect, 2259);
									sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
									return true;
								}
								// far 2260
								if (args[1].equalsIgnoreCase("far")) {
									Location loc = target.getLocation();
									Effect effect = Effect.RECORD_PLAY;
									target.playEffect(loc, effect, 2260);
									sender.sendMessage("Now playing" + args[1] + " for " + args[0]);
									return true;
								}
								// mall 2261
								if (args[1].equalsIgnoreCase("mall")) {
									Location loc = target.getLocation();
									Effect effect = Effect.RECORD_PLAY;
									target.playEffect(loc, effect, 2261);
									sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
									return true;
								}
								// mellohi 2262
								if (args[1].equalsIgnoreCase("mellohi")) {
									Location loc = target.getLocation();
									Effect effect = Effect.RECORD_PLAY;
									target.playEffect(loc, effect, 2262);
									sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
									return true;
								}							
								// stal 2263
								if (args[1].equalsIgnoreCase("stal")) {
									Location loc = target.getLocation();
									Effect effect = Effect.RECORD_PLAY;
									target.playEffect(loc, effect, 2263);
									sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
									return true;
								}
								// strad 2264
								if (args[1].equalsIgnoreCase("strad")) {
									Location loc = target.getLocation();
									Effect effect = Effect.RECORD_PLAY;
									target.playEffect(loc, effect, 2264);
									sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
									return true;
								}
								// ward 2265
								if (args[1].equalsIgnoreCase("ward")) {
									Location loc = target.getLocation();
									Effect effect = Effect.RECORD_PLAY;
									target.playEffect(loc, effect, 2265);
									sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
									return true;
								}
								// 11 2266
								if (args[1].equals("11")) {
									Location loc = target.getLocation();
									Effect effect = Effect.RECORD_PLAY;
									target.playEffect(loc, effect, 2267);
									sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
									return true;
								}
								// wait 2267
								if (args[1].equalsIgnoreCase("wait")) {
									Location loc = target.getLocation();
									Effect effect = Effect.RECORD_PLAY;
									target.playEffect(loc, effect, 2268);
									sender.sendMessage("Now playing " + args[1] + " for " + args[0]);
									return true;
								}
								else {
									sender.sendMessage("Not a valid disc!");
									return false;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
}
