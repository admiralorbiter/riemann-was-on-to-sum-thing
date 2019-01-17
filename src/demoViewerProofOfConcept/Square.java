package demoViewerProofOfConcept;

import java.awt.Color;

public class Square {

	Vertex v1;
	Vertex v2;
	Vertex v3;
	Vertex v4;
	Color color;
	
	Square(Vertex v1, Vertex v2, Vertex v3, Vertex v4, Color color){
		this.v1=v1;
		this.v2=v2;
		this.v3=v3;
		this.v4=v4;
		this.color=color;
	}
	
	//Not sure I can use this function since I need a way to pick the oreintation of the side
	Square(Vertex v1, double sideLength, Color color){
		this.v1=v1;
		this.v2=new Vertex(v1.x+sideLength, v1.y, v1.z);
		this.v3=new Vertex(v1.x, v1.y+sideLength, v1.z);
		this.v4=new Vertex(v1.x+sideLength, v2.y+sideLength, v1.z);
		this.color=color;
	}
	
}
