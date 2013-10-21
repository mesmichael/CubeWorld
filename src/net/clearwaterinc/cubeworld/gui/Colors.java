package net.clearwaterinc.cubeworld.gui;

public class Colors {
	/**
	 * Declare float arrays with 3 floats for OpenGL coloring
	 */
	public static float[] white  = {1.0f, 1.0f, 1.0f};
	public static float[] black  = {0.0f, 0.0f, 0.0f};
	public static float[] red    = {1.0f, 0.0f, 0,0f};
	public static float[] orange = {1.0f, 0.5f, 0.0f};
	public static float[] yellow = {1.0f, 1.0f, 0.0f};
	public static float[] green  = {0.0f, 1.0f, 0.0f};
	public static float[] blue   = {0.0f, 0.0f, 1.0f};
	public static float[] purple = {0.5f, 0.0f, 1.0f};
	public static float[] pink   = {1.0f, 0.0f, 0.5f};
	
	/**
	 * Declare float arrays with 3 floats for OpenGL coloring
	 */
	public static float[] whiteA  = {1.0f, 1.0f, 1.0f, 0.5f};
	public static float[] blackA  = {0.0f, 0.0f, 0.0f, 0.5f};
	public static float[] redA    = {1.0f, 0.0f, 0,0f, 0.5f};
	public static float[] orangeA = {1.0f, 0.5f, 0.0f, 0.5f};
	public static float[] yellowA = {1.0f, 1.0f, 0.0f, 0.5f};
	public static float[] greenA  = {0.0f, 1.0f, 0.0f, 0.5f};
	public static float[] blueA   = {0.0f, 0.0f, 1.0f, 0.5f};
	public static float[] purpleA = {0.5f, 0.0f, 1.0f, 0.5f};
	public static float[] pinkA   = {1.0f, 0.0f, 0.5f, 0.5f};
	
	public static float[] fromRGBA(int red, int green, int blue, int alpha){
		float[] rgba = {1.0f, 1.0f, 1.0f, 1.0f};
		rgba[0] = (float)red   / 255;
		rgba[1] = (float)green / 255;
		rgba[2] = (float)blue  / 255;
		rgba[3] = (float)alpha / 255;
		return rgba;
	}
	
	public static float[] fromRGB(int red, int green, int blue){
		float[] rgb = {1.0f, 1.0f, 1.0f};
		rgb[0] = (float)red   / 255;
		rgb[1] = (float)green / 255;
		rgb[2] = (float)blue  / 255;
		return rgb;
	}
	
	public static float[] fromHex(int hexColor){
		float[] hexRGB = {1.0f, 1.0f, 1.0f};
	    hexRGB[0] = ((float) (hexColor >> 16 & 0xff) / 255F);
	    hexRGB[1] = ((float) (hexColor >> 8 & 0xff) / 255F);
	    hexRGB[2] = ((float) (hexColor) / 255F);
	    try{
	    	hexRGB[3] = ((float) (hexColor >> 24 & 0xff) / 255F);
	    }catch(Exception e){}
    	return hexRGB;
	}
}
