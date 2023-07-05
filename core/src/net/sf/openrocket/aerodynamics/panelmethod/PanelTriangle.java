package net.sf.openrocket.aerodynamics.panelmethod;

public class PanelTriangle {
	public int groupId;
	public Vector3D p0;
	public Vector3D p1;
	public Vector3D p2;
	
	public TriangleStatistics statistics; //can be null if solver hasn't run
	
	public void mergePointStats(SolverSettings settings, Vector3D p, PointStatistics stats)
	{
		int steps = 16;
		
		float dx1 = (p1.x - p0.x)/steps;
		float dy1 = (p1.y - p0.y)/steps;
		float dz1 = (p1.z - p0.z)/steps;
		float dx2 = (p2.x - p0.x)/steps;
		float dy2 = (p2.y - p0.y)/steps;
		float dz2 = (p2.z - p0.z)/steps;
		
		Vector3D norm = new Vector3D(
				(dy1*dz2 - dy2*dz1)*statistics.strength.c,
				(dz1*dx2 - dz2*dx1)*statistics.strength.c,
				(dx1*dy2 - dx2*dy1)*statistics.strength.c);
		Vector3D center = new Vector3D(
				p0.x + (dx1 + dx2)/3,
				p0.y + (dy1 + dy2)/3,
				p0.z + (dz1 + dz2)/3);
		
		float k = settings.epsilon*settings.epsilon*settings.epsilon;
		
		for (int i=0; i<steps; i++)
		{
			for (int j=0; j<(steps-i); j++)
			{
				center.x = p0.x + (dx1 + dx2)/3 + i*dx1 + j*dx2;
				center.y = p0.y + (dy1 + dy2)/3 + i*dy1 + j*dy2;
				center.z = p0.z + (dz1 + dz2)/3 + i*dz1 + j*dz2;
				mergeDoublet(norm, center, p, k, stats.v);
			}
		}
		for (int i=0; i<steps-1; i++)
		{
			for (int j=0; j<(steps-i-1); j++)
			{
				center.x = p0.x + 2*(dx1 + dx2)/3 + i*dx1 + j*dx2;
				center.y = p0.y + 2*(dy1 + dy2)/3 + i*dy1 + j*dy2;
				center.z = p0.z + 2*(dz1 + dz2)/3 + i*dz1 + j*dz2;
				mergeDoublet(norm, center, p, k, stats.v);
			}
		}
	}

	static final void mergeDoublet(Vector3D norm, Vector3D center, Vector3D p, float k, Vector3D vOut) {
		
		// blurred doublet:
		// v = (3(N dot R)*abs(R)*R - R^3*N + K*N) / (R^3 + (K/2))^2
		float dx = p.x - center.x;
		float dy = p.y - center.y;
		float dz = p.z - center.z;
		float absR = (float)Math.sqrt(dx*dx + dy*dy + dz*dz);
		float r_dot_n = dx*norm.x + dy*norm.y + dz*norm.z;
		
		float denom = (absR*absR*absR + 0.5f*k);
		denom = 1/(denom*denom);
		
		vOut.x += (3*r_dot_n*absR*dx - absR*absR*absR*norm.x + k*norm.x)*denom;
		vOut.y += (3*r_dot_n*absR*dy - absR*absR*absR*norm.y + k*norm.y)*denom;
		vOut.z += (3*r_dot_n*absR*dz - absR*absR*absR*norm.z + k*norm.z)*denom;
	}
	

}
