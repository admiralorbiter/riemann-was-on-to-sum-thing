package riemann;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.*;

public class Application extends JFrame implements ActionListener {

	static JFrame frame = new JFrame("Riemann was on to sum thing");
	static JFrame init = new JFrame("Welcome to the Circus of Values");
	
	JTextField ubTF, lbTF, nTF, rotateTF, shapeTF, functionTF;
	JLabel contents;
	JLabel error;
	double lowerBound=4;
	double upperBound=10;
	double n=100;
	String function;
	double scaleX=0;
	double scaleY=0;
	
    public static void main(String[] args) {
    		Application app = new Application();
    }
    
    Application(){
    	 initialValues();
    	 init.setVisible(true);
    }
    
    void initialValues() {
 
    	JLabel ubLAB, lbLAB, nLAB, functionLAB;
    	JLabel ubInt, lbInt, nInt, functionInt;
    	JLabel instructions;
    	JLabel title;
    	JButton refresh;
    	int height=50;
    	int rowCount=0;
    	
    	Container pane = init.getContentPane();
    	pane.setLayout(null);
		init.setSize(1400, 800);
			
		rowCount=0;
			
		ubTF = new JTextField(10);
		lbTF = new JTextField(10);
		nTF = new JTextField(10);
		rotateTF = new JTextField(10);
		shapeTF = new JTextField(10);
		functionTF = new JTextField(10);
		
		ubTF.setActionCommand("ubTF");
		lbTF.setActionCommand("lbTF");
		nTF.setActionCommand("nTF");
		functionTF.setActionCommand("functionTF");
		
        ubTF.addActionListener(this);
		lbTF.addActionListener(this);
		nTF.addActionListener(this);
		functionTF.addActionListener(this);
			
		title = new JLabel("<html>Function Around X-Axis<br>Code is generated for openscad in a text document that this program is ran in.</html>");
		ubLAB=new JLabel("Enter the upperbound");
		lbLAB=new JLabel("Enter the lower bound");
		nLAB=new JLabel("Enter the number of sections (suggested at least 100)");
		functionLAB=new JLabel("Enter the function: y=");
		error = new JLabel(" ");
		ubInt=new JLabel("Upper Bound - Integer");
		lbInt=new JLabel("Lower Bound - Integer");
		nInt=new JLabel("Regions to cut up the shape - Integer");
		functionInt=new JLabel("<html>Function must be convered to openscad conversion. Biggest conversion is power function. Example x^(1/3) = pow(x, 1/3)</html>");
		
		pane.add(title);
		pane.add(ubLAB);
		pane.add(ubTF);
		pane.add(ubInt);
		pane.add(lbLAB);
		pane.add(lbTF);
		pane.add(lbInt);
		pane.add(nLAB);
		pane.add(nTF);
		pane.add(nInt);
		pane.add(functionLAB);
		pane.add(functionTF);
		pane.add(functionInt);
		
		Insets insets = pane.getInsets();
        Dimension size;
        
        insets=pane.getInsets();
        size = title.getPreferredSize();
        title.setBounds(25 + insets.left, 5 + insets.top,
                     size.width, size.height);
        rowCount++;
        
        insets=pane.getInsets();
        size = ubLAB.getPreferredSize();
        ubLAB.setBounds(25 + insets.left, height*rowCount + insets.top,
                     size.width, size.height);
        size = ubTF.getPreferredSize();
        ubTF.setBounds(400 + insets.left, height*rowCount + insets.top,
                     size.width, size.height);
        size = ubInt.getPreferredSize();
        ubInt.setBounds(600 + insets.left, height*rowCount + insets.top,
                     size.width + 50, size.height);
        rowCount++;
        
        insets = pane.getInsets();
        size = lbLAB.getPreferredSize();
        lbLAB.setBounds(25 + insets.left, height*rowCount + insets.top,
                     size.width, size.height);
        size = lbTF.getPreferredSize();
        lbTF.setBounds(400 + insets.left, height*rowCount + insets.top,
                     size.width, size.height);
        size = ubInt.getPreferredSize();
        lbInt.setBounds(600 + insets.left, height*rowCount + insets.top,
                     size.width + 50, size.height);
        rowCount++;
        
        insets = pane.getInsets();
        size = nLAB.getPreferredSize();
        nLAB.setBounds(25 + insets.left, height*rowCount + insets.top,
                     size.width, size.height);
        size = nTF.getPreferredSize();
        nTF.setBounds(400 + insets.left, height*rowCount + insets.top,
                     size.width, size.height);
        size = nInt.getPreferredSize();
        nInt.setBounds(600 + insets.left, height*rowCount + insets.top,
                     size.width + 50, size.height);
        rowCount++; 
        
        insets = pane.getInsets();
        size = functionLAB.getPreferredSize();
        functionLAB.setBounds(25 + insets.left, height*rowCount + insets.top,
                     size.width, size.height);
        size = functionTF.getPreferredSize();
        functionTF.setBounds(400 + insets.left, height*rowCount + insets.top,
                     size.width, size.height);
        size = functionInt.getPreferredSize();
        functionInt.setBounds(600 + insets.left, height*rowCount + insets.top,
                     size.width + 50, size.height);
        rowCount++;
        
		instructions=new JLabel("<html>"+"You must type enter after inputing data into the text boxes. "
				+ "You will be able to see the updated values below. Once the valeus are correct, "
				+ "click the button to generate results"+"<html>");
		pane.add(instructions);
		
		
		insets = pane.getInsets();
        size = instructions.getPreferredSize();
        instructions.setBounds(25 + insets.left, height*rowCount + insets.top,
                     size.width, size.height);
        rowCount++;
        
		contents=new JLabel("Loading...");
		pane.add(contents);
		
		refresh=new JButton("Refresh");
		refresh.setActionCommand("Refresh");
		refresh.addActionListener(this);
		pane.add(refresh);
		
		insets = pane.getInsets();
        size = contents.getPreferredSize();
        contents.setBounds(25 + insets.left, height*rowCount + insets.top,
                     size.width+600, size.height);
        size=refresh.getPreferredSize();
        refresh.setBounds(600 + insets.left, height*rowCount + insets.top,
                size.width, size.height);
        rowCount++;
   
        pane.add(error);
        insets=pane.getInsets();
        size=error.getPreferredSize();
        error.setBounds(25 + insets.left, height*rowCount + insets.top,
        				size.width+600, size.height);
        rowCount++;

        contents.setText(upperBound+" - "+lowerBound+" - "+n+" - "+function);
    }
    
