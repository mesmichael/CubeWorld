package net.clearwaterinc.cubeworld.render;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileInputStream;
import java.io.IOException;

import net.clearwaterinc.cubeworld.CubeWorld;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class RenderWorld {
	float yRotation = 0;
	float xRotation = 0;
    private Texture texture;
    public CubeWorld cw;
	
	public void startRender(){// Clear the screen and depth buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		cw = new CubeWorld();
		
		drawTextureCube();
		//drawCube(tempX, tempY, 0, Colors.blue);
	}
	
	public void drawTextureCube(){
		// Clear The Screen And The Depth Buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		// R,G,B,A Set The Color To Blue One Time Only
		glColor3f(0.5f, 0.5f, 1.0f);

		glTranslatef(0.0f, -10.108f, 0.0f);
		// draw quad
		glPushMatrix();
		
		if(texture == null)
			loadTexture(cw.getDir() +  "/res/image.png");
		
		texture.bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		// top
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); glVertex3f(-10, -10, 10);
		glTexCoord2f(0, 1); glVertex3f(10, -10, 10);
		glTexCoord2f(1, 1); glVertex3f(10, 10, 10);
		glTexCoord2f(1, 0); glVertex3f(-10, 10, 10);
		glEnd();
		
		//left
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); glVertex3f(-10, -10, -10);
		glTexCoord2f(0, 1); glVertex3f(-10, -10, 10);
		glTexCoord2f(1, 1); glVertex3f(-10, 10, 10);
		glTexCoord2f(1, 0); glVertex3f(-10, 10, -10);
		glEnd();
		
		//right
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); glVertex3f(10, -10, -10);
		glTexCoord2f(0, 1); glVertex3f(10, 10, -10);
		glTexCoord2f(1, 1); glVertex3f(10, 10, 10);
		glTexCoord2f(1, 0); glVertex3f(10, -10, 10);
		glEnd();
		
		//front
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); glVertex3f(-10, -10, -10);
		glTexCoord2f(0, 1); glVertex3f(10, -10, -10);
		glTexCoord2f(1, 1); glVertex3f(10, -10, 10);
		glTexCoord2f(1, 0); glVertex3f(-10, -10, 10);
		glEnd();
		
		//back
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); glVertex3f(10, 10, -10);
		glTexCoord2f(0, 1); glVertex3f(-10, 10, -10);
		glTexCoord2f(1, 1); glVertex3f(-10, 10, 10);
		glTexCoord2f(1, 0); glVertex3f(10, 10, 10);
		glEnd();
		
		//bottom
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); glVertex3f(10, -10, -10);
		glTexCoord2f(0, 1); glVertex3f(-10, -10, -10);
		glTexCoord2f(1, 1); glVertex3f(-10, 10, -10);
		glTexCoord2f(1, 0); glVertex3f(10, 10, -10);
		glEnd();

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glPopMatrix();
		glTranslatef(0.0f, 10.108f, 0.0f);
	}
	
	public void drawCube(float x, float y, float z, float[] color){
		// Color rect based off of how many args are given in the float[]
		if(color.length == 3){
			glColor3f(color[0], color[1], color[2]);	
		}else if(color.length == 4){
			glColor4f(color[0], color[1], color[2], color[3]);
		}else{
			System.out.print("Error at RenderWorld.drawRect: drawRect was called without too many or too few colors.");
		}
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);          // Clear The Screen And The Depth Buffer

		// Setting the color of each side. If I don't then it will look like a weird blob 
        glLoadIdentity();                          // Reset The Current Modelview Matrix
        glTranslatef(0.0f,0.0f,-10.0f);             // Move Right 1.5 Units And Into The Screen 6.0
        //glRotatef(rquad, x, y, z);
        glBegin(GL_QUADS);                        // Draw A Quad
            glColor3f(0.0f,1.0f,0.0f);             // Set The Color To Green
            glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Top)
            glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Top)
            glVertex3f(-1.0f, 1.0f, 1.0f);         // Bottom Left Of The Quad (Top)
            glVertex3f( 1.0f, 1.0f, 1.0f);         // Bottom Right Of The Quad (Top)
            
            glColor3f(1.0f,0.5f,0.0f);             // Set The Color To Orange
            glVertex3f( 1.0f,-1.0f, 1.0f);         // Top Right Of The Quad (Bottom)
            glVertex3f(-1.0f,-1.0f, 1.0f);         // Top Left Of The Quad (Bottom)
            glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Bottom)
            glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Bottom)
            
            glColor3f(1.0f,0.0f,0.0f);             // Set The Color To Red
            glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Front)
            glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Front)
            glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Front)
            glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Front)
            
            glColor3f(1.0f,1.0f,0.0f);             // Set The Color To Yellow
            glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Back)
            glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Back)
            glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Back)
            glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Back)
            
            glColor3f(0.0f,0.0f,1.0f);             // Set The Color To Blue
            glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Left)
            glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Left)
            glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Left)
            glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Left)
            
            glColor3f(1.0f,0.0f,1.0f);             // Set The Color To Violet
            glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Right)
            glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Right)
            glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Right)
            glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Right)
        glEnd();                                       // Done Drawing The Quad
        glTranslatef(0, 0, 10);             // Move Right 1.5 Units And Into The Screen 6.0
	}
	
	public void drawPyramid(int x, int y, int z, float[] color){
        glTranslatef(-1.5f,0.0f,-6.0f);                // Move Left 1.5 Units And Into The Screen 6.0
        //glRotatef(rtri,0.0f,1.0f,0.0f);                // Rotate The Triangle On The Y axis ( NEW )
        glBegin(GL_TRIANGLES);                    // Drawing Using Triangles
            glColor3f(1.0f,0.0f,0.0f);             // Red
            glVertex3f( 0.0f, 1.0f, 0.0f);         // Top Of Triangle (Front)
            glColor3f(0.0f,1.0f,0.0f);             // Green
            glVertex3f(-1.0f,-1.0f, 1.0f);         // Left Of Triangle (Front)
            glColor3f(0.0f,0.0f,1.0f);             // Blue
            glVertex3f( 1.0f,-1.0f, 1.0f);         // Right Of Triangle (Front)
            glColor3f(1.0f,0.0f,0.0f);             // Red
            glVertex3f( 0.0f, 1.0f, 0.0f);         // Top Of Triangle (Right)
            glColor3f(0.0f,0.0f,1.0f);             // Blue
            glVertex3f( 1.0f,-1.0f, 1.0f);         // Left Of Triangle (Right)
            glColor3f(0.0f,1.0f,0.0f);             // Green
            glVertex3f( 1.0f,-1.0f, -1.0f);            // Right Of Triangle (Right)
            glColor3f(1.0f,0.0f,0.0f);             // Red
            glVertex3f( 0.0f, 1.0f, 0.0f);         // Top Of Triangle (Back)
            glColor3f(0.0f,1.0f,0.0f);             // Green
            glVertex3f( 1.0f,-1.0f, -1.0f);            // Left Of Triangle (Back)
            glColor3f(0.0f,0.0f,1.0f);             // Blue
            glVertex3f(-1.0f,-1.0f, -1.0f);            // Right Of Triangle (Back)
            glColor3f(1.0f,0.0f,0.0f);             // Red
            glVertex3f( 0.0f, 1.0f, 0.0f);         // Top Of Triangle (Left)
            glColor3f(0.0f,0.0f,1.0f);             // Blue
            glVertex3f(-1.0f,-1.0f,-1.0f);         // Left Of Triangle (Left)
            glColor3f(0.0f,1.0f,0.0f);             // Green
            glVertex3f(-1.0f,-1.0f, 1.0f);         // Right Of Triangle (Left)
        glEnd();                                       // Finished Drawing The Triangle
	}
	
	public void loadTexture(String path){
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(path));
			System.out.println("Texture loaded: "+texture);
			System.out.println(">> Image width: "+texture.getImageWidth());
			System.out.println(">> Image height: "+texture.getImageHeight());
			System.out.println(">> Texture width: "+texture.getTextureWidth());
			System.out.println(">> Texture height: "+texture.getTextureHeight());
			System.out.println(">> Texture ID: "+texture.getTextureID());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
