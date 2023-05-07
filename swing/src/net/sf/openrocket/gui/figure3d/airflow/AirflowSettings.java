package net.sf.openrocket.gui.figure3d.airflow;

import net.sf.openrocket.aerodynamics.panelmethod.Vector3D;

public class AirflowSettings {
	// matrix is the transpose of what I expect:
	// * * * 0
	// * * * 0
	// * * * 0
	// x y z 1
	
	public ViewMatrix viewMatrix = new ViewMatrix(new double[] {
		1, 0, 0, 0,
		0, 1, 0, 0,
		0, 0, 1, 0,
		0, 0,-100, 1
	});
	public double defaultDistance = 100;
	public Vector3D cutPlaneCenter = new Vector3D(0.0f, 0.0f, 0.0f);
	public Vector3D cutPlaneU = new Vector3D(10.0f, 0.0f, 0.0f);
	public Vector3D cutPlaneV = new Vector3D(0.0f, 10.0f, 0.0f);
}
