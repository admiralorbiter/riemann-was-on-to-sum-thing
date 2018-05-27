package viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class DemoViewer {
	
	static Cube cube = new Cube(0, 0, 0, 100);
	static Matrix3 rotate;
	static double rot=0;
	static double rotTemp=0;
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        // slider to control horizontal rotation
        JSlider headingSlider = new JSlider(0, 360, 180);
        pane.add(headingSlider, BorderLayout.SOUTH);

        // slider to control vertical rotation
        JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        pane.add(pitchSlider, BorderLayout.EAST);

        //testing
        //cube.printVertices();
        
        
        // panel to display render results
        JPanel renderPanel = new JPanel() {
                public void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, getWidth(), getHeight());

                    // rendering magic will happen here
                    
                    g2.translate(getWidth() / 2, getHeight() / 2);
                    g2.setColor(Color.WHITE);
                    
                    rot=Math.toRadians(headingSlider.getValue());
                    
                    //double rot=90;
                    //System.out.println(rot);
                	rotate = new Matrix3(new double[] {Math.cos(rot), 0, -Math.sin(rot),
                														0, 1, 0,
                														Math.sin(rot), 0, Math.cos(rot)});
                	
                	/*
                	 * Essentially a hack since I am actually changing the vertices, I only want to
                	 * update the transform if it is being rotated or it will update everytime the 
                	 * screen refreshes.
                	*/
                	if(rot!=rotTemp) { 
                		cube.transformCube(rotate);
                		rotTemp=rot;
                	}
                	cube.drawCube(g2);
                	
                    
                }
            };

        pane.add(renderPanel, BorderLayout.CENTER);

        headingSlider.addChangeListener(e->renderPanel.repaint());
        pitchSlider.addChangeListener(e->renderPanel.repaint());
        
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
}
