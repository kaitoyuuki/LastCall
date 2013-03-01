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
	public BasicTracker cat, thirteen, blocks, chirp, far, mall, mellohi, stal, strad, ward, eleven, wait, listCount;
	public List<Playlist> playlists = new ArrayList<Playlist>();
	public File playlistsFolder;
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		getConfig();
		
		playlistsFolder = getPlaylistsFolder();
		playlists = loadPlaylists(playlistsFolder);
		/*
		File allList = new File(getPlaylistsFolder(), "all.txt");
		File musicList = new File(getPlaylistsFolder(), "music.txt");
		File originalList = new File(getPlaylistsFolder(), "original.txt");
		if (!allList.exists()) {
			this.saveResource(allList.getPath(), false);
		}
		if (!musicList.exists()) {
			this.saveResource(musicList.getPath(), false);
		}
		if (!originalList.exists()) {
			this.saveResource(originalList.getPath(), false);
		}
		*/
		getCommand("play").setExecutor(new LastCallPlay(this));
		getCommand("lastcall").setExecutor(new LastCallCommands(this));
		getCommand("lc").setExecutor(new LastCallCommands(this));
		getCommand("testcall").setExecutor(new MDCommands(this));
		getCommand("playlist").setExecutor(new PlaylistCommands(this));
		// Enabling MetricsExtension
		try{
			String graph = "Percentage of discs played";
			String PLgraph = "Number of Playlists";
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

			listCount = EMetrics.createBasicTracker(PLgraph, "Playlists");
			
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
			
			metrics.addTracker(listCount);
			
			listCount.increment(playlists.size());
			
			metrics.startMetrics();

		}catch(IOException e){
			// you broke something, didn't you?
		}
	}

	@Override
	public void onDisable() {
		// TODO Insert logic to be performed when the plugin is disabled
	}
	
	/**
	 * 
	 * @return File object for the playlists folder
	 */
	public File getPlaylistsFolder() {
		File pluginFolder = getDataFolder();
		File playlistsFolder = new File(pluginFolder, "Playlists");
		if(!(playlistsFolder.exists())) {
			playlistsFolder.mkdir();
		}
		return playlistsFolder;
	}
	/**
	 * 
	 * @return list of playlists
	 */
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	public Playlist getPlaylist(String name) {
		if (playlists.size() == 0) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		for(Playlist plist : playlists) {
			list.add(plist.getName());
		}
		if (list.contains(name)) {
			Playlist playlist = playlists.get(list.indexOf(name));
			return playlist;
		}
		else {
			return null;
		}
	}
	/**
	 * Adds the playlist to the list of available playlists
	 * @param playlist 
	 */
	public void addPlaylist(Playlist playlist) {
		playlists.add(playlist);
	}
	public void delPlaylist(Playlist playlist) {
		playlists.remove(playlist);
	}
	/**
	 * load all playlists from the playlist folder into the given List object
	 * @param playlistsFolder where to find the playlists
	 * @return playlists
	 */
	public List<Playlist> loadPlaylists(File playlistsFolder) {
		//load playlists from playlists folder

		File[] files = playlistsFolder.listFiles();
		if (files == null) {
			return null;
		}
		List<Playlist> playlists = new ArrayList<Playlist>();
		
		for (File file : files) {
			try {
				playlists.add(makeList(file));
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
		if (file.getName().endsWith(".txt")) {
			LastDiscs disc = new LastDiscs();
			String name = file.getName().substring(0, file.getName().lastIndexOf(".txt"));
			if (disc.getSong(name) != null) {
				return null;
			}
			FileReader fr = new FileReader(file);
			BufferedReader textReader = new BufferedReader(fr);

			int numberOfLines = readLines(file);
			String[] text = new String[numberOfLines];

			String owner;
			List<Song> songs = new ArrayList<Song>();

			int i;

			for (i=0; i < numberOfLines; i++) {
				text[i] = textReader.readLine();
			}
			textReader.close();

			owner = text[0];
			

			for (i=1; i < numberOfLines; i++) {
				Song song = disc.getSong(text[i]);
				songs.add(song);
			}
			Playlist playlist = new Playlist(owner, name, songs);
			return playlist;
		}
		else {
			return null;
		}
	}

}
