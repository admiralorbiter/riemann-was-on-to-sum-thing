package areaUnderFunction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Utility {
	
	Utility(){
		
	}
	
	void color(Graphics g2, Vertex v1, Vertex v2, Vertex v3, Vertex v4, Color color) {
		
		g2.setColor(color);
		Path2D path = new Path2D.Double();
		path.moveTo(v1.x, v1.y);
		for(double i=v1.x; i<v4.x; i++) {
			path.moveTo(i, v1.y);
			path.lineTo(i, v3.y);
		}
		path.closePath();
		((Graphics2D) g2).draw(path);
	}
}
