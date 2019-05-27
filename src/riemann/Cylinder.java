package riemann;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Cylinder {
	List<Vertex> vert = new ArrayList<>();
	double radius;
	double dx;
	Vertex center;
	double thickness;
	
	Cylinder(double x, double y, double z, double r, double n, double thickness){
		this.radius=r;
		this.dx=(double)r/n;
		//temp
		this.thickness=thickness;
		center = new Vertex(x, y, z);
		
		//first circle
		/*
		double dVert=360/n;
		for(int i=0; i<n; i++) {
			double yValue=Math.sqrt(r*r-(dVert*i)*(dVert*i));
			vert.add(new Vertex(x, yValue, dVert*i));
		}
		
		//second circle
		for(int i=0; i<n; i++) {
			double yValue=Math.sqrt(r*r-(dVert*i)*(dVert*i));
			vert.add(new Vertex(x+dx,yValue, dVert*i ));
		}*/
		System.out.println(r+","+-r+","+dx);
		for(double i=-r; i<=r; i=i+dx) {
			System.out.println("Test");
			double yValue=Math.sqrt(r*r-(i)*(i));
			vert.add(new Vertex(x, yValue, i));
		}
		for(double i=r; i>=-r; i=i-dx) {
			double yValue=Math.sqrt(r*r-(i)*(i));
			vert.add(new Vertex(x, -yValue, i));
		}
		
		
		//second circle
		for(double i=-r; i<=r; i=i+dx) {
			double yValue=Math.sqrt(r*r-(i)*(i));
			vert.add(new Vertex(x+thickness, yValue, i));
		}
		for(double i=r; i>=-r; i=i-dx) {
			double yValue=Math.sqrt(r*r-(i)*(i));
			vert.add(new Vertex(x+thickness, -yValue, i));
		}
		
	}
	
	//just the area under the
	double area() {
		return radius*2*thickness;
	}
	
	double volume() {
		return radius*radius*thickness*Math.PI;
	}
	
	void draw(Graphics g2) {
		/*
		Path2D path = new Path2D.Double();
		path.moveTo(vert.get(0).y, vert.get(0).z);
		
		for(int i=0; i<vert.size(); i++)
			path.lineTo(vert.get(i).y, vert.get(i).z);

        path.closePath();
        ((Graphics2D) g2).draw(path);*/
		
		Path2D path = new Path2D.Double();
		path.moveTo(vert.get(0).x, vert.get(0).y);
		
		for(int i=0; i<vert.size()/2; i++)
			path.lineTo(vert.get(i).x, vert.get(i).y);
		
		path.moveTo(vert.get(vert.size()/2).x, vert.get(vert.size()/2).y);
		for(int i=vert.size()/2; i<vert.size(); i++)
			path.lineTo(vert.get(i).x, vert.get(i).y);

        path.closePath();
        ((Graphics2D) g2).draw(path);
        
	}
	
	void draw(Graphics g2, Matrix3 transformMatrix) {
		List<Vertex> transform = transformCube(transformMatrix);
		
		Path2D path = new Path2D.Double();
        path.moveTo(transform.get(0).x, transform.get(0).y);
		
		for(int i=0; i<vert.size()/2; i++)
			path.lineTo(transform.get(i).x, transform.get(i).y);
		
		path.moveTo(transform.get(vert.size()/2).x, transform.get(vert.size()/2).y);
		for(int i=vert.size()/2; i<vert.size(); i++)
			path.lineTo(transform.get(i).x, transform.get(i).y);
		
		for(int i=0; i<vert.size()/2; i++) {
			path.moveTo(transform.get(i).x, transform.get(i).y);
			path.lineTo(transform.get(i+vert.size()/2).x, transform.get(i+vert.size()/2).y);
		}

        path.closePath();
        ((Graphics2D) g2).draw(path);
	}
	
	List<Vertex> transformCube(Matrix3 transformMatrix){
		List<Vertex> transform = new ArrayList<>();
		for(int i=0; i<vert.size(); i++) {
			transform.add(transformMatrix.transformVert(vert.get(i)));
		}
		return transform;
	}
	
	void printVertices() {
		for(int i=0; i<vert.size(); i++) {
			System.out.println(i+": "+vert.get(i).x+","+vert.get(i).y+","+vert.get(i).z);
		}
		System.out.println("Size: "+vert.size());
		System.out.println(vert.get(0).x+","+vert.get(0).y+","+vert.get(0).z);
		System.out.println(vert.get(1442).x+","+vert.get(1442).y+","+vert.get(1442).z);
	}
	
	void printVerticesTest() {
		for(int i=0; i<vert.size()/2; i++) {
			System.out.println(i+": "+vert.get(i).x+","+vert.get(i).y+","+vert.get(i).z+" - "+
								vert.get(i+vert.size()/2).x+","+vert.get(i+vert.size()/2).y+","+vert.get(i+vert.size()/2).z);
		}
	}
}
