/**
 * @author adventure
 * @time 2016/2/17
 * 
 * 用于控制单首音乐剪辑的播放，暂停和声音处理等基本功能
 * 
 */
package musicPlayer;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.omg.CORBA.PUBLIC_MEMBER;

public class ClipManager {
	
	public static ClipManager getInstance() {
		
		if (clipManager == null)
		{
			clipManager = new ClipManager();
			return clipManager;
		}
		else 
			return clipManager;
	}
	
	private ClipManager() {
		// TODO Auto-generated constructor stub
		try {
			music = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setClip(File musicFile) {
		
		try {
			AudioInputStream in;
			in = AudioSystem.getAudioInputStream(musicFile);
			
			AudioFormat baseFormat = in.getFormat();
			AudioFormat decodingFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 
				baseFormat.getSampleRate(), 
				16, 
				baseFormat.getChannels(), 
				baseFormat.getChannels() * 2, 
				baseFormat.getSampleRate(), 
				false);
			AudioInputStream decodeIn = AudioSystem.getAudioInputStream(decodingFormat, in);
			music.open(decodeIn);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void cleanClip() {
		music.flush();
	}
	
	public void closeClip() {
		cleanClip();
		music.close();
	}
	
	
	//播放音乐
	public void play() {
		music.start();
	}
	//暂停音乐
	public void stop() {
		music.stop();
	}
	//快进音乐
	public boolean speed(int Microseconds) {
		
		if (music.isRunning()) {
			music.stop();
		}
		if (music.getMicrosecondPosition() + DEFAULTSPEEDLENGTH >= music.getMicrosecondLength()) {
			return false;
		}
		music.setMicrosecondPosition(music.getMicrosecondPosition() + DEFAULTSPEEDLENGTH);
		music.start();
		return true;
			
	}
	//快退音乐
	public boolean backward(int Microseconds) {
		
		if (music.isRunning()) {
			music.stop();
		}
		if (music.getMicrosecondPosition() -+ DEFAULTSPEEDLENGTH <= 0) {
			return false;
		}
		music.setMicrosecondPosition(music.getMicrosecondPosition() - DEFAULTSPEEDLENGTH);
		music.start();
		return true;
		
	}
	//任意调整时间播放
	public boolean randomPlay(int time) {
		
		if (music.isRunning()) {
			music.stop();
		}
		if (time * 1000 >= music.getMicrosecondLength() || time * 1000 <= 0 ) {
			return false;
		}
		music.setMicrosecondPosition(time * 1000);
		music.start();
		return true;
		
	}
	
	
	//获得时间
	public float getVolumn() {
		return music.getLevel();
		
	}
	//获得以微秒计音乐长度
	public long getMicroMinuteLength() {
		return music.getMicrosecondLength();
	}
	//获得当前播放时间
	public long getCurrentPosition() {
		return music.getMicrosecondPosition();
	}
	
	
	//获得当前的播放状态
	public boolean isEnding() {
		return !music.isRunning();
	}
	
	public boolean isUpload() {
		return music.isOpen();
	}
	
	private Clip music;
	private static final long DEFAULTSPEEDLENGTH = 10000;
	
	private static ClipManager clipManager = null;

}
