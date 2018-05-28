package viewer;

import javax.swing.*;
import java.awt.*;

public class DemoViewer {
	
	static Cube cube = new Cube(-50, -50, 0, 100);
	static Matrix3 rotateMatrix;
	static Matrix3 pitchMatrix;
	static double rot=0;
	static double pitch=0;
	
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
                    pitch=Math.toRadians(pitchSlider.getValue());

                	rotateMatrix = new Matrix3(new double[] {Math.cos(rot), 0, -Math.sin(rot),
                														0, 1, 0,
                														Math.sin(rot), 0, Math.cos(rot)});
                	
                	pitchMatrix = new Matrix3(new double[] {1, 0, 0,
                											0, Math.cos(pitch), Math.sin(pitch),
                											0, -Math.sin(pitch), Math.cos(pitch)});
                	  
                	Matrix3 transformMatrix=rotateMatrix.multiply(pitchMatrix);
                	cube.drawCube(g2, transformMatrix);
                	
                    
                }
            };

        pane.add(renderPanel, BorderLayout.CENTER);

        headingSlider.addChangeListener(e->renderPanel.repaint());
        pitchSlider.addChangeListener(e->renderPanel.repaint());
        
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
}
