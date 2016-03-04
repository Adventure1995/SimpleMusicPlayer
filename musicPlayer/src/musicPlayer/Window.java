/**
 * @author adventure
 * 
 * @time 2016/3/4
 * 
 * 主界面
 */
package musicPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Window extends JFrame{
	
	public Window() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// TODO Auto-generated constructor stub
		setTitle("musicPlayer");
		setSize(400, 400);
		String defaultFile = "D:\\music";
		playerController = new PlayerController(new File(defaultFile));
		
		playerList = PlayerList.getInstance();
		
		JPanel mainPanel = new JPanel();
		add(mainPanel);
		
		JList<String> musicList = new JList<String>(playerList.getMusicNames());
		mainPanel.add(new JScrollPane(musicList));
		//实现对音乐列表的监听，当双击时，播放音乐
		musicList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				JList<String> list = (JList<String>) e.getSource();
				if (list.getSelectedIndex() != -1) {
					if (e.getClickCount() == 2) {
						File file = playerList.getFileAndNameMap().get(((JList<String>)(e.getSource())).getSelectedValue());
						playerController.play(file);
					}
				}
			}
		});
		
		JPanel controlJPanel = new JPanel();
		mainPanel.add(controlJPanel);
		
		JButton playButton = new JButton("play");
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playerController.play();
			}
		});
		controlJPanel.add(playButton);
		
		JButton stopButton = new JButton("stop");
		stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playerController.pause();
			}
		});
		controlJPanel.add(stopButton);
		
		JButton nextMusicButton = new JButton("nextMusic");
		nextMusicButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playerController.nextMusic();
			}
		});
		controlJPanel.add(nextMusicButton);
		
		JButton speedButton = new JButton("speed");
		speedButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		//		clipManager.speed();
			}
		});
		controlJPanel.add(speedButton);
		
	}
	
	private PlayerController playerController;
	private PlayerList playerList;

}
