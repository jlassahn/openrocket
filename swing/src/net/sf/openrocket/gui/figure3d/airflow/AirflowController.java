package net.sf.openrocket.gui.figure3d.airflow;

import net.sf.openrocket.aerodynamics.panelmethod.PanelSolver;

public class AirflowController {

	// probably shouldn't be public, have getters/setters etc instead
	public PanelSolver solver;
	public AirflowSettings settings;
	
	// need notification events for system changes:
	//   view settings that only require repaint
	//   view settings that require fetching new data from solved model
	//   changes that require rerunning the solver
	//   solver state changes (becomes invalid, finishes, etc)
	
	// does the controller have a timer for scheduling stuff?
	//    javax.swing.Timer
	//    wait for solver to finish
	//    smooth motion while view direction buttons are held
	
}
