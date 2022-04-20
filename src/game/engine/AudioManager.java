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
	
	private static final Map<Music, MediaPlayer> MUSICS = new HashMap<>();
	private static final Map<Sound, AudioClip> SOUNDS = new HashMap<>();
	
	/**
	 * Each element of this enum represents a music, playable by the method playMusic.
	 * It's used for long audio files.
	 */
	public static enum Music {
		BALOON_GROOVE("");
		
		private Music(String path) {
			Media media = new Media(getResPath(path));
			MUSICS.put(this, new MediaPlayer(media));
		}
	}
	/**
	 * Each element of this enum represents a sound, playable by the method playSound.
	 * It's used for short audio clips.
	 */
	public static enum Sound {
		POP("");
		
		private Sound(String path) {
			SOUNDS.put(this, new AudioClip(getResPath(path)));
		}
	}
	/**
	 * Converts a resource path to a URI path, for Audio loading
	 * @param path
	 * @return correct path
	 */
	private static String getResPath(String path) {
		return AudioManager.class.getClassLoader().getResource(path).toExternalForm();
	}
	
	private Map<Music, MediaPlayer> musics;
	private Map<Sound, AudioClip> sounds;
	
	public AudioManager() {
		this.musics = Map.copyOf(MUSICS);
		this.sounds = Map.copyOf(SOUNDS);
	}
	
	/**
	 * Plays a sound using given volume. Volume is a number between 0.0 (muted) and 1.0 (full volume)
	 * @param sound
	 * @param volume
	 */
	public void playSound(final Sound sound, double volume) {
		this.sounds.get(sound).play(volume);
	}
	/**
	 * Plays a music using given volume. Volume is a number between 0.0 (muted) and 1.0 (full volume).
	 * An AudioManager can play only one music at time.
	 * @param music
	 * @param volume
	 */
	public void playMusic(final Music music, double volume) {
		stopMusics();
		this.musics.get(music).setVolume(volume);
		this.musics.get(music).play();
	}
	/**
	 * Stops all the playing sounds
	 */
	public void stopSounds() {
		this.sounds.forEach((s, a) -> a.stop());
	}
	/**
	 * Stops all the playing musics
	 */
	public void stopMusics() {
		this.musics.forEach((s, m) -> m.stop());
	}
	/**
	 * Stops every audio playing
	 */
	public void stopAll() {
		stopSounds();
		stopMusics();
	}
}
