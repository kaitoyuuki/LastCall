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
		for(Song song : songs) {
			songString = String.format("%s%n%s", songString, song.getName());
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
		return songs.get(position);
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
	public void setOwner(String owner) {
		this.owner = owner;
		
	}
	@Override
	public void setName(String name) {
		this.name = name;
		
	}
	

}
