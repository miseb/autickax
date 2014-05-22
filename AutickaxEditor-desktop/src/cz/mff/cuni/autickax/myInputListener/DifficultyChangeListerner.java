package cz.mff.cuni.autickax.myInputListener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

import cz.mff.cuni.autickax.Difficulty;
import cz.mff.cuni.autickax.EditorScreen;

public class DifficultyChangeListerner extends MyInputListener {
	protected int index;
	Difficulty difficulty;

	public DifficultyChangeListerner(EditorScreen screen, int index) {
		super(screen);
		this.index = index;
		this.difficulty = Difficulty.values()[index];
	}

	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		screen.difficulty = Difficulty.Kiddie;
		if (screen.getPathway() != null && screen.getPathway().getDistanceMap() != null)
			screen.setPathwayTexture(screen.getPathway().getDistanceMap()
					.generateTexture(difficulty));
		super.touchUp(event, x, y, pointer, button);
	}

}
