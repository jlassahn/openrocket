package net.sf.openrocket.aerodynamics.panelmethod;

public class TriangleStatistics {
	public QuadraticStatistics strength;
	public LinearStatistics pressure; //difference between sides?
	public Vector3D force;
	public Vector3D torque; // around global origin
	// TODO maybe derivatives wrt free stream velocity of everything as well?
}
