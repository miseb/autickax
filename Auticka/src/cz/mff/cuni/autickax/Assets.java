package cz.mff.cuni.autickax;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cz.mff.cuni.serialization.AvailableLevelsLoader;

public class Assets {

	private static final String GRAPHICS_DIR = "images";
	private static final String GRAPHICS_FILE = GRAPHICS_DIR + "/images";
	private static final String FONT_FILE = "fonts/font.fnt";
	private static final String MENU_FONT_FILE = "fonts/menu.fnt";
	private static final String DIALOG_FONT_FILE = "fonts/dialog.fnt";
	private static final String FINISH_DIALOG_FONT_FILE = "fonts/finishDialog.fnt";
	private static final String TIME_STRINGS_FONT = "fonts/timeString.fnt";
	private static final String TIME_INT_FONT = "fonts/timeInt.fnt";
	private static final String LEVEL_NUMBER_FONT = "fonts/levels.fnt";
	private static final String AVAILABLE_LEVELS_FILE = "availableLevels.bin";	public AssetManager assetManager;

	private Map<String, TextureRegion> graphicsCacheMap;

	private TextureAtlas atlas;

	public SoundAndMusicManager soundAndMusicManager;

	public Assets() {
		assetManager = new AssetManager();
		graphicsCacheMap = new HashMap<String, TextureRegion>();
	}

	public boolean update() {
		return assetManager.update();
	}

	// return progress from 0 to 1
	public float getProgress() {
		return assetManager.getProgress();
	}

	public void load() {
		this.loadGraphics();
		this.loadFonts();
		this.loadSounds();
		this.loadAvailableLevels();
		//loadLevels(); it has to be loaded manually after loading whole assets because levels uses these assets 
	}

	public TextureRegion getGraphics(String name) {
		if (graphicsCacheMap.containsKey(name)) { // We have called this
													// already, so we can reuse
													// the cached result from
													// last time
			return graphicsCacheMap.get(name);
		}
		if (atlas == null) {
			atlas = assetManager.get(GRAPHICS_FILE, TextureAtlas.class);
		}
		TextureRegion tr = atlas.findRegion(name);

		if (tr == null) {
			throw new RuntimeException("Graphic " + name
					+ " not found it atlas");
		}

		tr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		graphicsCacheMap.put(name, tr); // cache the result
		return tr;
	}

	private void loadGraphics() {
		// TODO: Break into loading of common controls and level-specific.
		// TODO: Handle multiple resolution loading.
		//Figure out which graphics set to load, see http://wiki.starling-framework.org/manual/multi-resolution_development
		//int screenWidth = Gdx.app.getGraphics().getWidth();
		/*if( screenWidth >= 1280 ) {
			graphicsHorizontalRes = 1280;
			graphicsFile = GRAPHICS_FILE_X;
		}else{
			graphicsHorizontalRes = 320;
			graphicsFile = GRAPHICS_FILE_L;
		}*/
		assetManager.load(GRAPHICS_FILE , TextureAtlas.class);
	}



	private void loadFonts() {
		assetManager.load(FONT_FILE, BitmapFont.class);
		assetManager.load(MENU_FONT_FILE, BitmapFont.class);
		assetManager.load(DIALOG_FONT_FILE, BitmapFont.class);
		assetManager.load(FINISH_DIALOG_FONT_FILE, BitmapFont.class);
		assetManager.load(TIME_INT_FONT, BitmapFont.class);
		assetManager.load(TIME_STRINGS_FONT, BitmapFont.class);
		assetManager.load(LEVEL_NUMBER_FONT, BitmapFont.class);
	}
	
	private void loadAvailableLevels() {
		assetManager.setLoader(AvailableLevels.class, new AvailableLevelsLoader(new InternalFileHandleResolver()));
		assetManager.load(AVAILABLE_LEVELS_FILE, AvailableLevels.class);
	}

	public BitmapFont getFont() {
		return assetManager.get(FONT_FILE, BitmapFont.class);
	}

	public BitmapFont getMenuFont() {
		return assetManager.get(MENU_FONT_FILE, BitmapFont.class);
	}

	public BitmapFont getDialogFont() {
		return assetManager.get(DIALOG_FONT_FILE, BitmapFont.class);
	}
	
	public BitmapFont getFinishDialogFont() {
		return assetManager.get(FINISH_DIALOG_FONT_FILE, BitmapFont.class);
	}
		
	public AvailableLevels getAvailableLevels() {
		return assetManager.get(AVAILABLE_LEVELS_FILE, AvailableLevels.class);
	}

	public FileHandle loadLevel(String name, Difficulty difficulty) {
		return Gdx.files.internal("levels\\"+ difficulty.toString() + "\\" + name + ".xml");
	}
	
	public BitmapFont getTimeStringFont()
	{
		return assetManager.get(TIME_STRINGS_FONT, BitmapFont.class);
	}
	
	public BitmapFont getTimeIntFont()
	{
		return assetManager.get(TIME_INT_FONT, BitmapFont.class);
	}
	
	public BitmapFont getLevelNumberFont()
	{
		return assetManager.get(LEVEL_NUMBER_FONT, BitmapFont.class);
	}
	
	private void loadSounds() {
		Map<String, Sound> soundsMap = new HashMap<String, Sound>();
		soundsMap.put(Constants.SOUND_EDITOR, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_EDITOR_PATH)));
		soundsMap.put(Constants.SOUND_MUD, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_MUD_PATH)));
		soundsMap.put(Constants.SOUND_TREE, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_TREE_PATH)));
		soundsMap.put(Constants.SOUND_HOLE, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_HOLE_PATH)));
		soundsMap.put(Constants.SOUND_STONE, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_STONE_PATH)));
		soundsMap.put(Constants.SOUND_ENGINE_START, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_ENGINE_START_PATH)));
		soundsMap.put(Constants.SOUND_MENU_OPEN, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_MENU_OPEN_PATH)));
		soundsMap.put(Constants.SOUND_MENU_CLOSE, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_MENU_CLOSE_PATH)));
		soundsMap.put(Constants.SOUND_MINIGAME_FAIL, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_MINIGAME_FAIL_PATH)));
		soundsMap.put(Constants.SOUND_MINIGAME_SUCCESS, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_MINIGAME_SUCCESS_PATH)));
		soundsMap.put(Constants.SOUND_SUB1_CHEER, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_SUB1_CHEER_PATH)));
		soundsMap.put(Constants.SOUND_SUB2_CHEER, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_SUB2_CHEER_PATH)));
		soundsMap.put(Constants.SOUND_SUB1_FAIL, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_SUB1_FAIL_PATH)));
		soundsMap.put(Constants.SOUND_SUB2_START, Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_SUB2_START_PATH)));
		
		Music menuMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.MUSIC_MENU_PATH));
		Music raceMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.MUSIC_RACE_PATH));
		this.soundAndMusicManager = new SoundAndMusicManager(soundsMap, raceMusic, menuMusic);
	}
	

	

}
