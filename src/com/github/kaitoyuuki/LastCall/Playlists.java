package com.github.kaitoyuuki.LastCall;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public interface Playlists {


	/**
	 * removes the playlist from the currently available playlists.
	 */
	public void unloadPlaylist();
	/**
	 * Saves the playlist to a file. If the file does not exist, it will create it.
	 */
	public boolean saveList();
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
	 * removes the song at the given position from the playlist
	 * @param position location of the song to be removed
	 * @return false if the position is outside of the range of the list
	 */
	public boolean delSong(int position);
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
	/**
	 * plays all songs in the playlist for the target
	 * @param target player to play the song for
	 * @return false if the player does not exist
	 */
	public boolean play(Player target);
	/**
	 * plays all songs in the playlist for all players in the given world
	 * @param world world object where the songs should be played
	 * @return False if the world does not exist
	 */
	public boolean play(World world);
	/**
	 * plays all songs in the playlist for all players in the given group
	 * @param group name of the group. players must have lastcall.play.{groupname} to hear the songs
	 * @return false if the group does not exist
	 */
	public boolean play(String group);
	/**
	 * plays all songs for all players
	 */
	public void play();
	/**
	 * plays the given song number from the playlist for all players
	 * @param index song number. position of the song within <code>songs</code>
	 * @return false if the number is outside the range of the list
	 */
	public boolean play(Integer index);
	/**
	 * plays the given song number from the playlist for the given player
	 * @param target player who will hear the song
	 * @param index song number
	 * @return false if the player does not exist, or the number is outside the range of the list
	 */
	public boolean play(Player target, Integer index);
	/**
	 * plays the given song number from the playlist for all players in the given world
	 * @param world world where the song should be played
	 * @param index song number
	 * @return false if the world does not exist, or the number is outside the range of the list
	 */
	public boolean play(World world, Integer index);
	/**
	 * plays the given song number from the playlist for all players in the given group
	 * @param group group that should hear the song. Includes players with lastcall.play.{groupname}
	 * @param index song number
	 * @return false if the group does not exist, or the number is outside the range of the list
	 */
	public boolean play(String group, Integer index);
	
}
