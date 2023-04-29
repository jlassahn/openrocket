package net.sf.openrocket.gui.figure3d.airflow;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.openrocket.aerodynamics.panelmethod.PanelSolver;

public class AirflowController {
	private static final Logger log = LoggerFactory.getLogger(AirflowPanel.class);

	static interface ControllerEventHandler {
		void onControllerChange();
	}
	
	private Vector<ControllerEventHandler> redrawListeners = new Vector<ControllerEventHandler>();
	private Vector<ControllerEventHandler> refetchListeners = new Vector<ControllerEventHandler>();
	private Vector<ControllerEventHandler> solverListeners = new Vector<ControllerEventHandler>();
	
	// probably shouldn't be public, have getters/setters etc instead
	public PanelSolver solver = new PanelSolver();
	public AirflowSettings settings = new AirflowSettings();
	
	// objects should never need to listen to more than one of these events.
	//   refetch events also notify all redraw listeners
	//   solver events also notify all redraw and refetch listeners

	// Notify whenever the view needs to be redrawn
	public void addRedrawListener(ControllerEventHandler target) {
		redrawListeners.add(target);
	}
	
	public void removeRedrawListener(ControllerEventHandler target) {
		redrawListeners.remove(target);
	}
	
	// Notify whenever cached data needs to be reread or recomputed
	public void addRefetchListener(ControllerEventHandler target) {
		refetchListeners.add(target);
	}
	
	public void removeRefetchListener(ControllerEventHandler target) {
		refetchListeners.remove(target);
	}
	
	// Notify whenever the solver changes state
	public void addSolverListener(ControllerEventHandler target) {
		solverListeners.add(target);
	}
	
	public void removeSolverListener(ControllerEventHandler target) {
		solverListeners.remove(target);
	}
	
	public void notifyRedraw() {
		for (ControllerEventHandler target : redrawListeners)
			target.onControllerChange();
	}
	
	public void notifyRefetch() {
		for (ControllerEventHandler target : refetchListeners)
			target.onControllerChange();
		for (ControllerEventHandler target : redrawListeners)
			target.onControllerChange();
	}
	
	public void notifySolverChange() {
		for (ControllerEventHandler target : solverListeners)
			target.onControllerChange();
		for (ControllerEventHandler target : refetchListeners)
			target.onControllerChange();
		for (ControllerEventHandler target : redrawListeners)
			target.onControllerChange();
	}
	
	public void moveViewMatrix(double[] delta, double speed) {
		
		log.info("MOVE! "+delta);

		ViewMatrix matrix = new ViewMatrix();
		for (int i=0; i<16; i++)
			matrix.matrix[i] += delta[i]*speed;
		matrix.normalize();
		
		ViewMatrix result = new ViewMatrix();
		ViewMatrix.mult(matrix, settings.viewMatrix, result);
		result.normalize();
		settings.viewMatrix = result;
		
		notifyRedraw();
	}

}
