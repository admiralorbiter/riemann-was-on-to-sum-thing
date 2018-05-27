package viewer;

public class Matrix3 {
	
	double[] values;
	
	Matrix3(double[] values){
		this.values=values;
	}

	Vertex transformVert(Vertex in) {
		double xOut;
		double yOut;
		double zOut;
		
		xOut=in.x*values[0]+in.y*values[3]+in.z*values[6];
		yOut=in.x*values[1]+in.y*values[4]+in.z*values[7];
		zOut=in.x*values[2]+in.y*values[5]+in.z*values[8];
		
		Vertex out=new Vertex(xOut, yOut, zOut);
		
		return out;
	}
	
	Vertex translate(double x, double y, Vertex in) {
		Vertex out=new Vertex(in.x+x, in.y+y, in.z);
		
		return out;
	}
}
