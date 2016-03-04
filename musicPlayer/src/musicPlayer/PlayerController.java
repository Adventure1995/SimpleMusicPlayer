/**
 * @author adventure
 * @time 2016/2/18
 * 
 * 用于高层次的音乐播放功能
 */
package musicPlayer;

import java.io.File;

public class PlayerController {
	
	public PlayerController(File rootFile) {
		// TODO Auto-generated constructor stub
		clipManager = ClipManager.getInstance();
		modelManager = ModelManager.getInstance();
		playerList = PlayerList.getInstance();
		playerList.setPlayerList(rootFile);
		currentMusicIndex = 0;
		setPlayState(IS_STOPED);
	}
	//从当前列表第一首歌开始播放
	public void play() {
		//use thread sleep;
		setPlayState(IS_RUNNING);
		if (clipManager.isUpload()) {
			clipManager.play();
		} else {
			clipManager.cleanClip();
			clipManager.setClip(playerList.getPlayerList().elementAt(0));
			clipManager.play();
		}
	
	}
	//指定歌曲编号开始播放
	public void play(int index) {
		setPlayState(IS_RUNNING);
		clipManager.cleanClip();
		clipManager.setClip(playerList.getPlayerList().elementAt(index));
		clipManager.play();
	}
	//指定歌名开始播放
	public void play(File file) {
		setPlayState(IS_RUNNING);
		clipManager.cleanClip();
		clipManager.setClip(file);
		clipManager.play();
	}
	
	public void pause() {
		setPlayState(IS_STOPED);
		clipManager.stop();
	}
	
//	public void resumn() {
//		setPlayState(IS_RUNNING);
//	}
	
	public void nextMusic() {
		clipManager.stop();
		currentMusicIndex = getNextMusicIndex(currentMusicIndex, modelManager.getPlayModel());
		clipManager.closeClip();
		clipManager.setClip(playerList.getPlayerList().elementAt(currentMusicIndex));
		play();
	}
	
	public void preMusic() {
		clipManager.stop();
		clipManager.closeClip();
		clipManager.setClip(playerList.getPlayerList().elementAt(preMusicIndex));
		play();
	}
	
	
	
	
	public int getPlayState() {
		return playState;
	}

	public void setPlayState(int playState) {
		this.playState = playState;
	}
	
//	private File getNextMusic(int currentMusicIndex, int playModel) {
//		return playerList.getPlayerList().elementAt(getNextMusicIndex(currentMusicIndex, playModel));
//		
//	}
	
	private int getNextMusicIndex(int currentMusicIndex, int playModel) {
		preMusicIndex = currentMusicIndex;
		if (playModel == ModelManager.SERIALMODEL) {
			int tmp = currentMusicIndex + 1;
			if (tmp >= playerList.getPlayerList().size()) {
				tmp = 0;
				return tmp;
			} else {
				return tmp;
			}
		} else if (playModel == ModelManager.RANDOMMODEL) {
			int tmp = 0;
			do {
				tmp = (int) (Math.random() * playerList.getPlayerList().size());
			} while (tmp == currentMusicIndex);
			return tmp;
		} else if (playModel == ModelManager.SINGLEMODEL) {
			return currentMusicIndex;
		} else {
			return ILLIGAL_STATE;
		}
	}
	
	
	
	private int playState;
	private int currentMusicIndex;
	private int preMusicIndex;
	
	private ClipManager clipManager;
	private ModelManager modelManager;
	private PlayerList playerList;
	
	public static final int IS_RUNNING = 1;
	public static final int IS_STOPED = 2;
	private static final int ILLIGAL_STATE = -1;

}
