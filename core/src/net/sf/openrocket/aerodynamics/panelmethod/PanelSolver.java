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
		
		// FIXME fake
		// blurred doublet:
		// v = (3(N dot R)*abs(R)*R - R^3*N + K*N) / (R^3 + (K/2))^2
		final Vector3D norm = new Vector3D(1, 0, 0);
		final Vector3D center = new Vector3D(0, 0, 0);
		final float k = 1.0f;

		float dx = p.x - center.x;
		float dy = p.y - center.y;
		float dz = p.z - center.z;
		float absR = (float)Math.sqrt(dx*dx + dy*dy + dz*dz);
		float r_dot_n = dx*norm.x + dy*norm.y + dz*norm.z;
		
		float denom = (absR*absR*absR + 0.5f*k);
		denom = denom*denom;
		
		valOut.v.x = (3*r_dot_n*absR*dx - absR*absR*absR*norm.x + k*norm.x)/denom;
		valOut.v.y = (3*r_dot_n*absR*dy - absR*absR*absR*norm.y + k*norm.y)/denom;
		valOut.v.z = (3*r_dot_n*absR*dz - absR*absR*absR*norm.z + k*norm.z)/denom;
	}
}

