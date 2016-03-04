/**
 * @author adventure
 * @time 2016/2/17
 * 
 *  用于保存音乐菜单
 * 
 */
package musicPlayer;

import java.io.File;
import java.util.TreeMap;
import java.util.Vector;

public class PlayerList {
	
	public static PlayerList getInstance() {
		if (playerList == null) {
			playerList = new PlayerList();
			return playerList;
		} else {
			return playerList;
		}
	}
	
	public String[] getMusicNames() {
		String[] musicNames = new String[musics.size()];
		for (int i = 0; i < musics.size(); i++) {
			musicNames[i] = musics.elementAt(i).getName();
		}
		return musicNames;
	}
	
	
	public void setPlayerList(File rootFile) {
		
		getFiles(rootFile);
		
	}
	
	public TreeMap<String, File> getFileAndNameMap() {
		return map;
	}
	
	public Vector<File> getPlayerList() {
		return musics;
	}
	
	public void cleanList() {
		if (!musics.isEmpty()) {
			musics.clear();
		}
	}
	
	private void getFiles(File root) {
		if (root.isFile()) {
			if (isMusicFormat(root)) {
				musics.add(root);
				map.put(root.getName(), root);
			}
		}
		if (root.isDirectory()) {
			File[] listFiles = root.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				getFiles(listFiles[i]);
			}
		}
	}
	
	private boolean isMusicFormat(File file) {
		String fileName = file.getName();
		String expandName = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
		if (expandName.equalsIgnoreCase("mp3") || expandName.equalsIgnoreCase("wav")) {
			return true;
		}
		return false;
	}
	
	private PlayerList() {
		// TODO Auto-generated constructor stub
		musics = new Vector<File>();
		map = new TreeMap<String, File>();
	}
	
	
	private Vector<File> musics;
	private TreeMap<String, File> map;
	
	private static PlayerList playerList = null;

}
