package net.sf.openrocket.gui.figure3d.airflow;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class AirflowSettingsPane extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AirflowPanel.class);

	class ViewMoveButton extends JButton implements ChangeListener {
		
		// captures stage changed events to allow updates on a time while
		// the button is held down
		public ViewMoveButton(String text, String iconName) {
			super();
			
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
			log.info(getText() + " state change: "+ getModel().isPressed());
		}
	}
	
	public AirflowSettingsPane(AirflowController controller)
	{		

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
		panel.add(new ViewMoveButton("Rotate Left", "pix/arrows/rotate_left.png"), c);
		c.gridx = 2;
		c.gridy = 2;
		panel.add(new ViewMoveButton("Rotate Right", "pix/arrows/rotate_right.png"), c);
		c.gridx = 1;
		c.gridy = 1;
		panel.add(new ViewMoveButton("Rotate Up", "pix/arrows/rotate_up.png"), c);
		c.gridx = 1;
		c.gridy = 3;
		panel.add(new ViewMoveButton("Rotate Down", "pix/arrows/rotate_down.png"), c);
		c.gridx = 2;
		c.gridy = 1;
		panel.add(new ViewMoveButton("Rotate CW", "pix/arrows/rotate_cw.png"), c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new ViewMoveButton("Rotate CCW", "pix/arrows/rotate_ccw.png"), c);
		
		c.gridx = 6;
		c.gridy = 2;
		panel.add(new ViewMoveButton("Move Left", "pix/arrows/move_left.png"), c);
		c.gridx = 8;
		c.gridy = 2;
		panel.add(new ViewMoveButton("Move Right", "pix/arrows/move_right.png"), c);
		c.gridx = 7;
		c.gridy = 1;
		panel.add(new ViewMoveButton("Move Up", "pix/arrows/move_up.png"), c);
		c.gridx = 7;
		c.gridy = 3;
		panel.add(new ViewMoveButton("Move Down", "pix/arrows/move_down.png"), c);
		c.gridx = 8;
		c.gridy = 1;
		panel.add(new ViewMoveButton("Move Forward", "pix/arrows/move_forward.png"), c);
		c.gridx = 8;
		c.gridy = 3;
		panel.add(new ViewMoveButton("Move Back", "pix/arrows/move_back.png"), c);
		
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
