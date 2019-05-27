package riemann;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Application extends JFrame{
	
	private enum State {
		INPUT,
		DISPLAY;
	}
	
	private InputFrame inputframe = new InputFrame();
	private SolidFrame solidframe=null;
	private State state = State.INPUT;
	
	public Application() {
		setSize(1400, 800);
		setTitle("Welcome to the Circus of Values");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		inputframe.setVisible(true);
		add(inputframe);

	}
	
	public static void main(String[] args) {
		Application app = new Application();
		
		app.setVisible(true);
		
		while(true)
			app.run();
	}
	
	public void run() {
		
		if(state==State.INPUT) {
			System.out.println(inputframe.showSolid());
			if(inputframe.showSolid() && solidframe==null) {
				remove(inputframe);
				setTitle("Riemann was on to Sum Thing");
				solidframe = new SolidFrame(inputframe.getLowerBound(), inputframe.getUpperBound(), (int) inputframe.getN());
				solidframe.setVisible(true);
				add(solidframe);
				revalidate();
				repaint();
				state=State.DISPLAY;
			}
		}
		
		if(state==State.DISPLAY)
			solidframe.run();
		
		/*
		System.out.println(inputframe.showSolid());
		if(inputframe.showSolid() && solidframe==null) {
			remove(inputframe);
			setTitle("Riemann was on to Sum Thing");
			solidframe = new SolidFrame(inputframe.getLowerBound(), inputframe.getUpperBound(), (int) inputframe.getN());
			solidframe.setVisible(true);
			add(solidframe);
			revalidate();
			repaint();
		}else if(solidframe!=null) {
			solidframe.run();
		}*/
	}

}
