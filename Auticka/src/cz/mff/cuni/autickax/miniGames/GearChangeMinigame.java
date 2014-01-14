package cz.mff.cuni.autickax.miniGames;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import cz.mff.cuni.autickax.Autickax;
import cz.mff.cuni.autickax.Constants;
import cz.mff.cuni.autickax.dialogs.MessageDialog;
import cz.mff.cuni.autickax.gamelogic.SubLevel;
import cz.mff.cuni.autickax.scene.GameScreen;

public class GearChangeMinigame extends Minigame {
	private boolean floatResultNeeded;

	public GearChangeMinigame(GameScreen screen, SubLevel parent, boolean floatResultNeeded) {
		super(screen, parent);
		this.floatResultNeeded = floatResultNeeded;
		if(Autickax.showTooltips)
			this.parent.setDialog(new MessageDialog(screen, parent, Constants.TOOLTIP_MINIGAME_GEAR_CHANGE_WHAT_TO_DO));
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDialogEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMinigameEnded() {
		// TODO Auto-generated method stub
		
	}
	
}