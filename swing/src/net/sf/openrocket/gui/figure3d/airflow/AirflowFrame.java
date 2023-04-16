package net.sf.openrocket.gui.figure3d.airflow;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.openrocket.logging.LoggingSystemSetup;


public class AirflowFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(AirflowPanel.class);
	private final AirflowPanel panel;
	private final JDialog settingsDialog;

	private AirflowController controller;

	
	public AirflowFrame() {
		log.info("AirflowFrame constructor");
		
		controller = new AirflowController();
		
		this.setSize(512, 512);
		panel = new AirflowPanel(controller);
		//setJMenuBar(getMenu(app));  // do we need a menu?
		setContentPane(panel);

		settingsDialog = new JDialog(this, "Viewer Controls");
		settingsDialog.setContentPane(new AirflowSettingsPane(controller));

		settingsDialog.setPreferredSize(new Dimension(600, 500));
		settingsDialog.pack();
		settingsDialog.setVisible(true);
	}
	
	public static void main(String[] args) {
		LoggingSystemSetup.setupLoggingAppender();
		LoggingSystemSetup.addConsoleAppender();

		log.info("Starting");

		AirflowFrame frame = new AirflowFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Airflow Testing");
		frame.setVisible(true);
		log.info("Started");
	}

}
