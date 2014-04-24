package cz.mff.cuni.autickax;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class AutickaxDesktop {
	public static void main(String[] args) {
	// Runtime packing - expects assetSrc folder in a parent folder of this project
	//	TexturePacker2.process("../assetSrc/", "../SampleGDXgame-android/assets/img/packed", "graphics");
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "AutickaX";
		cfg.useGL20 = false;
		cfg.width =800;
		cfg.height = 480;
		cfg.fullscreen = false;
		cfg.resizable = false;
		//cfg.addIcon("icons/128.png", FileType.Internal);
		//cfg.addIcon("icons/32.png", FileType.Internal);
		//cfg.addIcon("icons/16.png", FileType.Internal);
		
		new LwjglApplication(new Autickax(), cfg);
	}
}