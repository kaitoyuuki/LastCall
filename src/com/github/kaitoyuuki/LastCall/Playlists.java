package com.github.kaitoyuuki.LastCall;


import java.io.File;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("unused")
public interface Playlists {


	/**
	 * removes the playlist from the currently available playlists.
	 */
	public void unloadPlaylist();
	/**
	 * Saves the playlist to a file. If the file does not exist, it will create it.
	 */
	public void saveList();
	/**
	 * stores the name of the playlist in the provided String object
	 * @return the String object provided
	 */
	public String getName();
	
	/**
	 * Stores the songs for the playlist in the provided Song List object
	 * @return the Song List object 
	 */
	public List<Song> getSongs();
	/**
	 * returns the song located at the given position in the playlist
	 * @param position index of the song to be retrieved
	 * @return song at that position, or <code>null</code> if the position is empty
	 */
	public Song getSong(int position);
	/**
	 * stores the owner of the playlist in the provided String object
	 * @return The String object provided
	 */
	public String getOwner();
	/**
	 * sets the song at the given position in the playlist
	 * @param position location to overwrite
	 * @param song song to set at the position
	 */
	public void setSong(int position, Song song);
	/**
	 * Adds the given song to the end of the playlist
	 * @param song song to add to the playlist
	 */
	public void setSong(Song song);
	/**
	 * Changes who owns the playlist
	 * @param owner either a username or <code>global</code>. 
	 * Determines who can access the playlist
	 */
	public void setOwner(String owner);
	/**
	 * changes the name of the playlist
	 * @param name new name for the playlist
	 */
	public void setName(String name);
	
}
