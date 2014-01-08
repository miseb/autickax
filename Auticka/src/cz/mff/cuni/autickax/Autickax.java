package cz.mff.cuni.autickax;

import com.badlogic.gdx.Game;

import cz.mff.cuni.autickax.input.Input;
import cz.mff.cuni.autickax.scene.LoadingScreen;

public class Autickax extends Game {

	private static Autickax _instance;


	public Assets assets;

	public Autickax() {
		_instance = this;
		assets = new Assets();
	}

	@Override
	public void create() {
		setScreen(new LoadingScreen());
		Input.InitDimensions();
	}

	public static Autickax getInstance() {
		return _instance; // will get created when app starts
	}
}