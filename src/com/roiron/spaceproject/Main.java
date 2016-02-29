package com.roiron.spaceproject;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.roiron.spaceproject.graphic.AngleControlPanel;
import com.roiron.spaceproject.graphic.RectangleShape;
import com.roiron.spaceproject.graphic.SpacePanel;
import com.roiron.spaceproject.physic.PhysicMotor;

public class Main extends JFrame {

	/**
	 * create varriable for the gui of the main frame
	 */
	private static final long serialVersionUID = 1L;
	SpacePanel spacePanel; // the main frame, where we draw the space
	AngleControlPanel angleControlPanel;
	Commandes commandes;
	JPanel controlPannel;
	KeyListener keyListener;
	JProgressBar thrustProgressBar;
	JProgressBar gasProgressBar;
	JProgressBar boosterProgressBar;
	JLabel statutLabel;
	JPanel mainPanel;
	PhysicMotor motor;
	Run thread;

	JPanel informationsPanel;
	JLabel informationsLabel;

	public Main() throws InterruptedException {
		this.setTitle("Space Simulation");
		this.setSize(1300, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		start();

	}
	/**
	 * We initialise everything for the gui of the main window
	 */
	public void start() {
		spacePanel = new SpacePanel();
		commandes = new Commandes();

		motor = new PhysicMotor(spacePanel.getListObject(), commandes);
		keyListener = new KeyListener() {

			public void keyTyped(KeyEvent arg0) {

			}

			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyChar() == 'w') {
					commandes.increaseThrust();
				}
				if (arg0.getKeyChar() == 's') {
					commandes.decreaseThrust();
				}
				if (arg0.getKeyChar() == 'd') {
					commandes.increaseAngleThrust();
				}
				if (arg0.getKeyChar() == 'a') {
					commandes.decreaseAngleThrust();
				}
				if (arg0.getKeyChar() == 'r') {
					stop();
					start();
				}
				if (arg0.getKeyChar() == 'p') {
					thread.pause();
				}
			}

			public void keyReleased(KeyEvent e) {

			}
		};
		
		//creating the gui
		this.addKeyListener(keyListener);
		controlPannel = new JPanel();
		controlPannel.setLayout(new GridLayout(5, 2));

		controlPannel.add(new JLabel("Thrust power"));
		thrustProgressBar = new JProgressBar(0, 100);
		thrustProgressBar.setValue(0);
		thrustProgressBar.setStringPainted(false);
		controlPannel.add(thrustProgressBar);

		controlPannel.add(new JLabel("angle direction"));
		angleControlPanel = new AngleControlPanel(commandes);
		controlPannel.add(angleControlPanel);

		controlPannel.add(new JLabel("Remaining gas"));
		gasProgressBar = new JProgressBar(0, 100);
		gasProgressBar.setValue(0);
		gasProgressBar.setStringPainted(true);
		controlPannel.add(gasProgressBar);

		controlPannel.add(new JLabel("fuel booster"));
		boosterProgressBar = new JProgressBar(0, 100);
		boosterProgressBar.setValue(0);
		boosterProgressBar.setStringPainted(true);
		controlPannel.add(boosterProgressBar);

		controlPannel.add(new JLabel("Statut"));
		statutLabel = new JLabel("Landed");
		statutLabel.setForeground(Color.green);
		controlPannel.add(statutLabel);

		informationsLabel = new JLabel(commandes.getInfos());
		informationsPanel = new JPanel();
		informationsPanel.add(informationsLabel);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		controlPannel.setBounds(1300 - 200, 0, 200, 300);
		mainPanel.add(controlPannel);
		informationsPanel.setBounds(1300 - 250, 1000 - 200, 250, 200);
		mainPanel.add(informationsPanel);
		spacePanel.setBounds(0, 0, 1300, 1000);
		mainPanel.add(spacePanel);

		// Set the help menu
		JMenuBar mb = new JMenuBar();
		this.setJMenuBar(mb);
		JMenuItem menu = new JMenuItem();
		menu.setText("Help");
		menu.setEnabled(true);
		menu.addActionListener(new ActionListener() {
			JFrame f;

			ActionListener set(JFrame f) {
				this.f = f;
				return this;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(f,
						"You have to control a rocket, without crashing it\n you can control it using"+
				"w,s( for the engine) and a,d, (to control the angle.)\n"+
						"When you start, you cannot stop your booster!\n"+
				"You can restart with r and pause with p\n"+
				"You can see informations about your rocket on the right (booster and "
				+ "motor fuel, thrust and thrust angle...)\n"+
						"good luck");
			}
		}.set(this));

		mb.add(menu);

		this.setContentPane(mainPanel);
		this.setVisible(true);

		thread = new Run();
		thread.start();

	}

	/**
	 * Stop the thread and reset the parameters
	 */
	public void stop() {
		this.removeKeyListener(keyListener);
		this.remove(mainPanel);
		thread.interrupt();
	}

	/**
	 * configure and draw everything
	 */
	public void updateGraphic() {
		// spacePanel.repaint();
		// angleControlPanel.repaint();
		thrustProgressBar.setValue((int) (commandes.getGasThrust() * 100.));
		gasProgressBar.setValue((int) (commandes.getGasTankPerecentage()));
		boosterProgressBar.setValue((int) (commandes.getBoosterTankPerecentage()));
		informationsLabel.setText(commandes.getInfos());
		if (commandes.isCrached()) {
			statutLabel.setText("crashed");
			statutLabel.setForeground(Color.red);
		} else if (spacePanel.getListObject().size()>4 && ((RectangleShape) (spacePanel.getElementListObjectById(3))).getInContact() != null) {

			statutLabel.setText("Landed");
			statutLabel.setForeground(Color.green);
		} else {
			statutLabel.setText("In flight");
			statutLabel.setForeground(Color.blue);
		}
		this.repaint();
	}

	public static void main(String[] args) throws InterruptedException {
		new Main();

	}
	/**
	 * the main thread that we start to calculate the physics, and give order for 
	 * swing to draw everything on an other thread
	 * @author Yohann
	 *
	 */
	public class Run extends Thread {
		boolean running;
		boolean pause;

		@Override
		public void run() {
			running = true;
			while (running) {
				for (int i = 0; i < 5; i++)
					motor.update();
				motor.simulate();
				updateGraphic();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while (pause) {
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}

		public void interrupt() {
			running = false;
		}

		public void pause() {
			pause = !pause;
		}

	}

}
