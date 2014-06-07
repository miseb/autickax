package cz.cuni.mff.xcars.entities;

import java.io.Externalizable;

import cz.cuni.mff.xcars.constants.Constants;
import cz.cuni.mff.xcars.gamelogic.SubLevel;
import cz.cuni.mff.xcars.miniGames.Minigame;
import cz.cuni.mff.xcars.miniGames.SwitchingMinigame;
import cz.cuni.mff.xcars.scene.GameScreen;

public final class Tornado extends GameObject implements Externalizable {
	public static final String name = Constants.gameObjects.TORNADO_NAME;

	public Tornado(float x, float y, int type) {
		super(x, y, type);
	}

	public Tornado(GameObject object) {
		super(object);
	}

	/** Parameterless constructor for the externalization */
	public Tornado() {
	}

	@Override
	public String getName() {
		return name;
	}

	/** Gets the texture name according to a type */
	public static String GetTextureName(int type) {
		return Constants.gameObjects.GAME_OBJECTS_TEXTURE_PREFIX
				+ Constants.gameObjects.TORNADO_NAME + type;
	}

	@Override
	public GameObject copy() {
		return new Tornado(this);
	}

	@Override
	public void setTexture(int type) {
		super.setTexture(Tornado.GetTextureName(type));
	}

	@Override
	public Minigame getMinigame(GameScreen gameScreen, SubLevel parent) {
		return new SwitchingMinigame(gameScreen, parent);
	}
}