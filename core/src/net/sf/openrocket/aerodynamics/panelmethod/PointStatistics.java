package net.sf.openrocket.aerodynamics.panelmethod;

public class PointStatistics {
	// if there's nothing else maybe don't need a separate PointStatistics class?
	public Vector3D v;
	// TODO maybe derivatives wrt free stream velocity of everything as well?
	
	public PointStatistics()
	{
		v = new Vector3D();
	}
	
	public void clear() {
		v.x = 0;
		v.y = 0;
		v.z = 0;
	}
}

