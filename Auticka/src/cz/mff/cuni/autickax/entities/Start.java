package cz.mff.cuni.autickax.entities;

import java.io.IOException;
import java.io.Serializable;

import com.badlogic.gdx.utils.XmlWriter;

import cz.mff.cuni.autickax.Constants;
import cz.mff.cuni.autickax.gamelogic.SubLevel;
import cz.mff.cuni.autickax.miniGames.Minigame;
import cz.mff.cuni.autickax.scene.GameScreen;

public class Start extends GameObject implements Serializable {
	private static final long serialVersionUID = 1L;

	public Start(float x, float y, GameScreen gameScreen, int type) {	
		super(x,y,gameScreen, type);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "start";
	}

	@Override
	void aditionalsToXml(XmlWriter writer) throws IOException {
		// TODO Auto-generated method stub		
	}
	
	/** Gets the texture name according to a type*/
	public static  String GetTextureName(int type){
		return Constants.START_TEXTURE_PREFIX + type;
	}
	
	@Override
	public GameObject copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTexture(int type) {
		super.setTexture(Start.GetTextureName(type));		
	}

	@Override
	public Minigame getMinigame(GameScreen gameScreen, SubLevel parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getSoundName() {
		return Constants.SOUND_NO_SOUND;
	}

}
