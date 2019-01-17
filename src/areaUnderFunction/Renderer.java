package areaUnderFunction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Renderer {
	Graphics2D g2;
	Color background;
	double width;
	double height;
	Renderer(Graphics g, double width, double height){
		g2 = (Graphics2D) g;
		background=Color.black;		//can use user input for this
		this.height=height;
		this.width=width;
		
		g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, (int)width, (int)height);
        //g2.translate(50, height-50);
        g2.translate(50, height/2);
        g2.setColor(Color.WHITE);
        
	}
	
	void draw(Cube cube, double rot, double pitch) {
		//g2.setColor(Color.BLACK);
        //g2.fillRect(0, 0, (int)width, (int)height);

        // rendering magic will happen here
        
        
    	Matrix3 rotateMatrix = new Matrix3(new double[] {Math.cos(rot), 0, -Math.sin(rot),
    														0, 1, 0,
    														Math.sin(rot), 0, Math.cos(rot)});
    	
    	Matrix3 pitchMatrix = new Matrix3(new double[] {1, 0, 0,
    											0, Math.cos(pitch), Math.sin(pitch),
    											0, -Math.sin(pitch), Math.cos(pitch)});
    	  
    	Matrix3 transformMatrix=rotateMatrix.multiply(pitchMatrix);
    	cube.drawCube(g2, transformMatrix);
	}
	
}
