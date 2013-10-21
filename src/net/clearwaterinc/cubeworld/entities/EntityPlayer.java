package net.clearwaterinc.cubeworld.entities;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class EntityPlayer {
	
	public float yaw = 0.0f, pitch = 0.0f;
	
	public Vector3f position = null;
	
	public static float x, y, z;
	
	public EntityPlayer(float x, float y, float z)
    {
        position = new Vector3f(x, y, z);
    }
	
    public void yaw(float amount){
        yaw += amount;
    }
 
    public void pitch(float amount){
        pitch += amount;
    }

    public void walkForward(float distance){
    	if(position.z > 10.108f){
    		position.y += distance * (float)Math.sin(Math.toRadians(pitch));
    		position.x += distance * (float)Math.cos(Math.toRadians(yaw - 90));
    	}else if(position.z < -10.108f){
    		position.y += distance * (float)Math.sin(Math.toRadians(pitch));
    		position.x += distance * (float)Math.cos(Math.toRadians(yaw - 90));
    	}else{
    		position.x -= distance * (float)Math.sin(Math.toRadians(yaw));
    		position.z += distance * (float)Math.cos(Math.toRadians(yaw));
    		
    	}
    }

    public void walkBackwards(float distance){
    	position.x += distance * (float)Math.sin(Math.toRadians(yaw));
    	position.z -= distance * (float)Math.cos(Math.toRadians(yaw));
    }

    public void strafeLeft(float distance){
    	position.x -= distance * (float)Math.sin(Math.toRadians(yaw - 90));
    	position.z += distance * (float)Math.cos(Math.toRadians(yaw - 90));
    }

    public void strafeRight(float distance){
    	position.x -= distance * (float)Math.sin(Math.toRadians(yaw + 90));
    	position.z += distance * (float)Math.cos(Math.toRadians(yaw + 90));
    }
    
    public void lookThrough()
    {
    	if(position.z > 10.108f){
    		GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
    		GL11.glRotatef(yaw,   0.0f, 0.0f, 1.0f);
    	}else{
    		GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
    		GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
    	}
        //translate to the position vector's location
        GL11.glTranslatef(position.x, position.y, position.z);
    }
    
    public static void setX(float X){x = X;}
    public static void setY(float Y){y = Y;}
    public static void setZ(float Z){z = Z;}
    
    public static float getX(){return x;}
    public static float getY(){return y;}
    public static float getZ(){return z;}
	
	public EntityPlayer(){}
}