package net.sf.openrocket.gui.figure3d.airflow;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class AirflowSettingsPane extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AirflowPanel.class);

	class SettingsHandler implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			log.info("state change: "+ button.getModel().isPressed());
		}

	}

	JButton button;
	
	public AirflowSettingsPane(AirflowController controller)
	{
		SettingsHandler handler = new SettingsHandler();
		
		JPanel panel = new JPanel();
		addTab("Panel Label", panel);

		URL url = ClassLoader.getSystemResource("pix/spheres/red-16x16.png");
		// url could be null if build is incomplete
		ImageIcon icon = new ImageIcon(url, "Icon Description");

		button = new JButton("test", icon);
		panel.add(button);
		button.addChangeListener(handler);
		
	}
}
