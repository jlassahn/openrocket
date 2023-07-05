package net.sf.openrocket.aerodynamics.panelmethod;

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class PanelSolver
{
	Vector<PanelTriangle> panels;
	Vector<PanelWake> wakes;
	SolverSettings settings;
	
	public PanelSolver() {
		panels = new Vector<PanelTriangle>();
		wakes = new Vector<PanelWake>();
		
		makeFakeGeometry(); // FIXME fake
	}
	
	public PanelTriangle addPanel(int groupId, Vector3D p0, Vector3D p1, Vector3D p2) {
		PanelTriangle panel = new PanelTriangle();
		panel.p0 = p0;
		panel.p1 = p1;
		panel.p2 = p2;
		panel.groupId = groupId;
		
		// FIXME who allocates stats data and when?
		
		panels.add(panel);
		return panel;
	}
	
	public void addWake(int groupId, Vector3D p1, Vector3D p2, Vector3D direction, float strength) {}
	public void deleteGroup(int groupId) {}
	public void deleteAll() {}
	
	public void startSolver(SolverSettings settings) {}
	public boolean isSolverDone() { return false; }
	public float solverProgress() { return 0.0f; }
	public boolean waitForSolver(long milliseconds) { return false; }
	public void cancelSolver() {}
	public void solve(SolverSettings settings) {}
	
	public Collection<PanelTriangle> getPanels() {
		return Collections.unmodifiableCollection(panels);
	}
	
	public Collection<PanelWake> getWakes() {
		return Collections.unmodifiableCollection(wakes);
	}
	
	public void getGroupStatistics(int groupId, GroupStatistics valOut) {}
	public void getGlobalStatistics(GroupStatistics valOut) {}
	public void getPointStatistics(Vector3D p, PointStatistics valOut) {
		
		valOut.clear();
		
		// FIXME if solver hasn't run return
		
		for (PanelTriangle panel : panels)
		{
			panel.mergePointStats(settings, p, valOut);
		}
	}
	
	void makeFakeGeometry() // FIXME fake
	{
		PanelTriangle panel = addPanel(1,
				new Vector3D(0,0,0),
				new Vector3D(10, 0, 0),
				new Vector3D(5, 10, 0));

		// FIXME should be set by solver not here
		settings = new SolverSettings();
		settings.epsilon = 1f;
		
		// FIXME should be created by solver not here
		panel.statistics = new TriangleStatistics();
		panel.statistics.strength = new QuadraticStrength();
		panel.statistics.strength.c = 0.05f;
		
	}
}

