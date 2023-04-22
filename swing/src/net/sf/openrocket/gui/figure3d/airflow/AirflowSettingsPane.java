package net.sf.openrocket.gui.figure3d.airflow;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Timer;


public class AirflowSettingsPane extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AirflowPanel.class);

	final static double[] ROTATE_LEFT = {
			 0,  0, -1,  0,
			 0,  0,  0,  0,
			 1,  0,  0,  0,
			 0,  0,  0,  0
	};
	final static double[] ROTATE_RIGHT = {
			 0,  0,  1,  0,
			 0,  0,  0,  0,
			-1,  0,  0,  0,
			 0,  0,  0,  0
	};
	final static double[] ROTATE_UP = {
			 0,  1,  0,  0,
			-1,  0,  0,  0,
			 0,  0,  0,  0,
			 0,  0,  0,  0
	};
	final static double[] ROTATE_DOWN = {
			 0, -1,  0,  0,
			 1,  0,  0,  0,
			 0,  0,  0,  0,
			 0,  0,  0,  0
	};
	final static double[] ROTATE_CW = {
			 0,  0,  0,  0,
			 0,  0,  1,  0,
			 0, -1,  0,  0,
			 0,  0,  0,  0
	};
	final static double[] ROTATE_CCW = {
			 0,  0,  0,  0,
			 0,  0, -1,  0,
			 0,  1,  0,  0,
			 0,  0,  0,  0
	};
	final static double[] MOVE_LEFT = {
			 0,  0,  0, -1,
			 0,  0,  0,  0,
			 0,  0,  0,  0,
			 0,  0,  0,  0
	};
	final static double[] MOVE_RIGHT = {
			 0,  0,  0,  1,
			 0,  0,  0,  0,
			 0,  0,  0,  0,
			 0,  0,  0,  0
	};
	final static double[] MOVE_UP = {
			 0,  0,  0,  0,
			 0,  0,  0,  1,
			 0,  0,  0,  0,
			 0,  0,  0,  0
	};
	final static double[] MOVE_DOWN = {
			 0,  0,  0,  0,
			 0,  0,  0, -1,
			 0,  0,  0,  0,
			 0,  0,  0,  0
	};
	final static double[] MOVE_FORWARD = {
			 0,  0,  0,  0,
			 0,  0,  0,  0,
			 0,  0,  0,  1,
			 0,  0,  0,  0
	};
	final static double[] MOVE_BACK = {
			 0,  0,  0,  0,
			 0,  0,  0,  0,
			 0,  0,  0, -1,
			 0,  0,  0,  0
	};
	
	private AirflowController controller;
	private ButtonTimer buttonTimer = new ButtonTimer();
	
	class ButtonTimer extends Timer implements ActionListener {
		
		Vector<ViewMoveButton> listeners;
		
		public ButtonTimer() {
			super(1000, null);
			listeners = new Vector<ViewMoveButton>();
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e) {
			for (ViewMoveButton element: listeners) {
				element.onTimer();
			}
		}
		
		void addListener(ViewMoveButton button) {
			listeners.add(button);
			start();
		}
		
		void removeListener(ViewMoveButton button) {
			listeners.remove(button);
			if (listeners.isEmpty())
				stop();
		}
	}
	
	class ViewMoveButton extends JButton implements ChangeListener {
		
		private boolean isPressed;
		private String name;
		private double[] moveDelta;
		private final double moveSpeed = 1.0;
		
		// captures stage changed events to allow updates on a time while
		// the button is held down
		public ViewMoveButton(String text, String iconName, double[] delta) {
			super();

			name = text;
			moveDelta = delta;
			isPressed = false;

			URL url = ClassLoader.getSystemResource(iconName);
			if (url != null)
			{
				ImageIcon icon = new ImageIcon(url, text);
				setIcon(icon);
			}
			addChangeListener(this);
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			log.info(name + " state change: "+ getModel().isPressed());
			
			if (getModel().isPressed()) {
				if (!isPressed) {
					isPressed = true;
					buttonTimer.addListener(this);
					controller.moveViewMatrix(moveDelta, moveSpeed);
				}
			} else {
				if (isPressed) {
					isPressed = false;
					buttonTimer.removeListener(this);
				}
			}
		}
		
		public void onTimer() {
			controller.moveViewMatrix(moveDelta, moveSpeed);
		}
	}
	
	public AirflowSettingsPane(AirflowController controllerIn)
	{
		controller = controllerIn;
		
		// Fake icon for experiments
		URL url = ClassLoader.getSystemResource("pix/spheres/red-16x16.png");
		// url could be null if build is incomplete
		ImageIcon icon = new ImageIcon(url, "Icon Description");

		JPanel panel = new JPanel();
		addTab("Solver", panel);
		panel.add(new JLabel("Recompute needed", icon, JLabel.LEADING));
		panel.add(new JButton("Recompute", icon));
		
		panel = new JPanel(new GridBagLayout());		
		addTab("Camera", panel);
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new ViewMoveButton("Rotate Left", "pix/arrows/rotate_left.png", ROTATE_LEFT), c);
		c.gridx = 2;
		c.gridy = 2;
		panel.add(new ViewMoveButton("Rotate Right", "pix/arrows/rotate_right.png", ROTATE_RIGHT), c);
		c.gridx = 1;
		c.gridy = 1;
		panel.add(new ViewMoveButton("Rotate Up", "pix/arrows/rotate_up.png", ROTATE_UP), c);
		c.gridx = 1;
		c.gridy = 3;
		panel.add(new ViewMoveButton("Rotate Down", "pix/arrows/rotate_down.png", ROTATE_DOWN), c);
		c.gridx = 2;
		c.gridy = 1;
		panel.add(new ViewMoveButton("Rotate CW", "pix/arrows/rotate_cw.png", ROTATE_CW), c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new ViewMoveButton("Rotate CCW", "pix/arrows/rotate_ccw.png", ROTATE_CCW), c);
		
		c.gridx = 6;
		c.gridy = 2;
		panel.add(new ViewMoveButton("Move Left", "pix/arrows/move_left.png", MOVE_LEFT), c);
		c.gridx = 8;
		c.gridy = 2;
		panel.add(new ViewMoveButton("Move Right", "pix/arrows/move_right.png", MOVE_RIGHT), c);
		c.gridx = 7;
		c.gridy = 1;
		panel.add(new ViewMoveButton("Move Up", "pix/arrows/move_up.png", MOVE_UP), c);
		c.gridx = 7;
		c.gridy = 3;
		panel.add(new ViewMoveButton("Move Down", "pix/arrows/move_down.png", MOVE_DOWN), c);
		c.gridx = 8;
		c.gridy = 1;
		panel.add(new ViewMoveButton("Move Forward", "pix/arrows/move_forward.png", MOVE_FORWARD), c);
		c.gridx = 8;
		c.gridy = 3;
		panel.add(new ViewMoveButton("Move Back", "pix/arrows/move_back.png", MOVE_BACK), c);
		
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 4;
		panel.add(new JButton("Reset X+", icon), c);
		c.gridy = 5;
		panel.add(new JButton("Reset X-", icon), c);
		c.gridx = 3;
		c.gridy = 4;
		panel.add(new JButton("Reset Y+", icon), c);
		c.gridy = 5;
		panel.add(new JButton("Reset Y-", icon), c);
		c.gridx = 6;
		c.gridy = 4;
		panel.add(new JButton("Reset Z+", icon), c);
		c.gridy = 5;
		panel.add(new JButton("Reset Z-", icon), c);

		panel = new JPanel();
		addTab("Data Display", panel);

		String[] surfaceList = {
			"Wireframe",
			"Abs Velocity",
			"X Velocity",
			"Y Velocity",
			"Z Velocity",
			"Squared Velocity",
			"Pressure Coefficient",
			"Doublet Strength"
		};
		panel.add(new JComboBox<String>(surfaceList));

		String[] volumeList = {
			"Abs Velocity",
			"X Velocity",
			"Y Velocity",
			"Z Velocity",
			"Squared Velocity",
			"Pressure Coefficient",
			"X Curl V",
			"Y Curl V",
			"Z Curl V",
			"Div V"
		};
		panel.add(new JComboBox<String>(volumeList));

		String[] planeList = {
			"XY",
			"YZ",
			"XZ"
		};
		panel.add(new JComboBox<String>(planeList));

		// enable cut plane
		// make plane semitransparent
		// size and position of cut plane
	}
}
