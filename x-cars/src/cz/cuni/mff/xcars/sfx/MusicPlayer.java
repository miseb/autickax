package cz.cuni.mff.xcars.sfx;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;

import cz.cuni.mff.xcars.constants.Constants;

public class MusicPlayer implements OnCompletionListener{

	private ArrayList<Music> tracks;
	private Music currentTrack;
	private int currentTrackIndex;
	private Random rnd;
	
	public MusicPlayer(ArrayList<Music> tracks)
	{
		this.tracks = tracks;
		for (Music music: tracks)
		{
			music.setOnCompletionListener(this);
			music.setVolume(Constants.sounds.MUSIC_DEFAULT_VOLUME);
			music.setLooping(false);
		}
		this.rnd = new Random();
		this.currentTrack = tracks.get(getNextIndex());
	}
	/** Chooses next index of a track */
	private int getNextIndex()
	{
		int previousIndex = currentTrackIndex;
		this.currentTrackIndex = rnd.nextInt(tracks.size());
		if (this.currentTrackIndex == previousIndex) //ensure switching tracks
		{
			currentTrackIndex = (currentTrackIndex + 1) % tracks.size();
		}
		return currentTrackIndex;	
	}
	/** Plays next song  */
	private void playNextSong()
	{
		this.currentTrack = tracks.get(getNextIndex());
		play();
	}
	
	/**	Starts playing currently assigned music */
	public void play()
	{
		this.currentTrack.play();
	}
	
	/** Pauses currently played music */
	public void pause()
	{
		this.currentTrack.pause();
	}
	
	/** Stops currently played music */
	public void stop()
	{
		this.currentTrack.stop();
	}

	/**
	 * @param music Track that finished playing
	 * Called when music track reaches the end.
	 * New song is then played
	 */
	@Override
	public void onCompletion(Music music) {
		playNextSong();
	}

}
