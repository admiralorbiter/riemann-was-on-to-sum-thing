package viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class DemoViewer {
	
	static Cube cube = new Cube(0, 0, 0, 100);
	static Matrix3 rotate;
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
        cube.printVertices();
        
        
        // panel to display render results
        JPanel renderPanel = new JPanel() {
                public void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, getWidth(), getHeight());

                    // rendering magic will happen here
                    
                    g2.translate(getWidth() / 2, getHeight() / 2);
                    g2.setColor(Color.WHITE);
                    
                    double rot=Math.toRadians(headingSlider.getValue());
                    //double rot=90;
                    //System.out.println(rot);
                	rotate = new Matrix3(new double[] {Math.cos(rot), 0, -Math.sin(rot),
                														0, 1, 0,
                														Math.sin(rot), 0, Math.cos(rot)});
                    /*
	                    Path2D path = new Path2D.Double();
	                    path.moveTo(cube.vert.get(0).x, cube.vert.get(0).y);
	                    path.lineTo(cube.vert.get(1).x, cube.vert.get(1).y);
	                    path.lineTo(cube.vert.get(2).x, cube.vert.get(2).y);
	                    path.lineTo(cube.vert.get(3).x, cube.vert.get(3).y);
	                    path.lineTo(cube.vert.get(4).x, cube.vert.get(4).y);
	                    path.lineTo(cube.vert.get(5).x, cube.vert.get(5).y);
	                    path.lineTo(cube.vert.get(6).x, cube.vert.get(6).y);
	                    path.lineTo(cube.vert.get(7).x, cube.vert.get(7).y);
	                    path.closePath();
	                    g2.draw(path);*/
                    /*
                    Path2D path = new Path2D.Double();
                    Vertex v=rotate.transform(cube.vert.get(0));
                    path.moveTo(v.x, v.y);
                    
                    for(int i=1; i<8; i++) {
                    	v=rotate.transform(cube.vert.get(i));
                    	path.lineTo(v.x, v.y);
                    }
                    path.closePath();
                    g2.draw(path);
                    */
                    drawCube(g2);
                    
                }
            };
        System.out.println("Test");
        pane.add(renderPanel, BorderLayout.CENTER);

        headingSlider.addChangeListener(e->renderPanel.repaint());
        pitchSlider.addChangeListener(e->renderPanel.repaint());
        
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
    static void drawCube(Graphics g2) {
    	Path2D path = new Path2D.Double();
        Vertex v=rotate.transform(cube.vert.get(0));
        path.moveTo(v.x, v.y);
        
        for(int i=1; i<8; i++) {
        	v=rotate.transform(cube.vert.get(i));
        	path.lineTo(v.x, v.y);
        }
        path.closePath();
        ((Graphics2D) g2).draw(path);
    }
}
