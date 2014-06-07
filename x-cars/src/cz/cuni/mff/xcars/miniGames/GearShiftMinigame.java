package cz.cuni.mff.xcars.miniGames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import cz.cuni.mff.xcars.Xcars;
import cz.cuni.mff.xcars.Difficulty;
import cz.cuni.mff.xcars.constants.Constants;
import cz.cuni.mff.xcars.dialogs.MessageDialog;
import cz.cuni.mff.xcars.exceptions.IllegalDifficultyException;
import cz.cuni.mff.xcars.gamelogic.SubLevel;
import cz.cuni.mff.xcars.input.Input;
import cz.cuni.mff.xcars.miniGames.support.GearShiftMinigameFinish;
import cz.cuni.mff.xcars.miniGames.support.GearShifter;
import cz.cuni.mff.xcars.scene.GameScreen;

public final class GearShiftMinigame extends Minigame {
	private static final float FAIL_VALUE = Constants.minigames.GEAR_SHIFT_FAIL_VALUE;
	private static final float ROW_1 = Constants.minigames.GEAR_SHIFT_MINIGAME_ROW_1;
	private static final float ROW_2 = Constants.minigames.GEAR_SHIFT_MINIGAME_ROW_2;
	private static final float ROW_3 = Constants.minigames.GEAR_SHIFT_MINIGAME_ROW_3;
	private static final float COLUMN_1 = Constants.minigames.GEAR_SHIFT_MINIGAME_COLUMN_1;
	private static final float COLUMN_2 = Constants.minigames.GEAR_SHIFT_MINIGAME_COLUMN_2;
	private static final float COLUMN_3 = Constants.minigames.GEAR_SHIFT_MINIGAME_COLUMN_3;
	private static float MAX_DISTANCE_FROM_LINE;

	private States state = States.BEGINNING_STATE;
	private GearShifter gearShifter;
	private GearShiftMinigameFinish finish;

	public GearShiftMinigame(GameScreen screen, SubLevel parent) {
		super(screen, parent);
		setDifficulty(this.level.getDifficulty());
		this.backgroundTexture = new NinePatchDrawable(
				Xcars.getInstance().assets
						.getNinePatch(Constants.minigames.GEAR_SHIFT_MINIGAME_BACKGROUND_TEXTURE));

		if (Xcars.settings.isShowTooltips())
			this.parent.setDialog(new MessageDialog(screen, parent,
					Constants.strings.TOOLTIP_MINIGAME_GEAR_SHIFT_WHAT_TO_DO));

		randomizeStartAndFinish(screen);
	}

	public void randomizeStartAndFinish(GameScreen screen) {
		float[] rows = new float[] { ROW_1, ROW_2, ROW_3 };
		float[] columns = new float[] { COLUMN_1, COLUMN_2, COLUMN_3 };

		int gearFinishRandomX = 0;
		int gearFinishRandomY = 0;
		do {
			gearFinishRandomX = MathUtils.random(2);
			gearFinishRandomY = MathUtils.random(2);
		} while (gearFinishRandomY == 1);
		this.finish = new GearShiftMinigameFinish(columns[gearFinishRandomX],
				rows[gearFinishRandomY]);		

		int gearShifterRandomX = 0;
		int gearShifterRandomY = 0;
		do {
			gearShifterRandomX = MathUtils.random(2);
			gearShifterRandomY = MathUtils.random(2);
		} while (gearShifterRandomY == 1
				|| (gearShifterRandomX == gearFinishRandomX && gearShifterRandomY == gearFinishRandomY));
		this.gearShifter = new GearShifter(columns[gearShifterRandomX], rows[gearShifterRandomY]);

	}

	@Override
	public void update(float delta) {
		this.gearShifter.update(delta);
		this.finish.update(delta);

		switch (state) {
		case BEGINNING_STATE:
			updateInBeginnigState(delta);
			break;
		case DRIVING_STATE:
			updateInDrivingState(delta);
			break;
		case LEAVING_STATE:
			updateInLeavingState(delta);
			break;
		default:
			throw new IllegalStateException(state.toString());
		}

	}

