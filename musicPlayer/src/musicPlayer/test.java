package musicPlayer;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class test {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// TODO Auto-generated method stub
		Window window = new Window();
		window.setVisible(true);
		ClipManager clipManager = ClipManager.getInstance();
//		PlayerList playerList = new PlayerList(new File("D:\\musics"));
	//	Vector<File> playerLists = playerList.getPlayerList();
	//	for (File file : playerLists) {
	//		System.out.println(file.getName());
	//	}
	//	clipManager.setClip(playerLists.elementAt(0));
	//	clipManager.play();
	//	while (true) {
	//		
	//	}
		//
		//
		//
		//
		//
		//
		//
		//
	}

}
