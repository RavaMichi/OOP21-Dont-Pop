package game.engine;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The class that controls all sounds and music
 */
public class AudioManager {
	
	private static Map<Music, MediaPlayer> musics;
	private static Map<Sound, AudioClip> sounds;
	
	/**
	 * Each element of this enum represents a music, playable by the method playMusic.
	 * It's used for long audio files.
	 */
	public static enum Music {
		BALOON_GROOVE("");
		
		private Music(String path) {
			Media media = new Media(getResPath(path));
			musics.put(this, new MediaPlayer(media));
		}
	}
	/**
	 * Each element of this enum represents a sound, playable by the method playSound.
	 * It's used for short audio clips.
	 */
	public static enum Sound {
		POP("");
		
		private Sound(String path) {
			sounds.put(this, new AudioClip(getResPath(path)));
		}
	}
	
	private static String getResPath(String path) {
		return AudioManager.class.getClassLoader().getResource(path).toExternalForm();
	}
	
	public void playSound() {
		
	}
}
