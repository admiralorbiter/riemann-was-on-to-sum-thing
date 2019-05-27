package riemann;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SolidFrame extends JPanel{
	
	double scaleX=0;
	double scaleY=0;
	double lowerBound;
	double upperBound;
	int n;
	
	List<Cylinder> cylinders = new ArrayList<Cylinder>();
	JSlider headingSlider = new JSlider(0, 360, 180);
	JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
	JTextField text = new JTextField();
	
	public SolidFrame(double lowerBound, double upperBound, int n) {
		this.lowerBound=lowerBound;
		this.upperBound=upperBound;
		this.n=n;
		
		setLayout(new BorderLayout());
		add(headingSlider, BorderLayout.SOUTH);
		add(pitchSlider, BorderLayout.EAST);
		add(text, BorderLayout.NORTH);
		createCylinders(getWidth(), getHeight());
		headingSlider.addChangeListener(e->repaint());
	    pitchSlider.addChangeListener(e->repaint());
	    text.addPropertyChangeListener(e->repaint());
	    repaint();
	}
	
	public void run() {
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
	   	Graphics g2 = (Graphics2D) g;
	   	Renderer render = new Renderer(g, getWidth(), getHeight());		//not sure i need to set the width and height each time. could have initialize just width and height and send down graphics
	
	   	double rot=Math.toRadians(headingSlider.getValue());
	      double pitch=Math.toRadians(pitchSlider.getValue());
	
	   	Matrix3 rotateMatrix = new Matrix3(new double[] {Math.cos(rot), 0, -Math.sin(rot),
	   														0, 1, 0,
	   														Math.sin(rot), 0, Math.cos(rot)});
	   	
	   	Matrix3 pitchMatrix = new Matrix3(new double[] {1, 0, 0,
	   											0, Math.cos(pitch), Math.sin(pitch),
	   											0, -Math.sin(pitch), Math.cos(pitch)});
	   	  
	   	Matrix3 transformMatrix=rotateMatrix.multiply(pitchMatrix);
	   	System.out.println("---------------------------------------------------------------------------------");
	   	for(int i=0; i<cylinders.size(); i++)
	   		cylinders.get(i).draw(g2, transformMatrix);
	 }
	  
	  private void createCylinders(double width, double height) {

	    	//need to generalize the scaling for different window sizes
	    	
	    	double maxY=f(lowerBound);
	    	double maxX=lowerBound;
	    	double dx=(double)(lowerBound+upperBound)/n;
	    	//need a function to find the max
	    	for(double i=lowerBound; i<=upperBound; i=i+dx) {
	    		if(f(i)>maxY)
	    			maxY=f(i);
	    		
	    		if(i>maxX)
	    			maxX=i;
	    	}
	    	double size=height/2-100;
	    	scaleY=maxY/(size);
	    	scaleX=(width-100)/maxX;

	    	for(double x=lowerBound; x<=upperBound; x=x+dx) {
	    		System.out.println(x);
	    		if(f(x)!=0)
	    			cylinders.add(new Cylinder(x*scaleX, -25, 0, f(x)/scaleY, 360, dx*scaleX));
	    	}
	    	System.exit(0);
	   }
	  
	  double f(double x) {
	    	return x*x;
	    }
}
