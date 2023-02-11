package net.sf.openrocket.aerodynamics.panelmethod;

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class PanelSolver
{
	Vector<PanelTriangle> panels;
	Vector<PanelWake> wakes;
	
	public PanelSolver() {
		panels = new Vector<PanelTriangle>();
		wakes = new Vector<PanelWake>();
	}
	
	public void addPanel(int groupId, Vector3D p0, Vector3D p1, Vector3D p2) { }
	public void addWake(int groupId, Vector3D p1, Vector3D p2, Vector3D direction, float strength) {}
	public void deleteGroup(int groupId) {}

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
		// FIXME fake
		// blurred doublet:
		// 2R(R dot N) - (R dot R)N + K1*N
		//   /  R^2*R^d + K2
		
		float k1 = 1.0f;
		float k2 = 1.0f;
		float rSquared = (p.x*p.x + p.y*p.y + p.z*p.z);
		float denom = rSquared*rSquared*(float)Math.sqrt(rSquared) + k2;
		
		valOut.v.x = (2*p.x*p.x - rSquared*1 + k1*1)/denom;
		valOut.v.y = 0.0f;
		valOut.v.z = 0.0f;
		
	}
}

