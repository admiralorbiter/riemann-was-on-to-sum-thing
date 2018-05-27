package viewer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Cube {
	/*
	List<Square> squ = new ArrayList<>();
	
	Cube(double x, double y, double z, double sideLength){
		Vertex v1=new Vertex(x, y, z);
		double sl=sideLength;
		Square side;
		
		side=new Square(v1,
						new Vertex(v1.x+sl, v1.y, v1.z),
						new Vertex(v1.x, v1.y+sl, v1.z),
						new Vertex(v1.x+sl, v1.y+sl, v1.z),
						Color.red);
		squ.add(side);
		
		side=new Square(v1,
				new Vertex(v1.x+sl, v1.y, v1.z),
				new Vertex(v1.x, v1.y+sl, v1.z),
				new Vertex(v1.x+sl, v1.y+sl, v1.z),
				Color.red);
		
		
	}*/
	
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
	
	void printVertices() {
		for(int i=0; i<vert.size(); i++)
			System.out.println(vert.get(i).x+", "+vert.get(i).y+", "+vert.get(i).z);
	}
	
}
