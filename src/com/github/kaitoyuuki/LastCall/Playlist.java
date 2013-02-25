package com.github.kaitoyuuki.LastCall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Arrays;


@SuppressWarnings("unused")
public class Playlist implements Playlists {
	
	private LCMain plugin;
	private String owner;
	private final String name;
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
	public void saveList() {
		// TODO save playlist to file
		
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
		// TODO decide whether or not I want to be able to change the name
		
	}
	

}
