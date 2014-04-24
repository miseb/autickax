import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;
import com.badlogic.gdx.tools.imagepacker.TexturePacker;

import cz.mff.cuni.autickax.AvailableLevels;
import cz.mff.cuni.autickax.Difficulty;
import cz.mff.cuni.autickax.Level;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AssetsProcessor {

	private static final String ASSETS_PATH = "../Assets/";
	private static final String ASSETS_IMAGES_PATH = ASSETS_PATH + "images";
	private static final String ASSETS_LOADING_SCREEN_IMAGES_PATH = ASSETS_PATH + "loadingScreen";
	private static final Path ASSETS_FONTS_PATH = Paths.get(ASSETS_PATH + "fonts");
	private static final Path ASSETS_SFX_PATH = Paths.get(ASSETS_PATH + "sfx");
	private static final Path ASSETS_LEVELS_PATH = Paths.get(ASSETS_PATH + "levels");
	
	private static final String ANDROID_PATH = "../Autickax-android/assets/";
	private static final Path ANDROID_IMAGES_PATH = Paths.get(ANDROID_PATH + "images");
	private static final Path ANDROID_LOADING_SCREEN_IMAGES_PATH = Paths.get(ANDROID_PATH + "loadingScreen");
	private static final Path ANDROID_FONTS_PATH = Paths.get(ANDROID_PATH + "fonts");
	private static final Path ANDROID_SFX_PATH = Paths.get(ANDROID_PATH + "sfx");
	
    public static void main(String[] args) throws IOException {
    	
    	createLevelsPaths();
    	System.out.println("Levels path created.");
    	
    	compressImages();
    	System.out.println("Images compressed and copied.");
    	
    	copyFont();
    	System.out.println("Font copied.");
    	
    	copySfx();
    	System.out.println("Audio copied.");
    	
    	System.out.println("Assets processed.");
    }
    
    private static void createLevelsPaths() {
    	AvailableLevels availableLevels = new AvailableLevels();
    	
    	File levelsImagesDirectory = new File(ANDROID_PATH.toString());
    	if (!levelsImagesDirectory.exists()) {
    		System.out.println("creating directory: " + ANDROID_PATH);
    		levelsImagesDirectory.mkdir();
    	}
    	  
    	File levelsDirectory = ASSETS_LEVELS_PATH.toFile();
    	File[] directories = levelsDirectory.listFiles();
    	for( File directory : directories ) {
        	File[] files = directory.listFiles();
        	
        	Difficulty levelDifficulty = Difficulty.valueOf(directory.getName());
        	
        	for( File file : files ) {
	    		LevelPath levelPath = new LevelPath(file);
	    		levelPath.parseLevel();
	    		Level level = levelPath.getLevel();
        	
	        	switch (levelDifficulty) {
				case Beginner:
					availableLevels.beginnerLevels.add(level);
					break;
				case Extreme:
					availableLevels.extremeLevels.add(level);
					break;
				case Hard:
					availableLevels.hardLevels.add(level);
					break;
				case Kiddie:
					availableLevels.kiddieLevels.add(level);
					break;
				case Normal:
					availableLevels.normalLevels.add(level);
					break;
				default:
					break;
	        	}
        	}
    	}
    	
    	 try
         {
            FileOutputStream fileOut = new FileOutputStream(ANDROID_PATH + "\\availableLevels.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(availableLevels);
            out.close();
            fileOut.close();
            System.out.println("Available levels serialized.");
         } catch (IOException i)
         {
        	 System.out.print("Problem during serialization: ");
             i.printStackTrace();
         }
    }

	private static void copySfx() throws IOException {
		FileUtilities.copyDirectory(
    		ASSETS_SFX_PATH.toFile(),
			ANDROID_SFX_PATH.toFile()
		);
	}

	private static void copyFont() throws IOException {
		FileUtilities.copyDirectory(
    		ASSETS_FONTS_PATH.toFile(),
			ANDROID_FONTS_PATH.toFile()
		);
	}
    
    private static void compressImages() {
    	Settings settings = new Settings();
    	
    	TexturePacker.process(
			settings,
			ASSETS_LOADING_SCREEN_IMAGES_PATH,
			ANDROID_LOADING_SCREEN_IMAGES_PATH.toString(),
			"images"
		);
    	
    	TexturePacker.process(
			settings,
			ASSETS_IMAGES_PATH,
			ANDROID_IMAGES_PATH.toString(),
			"images"
		);
    }
}