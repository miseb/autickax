package cz.cuni.mff.xcars.myInputListener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

import cz.cuni.mff.xcars.EditorScreen;
import cz.cuni.mff.xcars.constants.Constants;
import cz.cuni.mff.xcars.entities.GameObject;
import cz.cuni.mff.xcars.pathway.Arrow;

public class PlacedObjectsInputListener extends MyInputListener {
	private final Object representedObject;

	public PlacedObjectsInputListener(Object representedObject, EditorScreen screen) {
		super(screen);
		this.representedObject = representedObject;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		this.screen.draggedObject = representedObject;
		if(representedObject instanceof Arrow){
			this.screen.lastArrowMoved = (Arrow)representedObject;
		}
		return super.touchDown(event, x, Constants.WORLD_HEIGHT - y, pointer, button);
	}
	
	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		if (this.representedObject instanceof GameObject) {
			GameObject draggedGameObject = (GameObject) this.representedObject;
			if (!this.screen.isInWorld(draggedGameObject))
				draggedGameObject.remove();

		} else if (this.representedObject instanceof Arrow) {
			Arrow draggedArrow = (Arrow) this.representedObject;
			if (!this.screen.isInWorld(draggedArrow))
				draggedArrow.remove();
		}
		super.touchUp(event, x, y, pointer, button);
	}
}