	   public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Refresh")) {
			try {
						String text;
						double tempt;
						
						text=ubTF.getText();
						tempt = Double.parseDouble(text);
						upperBound=tempt;
					
					
						text=lbTF.getText();
						tempt = Double.parseDouble(text);
						lowerBound=tempt;
					
					
						text=nTF.getText();
						tempt = Integer.parseInt(text);
						n=tempt;
					
					
						function=functionTF.getText();
						app();
						frame.setVisible(true);
			}
			catch (NumberFormatException nfe)
			{
			  System.out.println("NumberFormatException: " + nfe.getMessage());
			  error.setText("Error with inputs");
			}
		}
		if(ae.getActionCommand().equals("generateCode")) {
			//generateCode();
		}
		contents.setText(upperBound+" - "+lowerBound+" - "+n+" - "+function);
	}
    
    void app(){
    	List<Cylinder> cylinders = new ArrayList<>();
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
         
         frame.setSize(1400, 800);

         createCylinders(frame.getWidth(), frame.getHeight(), cylinders);
         
         // panel to display render results
         JPanel renderPanel = new JPanel() {
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
                 	
                 	for(int i=0; i<cylinders.size(); i++)
                 		cylinders.get(i).draw(g2, transformMatrix);
                 }
             };
         pane.add(renderPanel, BorderLayout.CENTER);

         headingSlider.addChangeListener(e->renderPanel.repaint());
         pitchSlider.addChangeListener(e->renderPanel.repaint());
         text.addPropertyChangeListener(e->renderPanel.repaint());
    }
    
    void createCylinders(double width, double height, List<Cylinder> cylinders) {

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
  
    		if(f(x)!=0)
    			cylinders.add(new Cylinder(x*scaleX, -25, 0, f(x)/scaleY, 360, dx*scaleX));
    	}
    }
    
    double f(double x) {
    	return x*x;
    }
}
