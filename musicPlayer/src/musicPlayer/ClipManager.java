/**
 * @author adventure
 * @time 2016/2/17
 * 
 * ���ڿ��Ƶ������ּ����Ĳ��ţ���ͣ����������Ȼ�������
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
	
	
	//��������
	public void play() {
		music.start();
	}
	//��ͣ����
	public void stop() {
		music.stop();
	}
	//�������
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
	//��������
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
	//�������ʱ�䲥��
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
	
	
	//���ʱ��
	public float getVolumn() {
		return music.getLevel();
		
	}
	//�����΢������ֳ���
	public long getMicroMinuteLength() {
		return music.getMicrosecondLength();
	}
	//��õ�ǰ����ʱ��
	public long getCurrentPosition() {
		return music.getMicrosecondPosition();
	}
	
	
	//��õ�ǰ�Ĳ���״̬
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
