package viewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Cube {
	
	List<Vertex> vert = new ArrayList<>();
	
	Cube(double x, double y, double z, double sideLength){
		double sl=sideLength;
		Vertex v1=new Vertex(x, y, z);
		vert.add(v1);
		
		//starts in top left and goes counter clockwise
		vert.add(new Vertex(x, y+sl, z));		//v2
		vert.add(new Vertex(x+sl, y+sl, z));	//v3
		vert.add(new Vertex(x+sl, y, z));		//v4
		vert.add(new Vertex(x, y, z+sl));		//v5
		vert.add(new Vertex(x, y+sl, z+sl));	//v6
		vert.add(new Vertex(x+sl, y+sl, z+sl));	//v7
		vert.add(new Vertex(x+sl, y, z+sl));	//v8
	}
	
	void drawCube(Graphics g2) {
		Path2D path = new Path2D.Double();
        path.moveTo(vert.get(0).x, vert.get(0).y);
        for(int i=1; i<=3; i++) {
        	path.lineTo(vert.get(i).x, vert.get(i).y);
        }
        path.lineTo(vert.get(0).x, vert.get(0).y);
        
        path.moveTo(vert.get(4).x, vert.get(4).y);
        for(int i=5; i<=7; i++) {
        	path.lineTo(vert.get(i).x, vert.get(i).y);
        }
        path.lineTo(vert.get(4).x, vert.get(4).y);
        
        for(int i=0; i<=3; i++) {
        	path.moveTo(vert.get(i).x, vert.get(i).y);
        	path.lineTo(vert.get(i+4).x, vert.get(i+4).y);
        }
        
        
        path.closePath();
        ((Graphics2D) g2).draw(path);
        
        /*
        Utility u = new Utility();
        u.color(g2, vert.get(0), vert.get(1), vert.get(2), vert.get(3), Color.red);
        u.color(g2, vert.get(0), vert.get(1), vert.get(5), vert.get(4), Color.blue);
        u.color(g2, vert.get(4), vert.get(5), vert.get(6), vert.get(7), Color.green);
        u.color(g2, vert.get(3), vert.get(2), vert.get(6), vert.get(7), Color.yellow);
        */
	}
	
	void transformCube(Matrix3 trans) {
		for(int i=0; i<vert.size(); i++)
			vert.set(i, trans.transformVert(vert.get(i)));
	}
	
	//Test Function
	void printVertices() {
		for(int i=0; i<vert.size(); i++)
			System.out.println(vert.get(i).x+", "+vert.get(i).y+", "+vert.get(i).z);
	}
	
}
