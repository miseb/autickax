package cz.mff.cuni.autickax.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import cz.mff.cuni.autickax.Autickax;

public abstract class BaseScreen implements Screen {	
	private Autickax game;
	protected float stageWidth;
	protected float stageHeight;
	protected final Stage stage;
	protected SpriteBatch batch;

	public BaseScreen() {
		setGame(Autickax.getInstance());
		stageWidth = Gdx.graphics.getWidth();
		stageHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();

		stage = new Stage(stageWidth, stageHeight, false); // https://github.com/libgdx/libgdx/wiki/Scene2d
		
		this.stage.addListener(new ScreenInputListener(this));
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
	}
	
	protected void clearScreenWithColor() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void render(float delta) {
		this.clearScreenWithColor();
		
		stage.act(delta); // don't forget to advance the stage ( input + actions
							// )
		stage.draw(); // and also display it :)
	}

	@Override
	public void resize(int width, int height) {
		// stage.setViewport(width, height, true);
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	protected abstract void onBackKeyPressed();

	public Autickax getGame() {
		return game;
	}

	public void setGame(Autickax game) {
		this.game = game;
	}
	

}
