package cz.mff.cuni.autickax;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Sound;

import cz.mff.cuni.autickax.scene.DifficultySelectScreen;
import cz.mff.cuni.autickax.scene.GameScreen;
import cz.mff.cuni.autickax.scene.LevelLoadingScreen;
import cz.mff.cuni.autickax.scene.LevelSelectScreen;
import cz.mff.cuni.autickax.scene.MainMenuScreen;
import cz.mff.cuni.autickax.drawing.Font;
import cz.mff.cuni.autickax.input.Input;
import cz.mff.cuni.autickax.scene.LoadingScreen;

public class Autickax extends Game {

	private static Autickax _instance;
	
	public static LoadingScreen loadingScreen;
	public static MainMenuScreen mainMenuScreen;
	public static DifficultySelectScreen difficultySelectScreen;
	public static LevelSelectScreen levelSelectScreen;
	public static LevelLoadingScreen levelLoadingScreen;
	public static GameScreen gameScreen;
	public static Settings settings;
	public static PlayedLevels playedLevels;
	
	static public Font font;

	public Assets assets;

	public Autickax() {
		_instance = this;
		assets = new Assets();
	}
	

	

	@Override
	public void create() {		
		Autickax.loadingScreen = new LoadingScreen();
		
		settings = new Settings();
		settings.loadSettings();
		
		playedLevels = new PlayedLevels();
		playedLevels.loadLevels();
		
		setScreen(Autickax.loadingScreen);
		Input.InitDimensions();
	}

	public static Autickax getInstance() {
		return _instance; // will get created when app starts
	}
	
	
}
