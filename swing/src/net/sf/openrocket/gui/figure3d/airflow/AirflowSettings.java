package net.sf.openrocket.gui.figure3d.airflow;

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
}
