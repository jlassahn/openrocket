package net.sf.openrocket.gui.figure3d.airflow;

public class ViewMatrix {
	public double[] matrix;
	
	public ViewMatrix() {
		matrix = new double[] {
			1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1
		};
	}
	
	public ViewMatrix(double[] data) {
		matrix = data.clone();
	}
	
	public static void mult(ViewMatrix a, ViewMatrix b, ViewMatrix result) {
		for (int i=0; i<4; i++)
		for (int k=0; k<4; k++) {
			double sum = 0;
			for (int j=0; j<4; j++) {
				sum += a.matrix[i + 4*j]*b.matrix[j + 4*k]; 
			}
			result.matrix[i + 4*k] = sum;
		}
	}
	
	public void normalize() {
		for (int i=0; i<3; i++) {
			for (int j=0; j<i; j++) {
				double dot = 
						matrix[i*4 + 0]*matrix[j*4 + 0] +
						matrix[i*4 + 1]*matrix[j*4 + 1] +
						matrix[i*4 + 2]*matrix[j*4 + 2];
			
				matrix[i*4 + 0] -= dot*matrix[j*4 + 0];
				matrix[i*4 + 1] -= dot*matrix[j*4 + 1];
				matrix[i*4 + 2] -= dot*matrix[j*4 + 2];
			}
			
			double sum2 =
					matrix[i*4 + 0]*matrix[i*4 + 0] +
					matrix[i*4 + 1]*matrix[i*4 + 1] +
					matrix[i*4 + 2]*matrix[i*4 + 2];
			double scale = 1.0/Math.sqrt(sum2);
			matrix[i*4 + 0] *= scale;
			matrix[i*4 + 1] *= scale;
			matrix[i*4 + 2] *= scale;
			
			matrix[4*i + 3] = 0.0;
		}
		matrix[15] = 1.0;
	}
}
