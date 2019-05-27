package riemann;

import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputFrame extends JPanel implements ActionListener{
	
	private boolean showSolid=false;
	private double upperBound=4;
	private double lowerBound=10;
	private double n=100;
	private String function;
	
	//Container pane = getPane();
	JTextField ubField, lbField, nField, rotateField, shapeField, functionField;
	
	JButton refresh;
	
	JLabel contents;
	JLabel title, error, instructions;
	JLabel ubLabel, lbLabel, nLabel, rotateLabel, shapeLabel, functionLabel;
	JLabel functionInstructions;
	
	public InputFrame() {
		setLayout(null);
		init();
	}
	
	public void init() {
		setButtons();
		setTextFields();
		setLabels();
		createPane();
		createInsets();
	}

	private void setLabels() {
		title = new JLabel("<html>Function Around X-Axis<br>Code is generated for openscad in a text document that this program is ran in.</html>");
		ubLabel=new JLabel("Enter the upperbound as an integer");
		lbLabel=new JLabel("Enter the lower bound as an integer");
		nLabel=new JLabel("Enter the number of sections/regions (suggested at least 100) as an integer");
		functionLabel=new JLabel("Enter the function: y=");
		error = new JLabel(" ");
		functionInstructions=new JLabel("<html>Function must be convered to openscad conversion. Biggest conversion is power function. Example x^(1/3) = pow(x, 1/3)</html>");
		instructions=new JLabel("<html>"+"You must type enter after inputing data into the text boxes. "
				+ "You will be able to see the updated values below. Once the valeus are correct, "
				+ "click the button to generate results"+"<html>");
	
		contents = new JLabel("Loading...");
		contents.setText(upperBound+" - "+lowerBound+" - "+n+" - "+function);
	}
	
	private void setButtons() {
		refresh = new JButton("Refresh");
		refresh.setActionCommand("Refresh");
		refresh.addActionListener(this);
	}
	
	private void setTextFields() {
		ubField = new JTextField(10);
		ubField.setActionCommand("ubField");
		ubField.addActionListener(this);
		
		lbField= new JTextField(10);
		lbField.setActionCommand("lbField");
		lbField.addActionListener(this);
		
		nField= new JTextField(10);
		nField.setActionCommand("nField");
		lbField.addActionListener(this);
		
		functionField= new JTextField(10);
		functionField.setActionCommand("functionField");
		functionField.addActionListener(this);
		
		rotateField= new JTextField(10);
		shapeField= new JTextField(10); 
		
	}
	
	private void createPane() {
		add(title);
		add(ubLabel);
		add(ubField);
		add(lbLabel);
		add(lbField);
		add(nLabel);
		add(nField);
		add(functionLabel);
		add(functionField);
		add(functionInstructions);
		add(instructions);
		add(refresh);
		add(contents);
	}
	
	private void createInsets() {

		Rectangle label = new Rectangle(25, 50, 200, 25);
		Rectangle field = new Rectangle(250, 50, 100, 25);
		Rectangle instruction = new Rectangle(400, 50, 300, 100);
		
		title.setBounds(25, 0, 600, 50);
		
		ubLabel.setBounds(label.x, label.y*1, label.width, label.height);
		ubField.setBounds(field.x, field.y*1, field.width, field.height);
		
		lbLabel.setBounds(label.x, label.y*2, label.width, label.height);
		lbField.setBounds(field.x, field.y*2, field.width, field.height);
		
		nLabel.setBounds(label.x, label.y*3, label.width, label.height);
		nField.setBounds(field.x, field.y*3, field.width, field.height);

		functionLabel.setBounds(label.x, label.y*4, label.width, label.height);
		functionField.setBounds(field.x, field.y*4, field.width, field.height);
		
		functionInstructions.setBounds(instruction.x, instruction.y*3, instruction.width, instruction.height);
	
		instructions.setBounds(instruction.x, instruction.y*5, instruction.width, instruction.height);
	
		refresh.setBounds(field.x, field.y*5, field.width, field.height);
	
		contents.setBounds(label.x, label.y*5, label.width, label.height);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Refresh")) {
			try {
						String text;
						double tempt;
						
						text=ubField.getText();
						tempt = Double.parseDouble(text);
						upperBound=tempt;
					
					
						text=lbField.getText();
						tempt = Double.parseDouble(text);
						lowerBound=tempt;
					
					
						text=nField.getText();
						tempt = Integer.parseInt(text);
						n=tempt;
					
					
						function=functionField.getText();
						showSolid=true;
						repaint();
						setVisible(true);
			}
			catch (NumberFormatException nfe)
			{
			  System.out.println("NumberFormatException: " + nfe.getMessage());
			  error.setText("Error with inputs");
			}
			
			contents.setText(upperBound+" - "+lowerBound+" - "+n+" - "+function);
		}
	}
	
	//Getters and Setters
	public boolean showSolid() {return showSolid;}
	public double getUpperBound() {return upperBound;}
	public double getLowerBound() {return lowerBound;}
	public double getN() {return n;}
}
