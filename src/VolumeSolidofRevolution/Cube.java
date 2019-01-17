package VolumeSolidofRevolution;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Cube {
	
	List<Vertex> vert = new ArrayList<>();
	
	double height;
	double dx;
	
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
		this.height=sideLength;
		this.dx=sideLength;
	}
	
	Cube(double x, double y, double z, double height, double dx){
		Vertex v1=new Vertex(x, y, z);
		vert.add(v1);
		
		//starts in top left and goes counter clockwise
		vert.add(new Vertex(x, y+height, z));		//v2
		vert.add(new Vertex(x+dx, y+height, z));	//v3
		vert.add(new Vertex(x+dx, y, z));		//v4
		vert.add(new Vertex(x, y, z+dx));		//v5
		vert.add(new Vertex(x, y+height, z+dx));	//v6
		vert.add(new Vertex(x+dx, y+height, z+dx));	//v7
		vert.add(new Vertex(x+dx, y, z+dx));	//v8
		this.height=height;
		this.dx=dx;
	}
	
	List<Vertex> transformCube(Matrix3 transformMatrix){
		List<Vertex> transform = new ArrayList<>();
		for(int i=0; i<vert.size(); i++) {
			transform.add(transformMatrix.transformVert(vert.get(i)));
		}
		return transform;
	}
	
	void drawCube(Graphics g2, Matrix3 transformMatrix) {
		List<Vertex> transform = transformCube(transformMatrix);
		Path2D path = new Path2D.Double();
        path.moveTo(transform.get(0).x, transform.get(0).y);
        for(int i=1; i<=3; i++) {
        	path.lineTo(transform.get(i).x, transform.get(i).y);
        }
        path.lineTo(transform.get(0).x, transform.get(0).y);
        
        path.moveTo(transform.get(4).x, transform.get(4).y);
        for(int i=5; i<=7; i++) {
        	path.lineTo(transform.get(i).x, transform.get(i).y);
        }
        path.lineTo(transform.get(4).x, transform.get(4).y);
        
        for(int i=0; i<=3; i++) {
        	path.moveTo(transform.get(i).x, transform.get(i).y);
        	path.lineTo(transform.get(i+4).x, transform.get(i+4).y);
        }
        
        
        path.closePath();
        ((Graphics2D) g2).draw(path);

	}
	//just the area under the function 
	double area() {
		return height*dx;
	}
	
	//TESTING Purposes
	void printVertices() {
		for(int i=0; i<vert.size(); i++)
			System.out.println(vert.get(i).x+", "+vert.get(i).y+", "+vert.get(i).z);
	}
	
}
