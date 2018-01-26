package jhd.bluebox.sensu;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * 
 * @author jia.haodong 该类主要2个目的，播放或暂停，共有2个音乐内容，一个报错MP3，一个有未读邮件通知MP3
 */
public class Mp3Player {
	//
	private Player player;
	private Player playerErr;
	private boolean playing = false;
	private boolean playingErr = false;

	public Mp3Player() {
	}

	public void play() {
		if (playing) {
			System.out.println("playing");
			return;
		}
		playing = true;
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(Config.MP3_PATH));
			player = new Player(in);
			System.out.println("play");
			player.play();
			in.close();
			player.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		playing = false;
	}

	public void playErr() {
		if (playingErr) {
			System.out.println("playing err");
			return;
		}
		playingErr = true;
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(Config.MP3_PATH_ERR));
			playerErr = new Player(in);
			System.out.println("play err");
			playerErr.play();
			in.close();
			playerErr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		playingErr = false;
	}

	public void stop() {
		if (player != null) {
			player.close();
			playing = false;
			System.out.println("stop mp3");
		}
		if (playerErr != null) {
			playerErr.close();
			playingErr = false;
			System.out.println("stop err mp3");
		}
	}

}
