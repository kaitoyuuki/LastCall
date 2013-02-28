package com.github.kaitoyuuki.LastCall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;


@SuppressWarnings("unused")
public class Playlist implements Playlists {
	
	private LCMain plugin;
	private String owner;
	private String name;
    private List<Song> songs;
	//TODO add and initialize Song variables for each song.
	

	public Playlist(String owner, String name, List<Song> songs) {
		this.owner = owner;
		this.name = name;
		this.songs = songs;
	}



	@Override
	public void unloadPlaylist() {
		// TODO remove playlist from active lists
		
	}


	
	@Override
	public boolean saveList() {
		plugin = new LCMain();
		String filename = new String(name + ".txt");
		String text = new String();
		String songString = new String();
		File serverFolder = plugin.getDataFolder();
		File pluginFolder = new File(serverFolder, "plugins");
		File dataFolder = new File(pluginFolder, "LastCall");
		File playlistFolder = new File(dataFolder, "Playlists");
		File file = new File(playlistFolder, filename);
		if (songs.size() > 1) {
			int i;
			songString = songs.get(0).getName();
			for(i=1; i < songs.size(); i++) {
				Song song = songs.get(i);
				songString = String.format("%s%n%s", songString, song.getName());
			}
		}
		else if(songs.size() == 1){
			songString = songs.get(0).getName();
		}
		text = String.format("%s%n%s", owner, songString);
		try {
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(text);
			out.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Song> getSongs() {
		return songs;
	}

	@Override
	public Song getSong(int position) {
		try {	
		return songs.get(position);
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
		
	}

	@Override
	public String getOwner() {
		return owner;
	}

	@Override
	public void setSong(int position, Song song) {
		this.songs.add(position, song);
		
	}

	@Override
	public void setSong(Song song) {
		this.songs.add(song);
		
	}
	
	@Override
	public boolean delSong(int position) {
		try {
			this.songs.remove(position);
			return true;
		} catch(IndexOutOfBoundsException e) {
			return false;
		}
	}

	@Override
	public void setOwner(String owner) {
		this.owner = owner;
		
	}
	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public boolean play(final Player player) {
		if(songs.size() > 1) {
			plugin = new LCMain();
			final List<Song> listsongs = songs;
			Bukkit.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable() {
				@Override
				public void run() {
					int count = 0;
					do {
						final int it = count;
						Bukkit.getServer().getScheduler().runTask(plugin, new Runnable() {
							@Override
							public void run() {
								listsongs.get(it).play(player);
							}
						});
						try {
							Thread.sleep(1000 * listsongs.get(count).getLength());
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						count++;
					} while (count < listsongs.size());
				}
			});
		}
		else if(songs.size() == 1) {
			Song song = songs.get(0);
			song.play(player);
			return true;
		}
		else {
			return false;
		}
		return false;
	}



	@Override
	public boolean play(World world) {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			if(world == player.getWorld()) {
				play(player);
			}
		}
		return true;
	}



	@Override
	public boolean play(String group) {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			if(player.hasPermission("lastcall.play." + group)) {
				play(player);
			}
		}
		return true;
	}



	@Override
	public void play() {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			play(player);
		}	
	}



	@Override
	public boolean play(Integer index) {
		try {
			Song song = songs.get(index);
			song.play();
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}



	@Override
	public boolean play(Player target, Integer index) {
		if(!(target.isOnline())) {
			return false;
		}
		try {
		Song song = songs.get(index);
		song.play(target);
		return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}



	@Override
	public boolean play(World world, Integer index) {
		try {
		Song song = songs.get(index);
		song.play(world);
		return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}



	@Override
	public boolean play(String group, Integer index) {
		try {
		Song song = songs.get(index);
		song.play(group);
		return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	

}
