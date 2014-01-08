package cz.mff.cuni.autickax;

public final class Constants {

	
	//
	// PATHWAY
	//
	
	/** Determines where is located start on the curve in percents*/
	public static final float START_POSITION_IN_CURVE = 0;
	/** Determines where is located finish on the curve in percents*/
	public static final float FINISH_POSITION_IN_CURVE = 1;
	
	/** Determines maximal total distance from pathway (in pixels). Beyond it is no pathway.*/
	public static final int MAX_DISTANCE_FROM_PATHWAY = 60;
	
	/** Determines maximal distance of proper surface  from pathway (in pixels)*/
	public static final int MAX_SURFACE_DISTANCE_FROM_PATHWAY = 30;
	
	/** Number of waypoints that check if the player raced through all the track */
	public static final int WAYPOINTS_COUNT = 40;
	
	/** Amount of parts used between two points during the counting of distances in DistanceMap*/
	public static final int LINE_SEGMENTATION = 100; 
	
	
	
	//
	// SPEED REGULATION
	//
	
	/** The speed is effected by this constant when the car is out of proper surface of the pathway */
	public static final float OUT_OF_SURFACE_PENALIZATION_FACTOR = 3;
	
	
	
	//
	// DRAWING PLACEMENTS
	//
	
	//TODO: dont use this
	public static final String 	BUTTON_PLAY = "play";
	
	
	
	//
	// SOUNDS & MUSIC
	//
	
	//TODO: dont use this
	public static final String 	SOUND_JUMP = "jump";
	//TODO: dont use this
	public static final String 	SOUND_HIT = "hit";	
	
	/** Default value of volume */
	public static final float 	MUSIC_DEFAULT_VOLUME = 0.5f;
	
	
	
	//
	// INPUT & COORDINATES
	//
	/** Determines ideal world width. If the resolution is different, coordinates are stretched. */ 
	public final static float WORLD_WIDTH = 800;
	/** Determines ideal world height. If the resolution is different, coordinates are stretched. */
	public final static float WORLD_HEIGHT = 480; 

	

	//
	// GAME OBJECTS
	//
	public static final String 	CAR_TYPE_0_TEXTURE_NAME = "car";
	public static final int  	CAR_TYPE_0_WIDTH = 100;
	public static final int 	CAR_TYPE_0_HEIGHT = 63;
	
	public static final String 	FINISH_TYPE_1_TEXTURE_NAME = "finish";
	public static final int  	FINISH_TYPE_1_WIDTH = 30;
	public static final int 	FINISH_TYPE_1_HEIGHT = 30;
	
	public static final String 	MUD_TYPE_1_TEXTURE_NAME = "mud";
	public static final int  	MUD_TYPE_1_WIDTH = 30;
	public static final int 	MUD_TYPE_1_HEIGHT = 30;
	
	public static final String 	START_TYPE_1_TEXTURE_NAME = "start";
	public static final int  	START_TYPE_1_WIDTH = 30;
	public static final int 	START_TYPE_1_HEIGHT = 30;
	
	public static final String 	STONE_TYPE_1_TEXTURE_NAME = "stone";
	public static final int  	STONE_TYPE_1_WIDTH = 30;
	public static final int 	STONE_TYPE_1_HEIGHT = 30;
	
	public static final String 	TREE_TYPE_1_TEXTURE_NAME = "tree";
	public static final int  	TREE_TYPE_1_WIDTH = 30;
	public static final int 	TREE_TYPE_1_HEIGHT = 30;
}
