package net.clearwaterinc.cubeworld;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import net.clearwaterinc.cubeworld.entities.EntityPlayer;
import net.clearwaterinc.cubeworld.render.RenderWorld;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class CubeWorld {
	public boolean mouseLeft, mouseRight;
	public static boolean moveLeft, moveRight, moveUp, moveDown;
	
	public float rotation = 0;
	
	public int mouseX, mouseY;
	public int FPS;
	public int delta;
	
	public long lastFrame, lastFPS;
	
	public String OS = null;
	private static String OSv = System.getProperty("os.name").toLowerCase();

	public EntityPlayer entityPlayer;
	public RenderWorld renderWorld;
	
	public void start(){
		try{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
			renderWorld = new RenderWorld();
		}catch (LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		initGL();
		getDelta();
		lastFPS = getTime();
		entityPlayer = new EntityPlayer(0, 0, 0);

    	float dx = 0.0f;
    	float dy = 0.0f;
    	float mouseSensitivity = 0.15f;

    	Mouse.setGrabbed(true);
		while(!Display.isCloseRequested()){
			
    		//distance in mouse movement from the last getDX() call.
    		dx = Mouse.getDX();
    		//distance in mouse movement from the last getDY() call.
    		dy = Mouse.getDY();
    		
    		//control camera yaw from x movement from the mouse
    		entityPlayer.yaw(dx * mouseSensitivity);
    		//control camera pitch from y movement from the mouse
    		entityPlayer.pitch(-dy * mouseSensitivity);
			
			delta = getDelta();
			
			updateFPS();
			renderWorld.startRender();
			
    		//set the modelview matrix back to the identity
    		GL11.glLoadIdentity();
    		//look through the camera before you draw anything
    		entityPlayer.lookThrough();
			
			runChecks();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	/**
	 * Initialize OpenGL view
	 **/
	public void initGL(){
	       GL11.glEnable(GL11.GL_LIGHTING);
	        GL11.glMatrixMode(GL11.GL_PROJECTION);
	        GL11.glLoadIdentity();
	        GLU.gluPerspective(45.0f, ((float) 800) / ((float) 600), 0.1f, 100.0f);
	        GL11.glMatrixMode(GL11.GL_MODELVIEW);
	        GL11.glLoadIdentity();

	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glShadeModel(GL11.GL_SMOOTH);
	        GL11.glClearColor(0.1f, 0.2f, 0.7f, 0.0f);
	        GL11.glClearDepth(1.0f);
	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	        GL11.glDepthFunc(GL11.GL_LEQUAL);
	        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
	        float lightAmbient[] = {0.5f, 0.5f, 0.5f, 1.0f};  // Ambient Light Values
	        float lightDiffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};      // Diffuse Light Values
	        float lightPosition[] = {255.0f, 255.0f, 255.0f, 255.0f}; // Light Position

	        ByteBuffer temp = ByteBuffer.allocateDirect(16);
	        temp.order(ByteOrder.nativeOrder());
	        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_AMBIENT, (FloatBuffer) temp.asFloatBuffer().put(lightAmbient).flip());              // Setup The Ambient Light
	        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, (FloatBuffer) temp.asFloatBuffer().put(lightDiffuse).flip());              // Setup The Diffuse Light
	        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, (FloatBuffer) temp.asFloatBuffer().put(lightPosition).flip());         // Position The Light
	        GL11.glEnable(GL11.GL_LIGHT2);                          // Enable Light One
	}
	
	/**
	 * Runs every tick to check mouse X & Y, checks keys, etc.
	 */
	public void runChecks(){
		initKeys();
		monitorInput();
	}
	
	public void initKeys(){
		mouseLeft = Mouse.isButtonDown(0);
		mouseRight = Mouse.isButtonDown(1);
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
	}
	
	public void monitorInput(){
		float dt = 0.0005f;
    	float movementSpeed = 10.0f;
		if (Keyboard.isKeyDown(Keyboard.KEY_W))//move forward
		{
			entityPlayer.walkForward(movementSpeed*dt);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S))//move backwards
		{
			entityPlayer.walkBackwards(movementSpeed*dt);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A))//strafe left
		{
			entityPlayer.strafeLeft(movementSpeed*dt);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D))//strafe right
		{
			entityPlayer.strafeRight(movementSpeed*dt);
		}
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
					Display.destroy();
				}
			}else{

			}
		}
	}
	
	public String getDir(){
        String dir = System.getProperty("user.home", ".");
        File save = new File(dir, "cubeworld/");
		
		if (isWindows()) {
			String appData = System.getenv("APPDATA");
			
			if (appData != null){
				save = new File(appData, "." + "cubeworld" + '/');
			}else{
				save = new File(dir, '.' + "cubeworld" + '/');
			}
		} else if (isMac()) {
			save = new File(dir, "Library/Application Support/" + "cubeworld");
		} else if (isUnix()) {
			save = new File(dir, '.' + "cubeworld" + '/');
		} else if (isSolaris()) {
			save = new File(dir, "cubeworld" + '/');
		} else {
			save = new File(dir, "cubeworld/");
		}
		
		return save.toString();
	}
	
	public static boolean isWindows() {
		 
		return (OSv.indexOf("win") >= 0);
 
	}
 
	public static boolean isMac() {
 
		return (OSv.indexOf("mac") >= 0);
 
	}
 
	public static boolean isUnix() {
 
		return (OSv.indexOf("nix") >= 0 || OSv.indexOf("nux") >= 0 || OSv.indexOf("aix") > 0 );
 
	}
 
	public static boolean isSolaris() {
 
		return (OSv.indexOf("sunos") >= 0);
 
	}
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public int getDelta(){
		long time = getTime();
		int delta = (int)(time - lastFrame);
		lastFrame = time;
		
		return delta;
	}
	
	public int getFPS(){
		return FPS;
	}
	
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + FPS + " X: " + entityPlayer.position.x + "Y: " + entityPlayer.position.y + "Z: " + entityPlayer.position.z);
			FPS = 0;
			lastFPS += 1000;
		}
		FPS++;
	}
}