	private void updateInBeginnigState(float delta) {
		if (Gdx.input.justTouched()) {
			Vector2 touchPos = new Vector2(Input.getX(), Input.getY());

			Vector2 shift = new Vector2(this.gearShifter.getPosition()).sub(touchPos.x, touchPos.y);
			if (shift.len() <= Constants.misc.SHIFTABLE_OBJECT_MAX_CAPABLE_DISTANCE) {
				this.gearShifter.setDragged(true);
				this.gearShifter.setShift(shift);
				state = States.DRIVING_STATE;
			}
		}

	}

	private void updateInDrivingState(float delta) {
		// Focus was lost
		if (!this.gearShifter.isDragged()) {
			fail(Constants.strings.TOOLTIP_MINIGAME_GEAR_SHIFT_WAS_RELEASED);
		}
		// Finish reached
		else if (this.gearShifter.positionCollides(finish)) {
			win();
		}
		// Out of pathway
		else if (!isInGrid(this.gearShifter.getPosition().x, this.gearShifter.getPosition().y)) {
			fail(null);
		}
	}

	private void fail(String primaryMessage) {
		if (primaryMessage != null)
			this.resultMessage = primaryMessage;
		else
			this.resultMessage = Constants.strings.TOOLTIP_MINIGAME_GEAR_SHIFT_FAIL;
		this.result = ResultType.FAILED_WITH_VALUE;
		this.resultValue = FAIL_VALUE;
		leave();
	}

	private void win() {
		this.resultMessage = Constants.strings.TOOLTIP_MINIGAME_GEAR_SHIFT_SUCCESS;
		this.result = ResultType.PROCEEDED;
		leave();
	}
	
	private void leave(){		
		this.playResultSound();
		this.gearShifter.setCanBeDragged(false);
		this.gearShifter.setDragged(false);
		this.state = States.LEAVING_STATE;
	}

	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		batch.begin();
		this.finish.draw(batch);
		this.gearShifter.draw(batch);
		batch.end();
	}

	private boolean isInGrid(float x, float y) {
		// Vertical limits
		if (y > ROW_3 + MAX_DISTANCE_FROM_LINE || y < ROW_1 - MAX_DISTANCE_FROM_LINE)
			return false;

		// In first column
		if (x > COLUMN_1 - MAX_DISTANCE_FROM_LINE && x < COLUMN_1 + MAX_DISTANCE_FROM_LINE)
			return true;

		// In second column
		if (x > COLUMN_2 - MAX_DISTANCE_FROM_LINE && x < COLUMN_2 + MAX_DISTANCE_FROM_LINE)
			return true;

		// In second column
		if (x > COLUMN_3 - MAX_DISTANCE_FROM_LINE && x < COLUMN_3 + MAX_DISTANCE_FROM_LINE)
			return true;

		// Middle row
		if (x > COLUMN_1 - MAX_DISTANCE_FROM_LINE && x < COLUMN_3 + MAX_DISTANCE_FROM_LINE
				&& y > ROW_2 - MAX_DISTANCE_FROM_LINE && y < ROW_2 + MAX_DISTANCE_FROM_LINE)
			return true;

		return false;
	}

	private void setDifficulty(Difficulty difficulty) {
		switch (difficulty) {
		case Kiddie:
			MAX_DISTANCE_FROM_LINE = Constants.minigames.GEAR_SHIFT_MINIGAME_MAX_DISTANCE_FROM_LINE_KIDDIE;
			break;
		case Beginner:
			MAX_DISTANCE_FROM_LINE = Constants.minigames.GEAR_SHIFT_MINIGAME_MAX_DISTANCE_FROM_LINE_BEGINNER;
			break;
		case Normal:
			MAX_DISTANCE_FROM_LINE = Constants.minigames.GEAR_SHIFT_MINIGAME_MAX_DISTANCE_FROM_LINE_NORMAL;
			break;
		case Hard:
			MAX_DISTANCE_FROM_LINE = Constants.minigames.GEAR_SHIFT_MINIGAME_MAX_DISTANCE_FROM_LINE_HARD;
			break;
		case Extreme:
			MAX_DISTANCE_FROM_LINE = Constants.minigames.GEAR_SHIFT_MINIGAME_MAX_DISTANCE_FROM_LINE_EXTREME;
			break;
		default:
			throw new IllegalDifficultyException(difficulty.toString());
		}
		return;
	}

	private enum States {
		BEGINNING_STATE, DRIVING_STATE, LEAVING_STATE;
	}
}