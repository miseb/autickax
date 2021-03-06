package cz.cuni.mff.xcars;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import cz.cuni.mff.xcars.Xcars;

public class XcarsDesktop {
	public static void main(String[] args) {
	// Runtime packing - expects assetSrc folder in a parent folder of this project
	//	TexturePacker2.process("../assetSrc/", "../SampleGDXgame-android/assets/img/packed", "graphics");
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "x-cars";
		//cfg.width = 1280;
		//cfg.height = 768;
		cfg.width = 1200;
		cfg.height = 720;
		//cfg.width = 800;
		//cfg.height = 480;
		
		cfg.fullscreen = false;
		cfg.resizable = false;
		cfg.addIcon("icons/128.png", FileType.Internal);
		cfg.addIcon("icons/32.png", FileType.Internal);
		cfg.addIcon("icons/16.png", FileType.Internal);
		
		new LwjglApplication(new Xcars(null), cfg);
	}
}
