package areaUnderFunction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
	
	//static Cube cube = new Cube(-50, -50, 0, 100);
	//x, y, z, height, dx
	
	static double startingBound=0;
	static double endingBound=3.14;
	static double dx=.01;
	
	static Cube cube = new Cube(-50, -50, 0, 100, 50);
	static Matrix3 rotateMatrix;
	static Matrix3 pitchMatrix;
	static double maxY=0;
	static double scaleY=100;
	static double scaleX=100;
	static double maxX=0;
	
	static String value="initial";
	static String oldValue="initial";
	
	static List<Cube> cubes = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Riemann was on to sum thing");
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        // slider to control horizontal rotation
        JSlider headingSlider = new JSlider(0, 360, 180);
        pane.add(headingSlider, BorderLayout.SOUTH);

        // slider to control vertical rotation
        JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        pane.add(pitchSlider, BorderLayout.EAST);

        //control values
        JTextField text = new JTextField();
        pane.add(text, BorderLayout.NORTH);
        
        frame.setSize(800, 800);
        
        createCubes(frame.getWidth(), frame.getHeight());
        
        // panel to display render results
        JPanel renderPanel = new JPanel() {
                public void paintComponent(Graphics g) {
                	Graphics g2 = (Graphics2D) g;
                	Renderer render = new Renderer(g, getWidth(), getHeight());		//not sure i need to set the width and height each time. could have initialize just width and height and send down graphics
                	 double rot=Math.toRadians(headingSlider.getValue());
                     double pitch=Math.toRadians(pitchSlider.getValue());
                     //render.draw(cube, rot, pitch);
                     
                     //testing purposes
                     //g.drawString("Edge", -300, -300);
                     
                     for(int i=0; i<cubes.size(); i++) {
                    	 render.draw(cubes.get(i), 0, pitch*2);
                     }
                     g2.drawString("Area: "+Double.toString(areaUnderFunction()), 0, 25);
                }
            };
            System.out.println(maxY+","+maxY/600);
            System.out.println(areaUnderFunction());
        pane.add(renderPanel, BorderLayout.CENTER);

        headingSlider.addChangeListener(e->renderPanel.repaint());
        pitchSlider.addChangeListener(e->renderPanel.repaint());
        text.addPropertyChangeListener(e->renderPanel.repaint());
        
        
        frame.setVisible(true);
    }
    
    static void createCubes(double width, double height) {

    	//need to generalize the scaling for different window sizes
    	
    	maxY=f(startingBound);
    	maxX=startingBound;
    	
    	//need a function to find the max
    	for(double i=startingBound; i<=endingBound; i=i+dx) {
    		if(f(i)>maxY)
    			maxY=f(i);
    		
    		if(i>maxX)
    			maxX=i;
    	}
    	double size=height/2-100;
    	System.out.println(size);
    	scaleY=maxY/(size);
    	scaleX=(width-100)/maxX;
    	
    	for(double x=startingBound; x<=endingBound; x=x+dx) {
    		//System.out.println(x+", "+f(x));
    		cubes.add(new Cube(x*scaleX, -f(x)/scaleY, 0, f(x)/scaleY, dx*scaleX));
    		//cubes.add(new Cube(x, 100, 0, 100, 100));
    	}
    	
    	
    }
    
    static double f(double x){
    	//return Math.pow(x, 2);
    	//return Math.exp(x);
    	return Math.sin(x);
    }
    
    static double areaUnderFunction() {
    	double area=0;
    	for(int i=0; i<cubes.size(); i++) {
    		area=area+cubes.get(i).area();
    	}
    	return area*scaleY/scaleX;
    }
    
}
