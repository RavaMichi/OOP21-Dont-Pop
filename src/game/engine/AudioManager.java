package game.engine;

import java.util.Map;

import javafx.scene.media.MediaPlayer;

/**
 * The class that controls all sounds and music
 */
public class AudioManager {
	/**
	 * Each element of this enum represents a music, playable by the method playMusic.
	 * It's used for long audio files.
	 */
	public static enum Music {
		
	}
	/**
	 * Each element of this enum represents a sound, playable by the method playSound.
	 * It's used for short audio clips.
	 */
	public static enum Sound {
		
	}
	
	private static Map<Music, MediaPlayer> musics;
	private static Map<Sound, MediaPlayer> sounds;
}
