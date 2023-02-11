package net.sf.openrocket.aerodynamics.panelmethod;

public class PanelTriangle {
	public int groupId;
	public Vector3D p0;
	public Vector3D p1;
	public Vector3D p2;
	
	public TriangleStatistics statistics; //can be null if solver hasn't run
}
