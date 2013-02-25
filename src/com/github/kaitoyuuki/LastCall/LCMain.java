package com.github.kaitoyuuki.LastCall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import com.turt2live.metrics.LastCall.EMetrics;
import com.turt2live.metrics.LastCall.tracker.BasicTracker;

@SuppressWarnings("unused")
public final class LCMain extends JavaPlugin {
	public BasicTracker cat, thirteen, blocks, chirp, far, mall, mellohi, stal, strad, ward, eleven, wait;
	
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		getConfig();
		
		File playlistsFolder = getPlaylistsFolder();
		//List<Playlist> playlists = loadPlaylists(playlistsFolder);
		
		getCommand("play").setExecutor(new LastCallPlay(this));
		getCommand("playall").setExecutor(new LastCallPlay(this));
		getCommand("lastcall").setExecutor(new LastCallCommands(this));
		getCommand("lc").setExecutor(new LastCallCommands(this));
		getCommand("testcall").setExecutor(new MDCommands(this));
		// Enabling MetricsExtension
		// TODO add graph and trackers/plotters for number of playlists
		try{
			String graph = "Percentage of discs played";
			EMetrics metrics = new EMetrics(this);
			
			thirteen = EMetrics.createBasicTracker(graph, "13");
			cat = EMetrics.createBasicTracker(graph, "cat");
			blocks = EMetrics.createBasicTracker(graph, "blocks");
			chirp = EMetrics.createBasicTracker(graph, "chirp");
			far = EMetrics.createBasicTracker(graph, "far");
			mall = EMetrics.createBasicTracker(graph, "mall");
			mellohi = EMetrics.createBasicTracker(graph, "mellohi");
			stal = EMetrics.createBasicTracker(graph, "stal");
			strad = EMetrics.createBasicTracker(graph, "strad");
			ward = EMetrics.createBasicTracker(graph, "ward");
			eleven = EMetrics.createBasicTracker(graph, "eleven");
			wait = EMetrics.createBasicTracker(graph, "wait");
			
			metrics.addTracker(thirteen);
			metrics.addTracker(cat);
			metrics.addTracker(blocks);
			metrics.addTracker(chirp);
			metrics.addTracker(far);
			metrics.addTracker(mall);
			metrics.addTracker(mellohi);
			metrics.addTracker(stal);
			metrics.addTracker(strad);
			metrics.addTracker(ward);
			metrics.addTracker(eleven);
			metrics.addTracker(wait);
			metrics.startMetrics();
			
		}catch(IOException e){
			// you broke something, didn't you?
		}
	}

	@Override
	public void onDisable() {
		// TODO Insert logic to be performed when the plugin is disabled
	}
	
	public File getPlaylistsFolder() {
		File pluginFolder = getDataFolder();
		return new File(pluginFolder, "Playlists");
	}
	/**
	 * load all playlists from the playlist folder into the given List object
	 * @param playlistsFolder where to find the playlists
	 * @return playlists
	 */
	public List<Playlist> loadPlaylists(File playlistsFolder) {
		//load playlists from playlists folder
		
		File[] files = playlistsFolder.listFiles();
		List<Playlist> playlists = new ArrayList<Playlist>();
		int i;
		for (i=0; i < files.length; i++) {
			try {
				playlists.add(i, makeList(files[i]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return playlists;
	}
	/**
	 * count how many lines are in the given file
	 * @param file
	 * @return number of lines
	 * @throws IOException
	 */
	int readLines(File file) throws IOException {
		
		FileReader reader = new FileReader(file);
		BufferedReader bf = new BufferedReader(reader);
		
		String aLine;
		int numberOfLines= 0;
		
		while (( aLine = bf.readLine()) != null) {
			numberOfLines++;
		}
		bf.close();
		
		return numberOfLines;
	}
	/**
	 * make a playlist from the given file
	 * @param file file to load the playlist from
	 * @return playlist object
	 * @throws IOException file doesn't exist
	 */
	public Playlist makeList(File file) throws IOException {
		LastDiscs disc = new LastDiscs();
		Playlist playlist = new Playlist(null, null, null);
		FileReader fr = new FileReader(file);
		BufferedReader textReader = new BufferedReader(fr);
		int numberOfLines = readLines(file);
		String[] text = new String[numberOfLines];
		
		int i;
		
		for (i=0; i < numberOfLines; i++) {
			text[i] = textReader.readLine();
		}
		textReader.close();
		
		playlist.setOwner(text[0]);
		playlist.setName(file.getName());
		for (i=1; i < numberOfLines; i++) {
			Song song = disc.getSong(text[i]);
			playlist.setSong(song);
			
		}
		return playlist;
		
	}

}
