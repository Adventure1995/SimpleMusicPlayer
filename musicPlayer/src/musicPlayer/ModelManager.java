/**
 * @author adventure
 * @time 2016/2/18
 * 
 * 用于多首歌的模式切换，列表循环，随机播放，单曲循环
 * 
 */
package musicPlayer;

public class ModelManager {
	
	public static ModelManager getInstance() {
		if (modelManager == null) {
			modelManager = new ModelManager();
			return modelManager;
		} else {
			return modelManager;
		}
	}
	
	public int getPlayModel() {
		return playModel;
	}
	
	public void setPlayModel(int playModel) {
		switch (playModel) {
		case SERIALMODEL:
			this.playModel = SERIALMODEL;
			break;
		case RANDOMMODEL:
			this.playModel = RANDOMMODEL;
			break;
		case SINGLEMODEL:
			this.playModel = SINGLEMODEL;
			break;
		default:
			this.playModel = ILLIGALMODEL;
			break;
		}
	}
	
	private ModelManager() {
		// TODO Auto-generated constructor stub
		playModel = SERIALMODEL;
	}

	private int playModel;
	private static ModelManager modelManager = null;
	
	public static final int SERIALMODEL = 1;
	public static final int SINGLEMODEL = 2;
	public static final int RANDOMMODEL = 3;
	public static final int ILLIGALMODEL = -1;
	
}
