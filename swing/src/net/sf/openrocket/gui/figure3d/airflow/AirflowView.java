package net.sf.openrocket.gui.figure3d.airflow;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

import net.sf.openrocket.aerodynamics.panelmethod.Vector3D;

public class AirflowView implements AirflowController.ControllerEventHandler {
	private static final Logger log = LoggerFactory.getLogger(AirflowPanel.class);

	private AirflowController controller;

	private static final int CUT_PLANE_SIZE = 512;
	private boolean cutPlaneBufferValid = false;
	private byte[] cutPlaneArray = new byte[CUT_PLANE_SIZE*CUT_PLANE_SIZE*4];
	private ByteBuffer cutPlaneBuffer = ByteBuffer.wrap(cutPlaneArray);

	public AirflowView(AirflowController controllerIn) {
		log.info("AirflowView constructor");
		controller = controllerIn;
		
		controller.addRefetchListener(this);
	}
	
	@Override
	public void onControllerChange() {
		log.info("controller refetch");
		cutPlaneBufferValid = false;
	}

	
	public void draw(GL2 gl) {
		drawTriangles(gl);
		drawLines(gl);
		drawCutPlane(gl);
	}
	
	private void drawTriangles(GL2 gl) {
		
	}
	
	private void drawLines(GL2 gl) {
		gl.glBegin(GL.GL_LINES);

		gl.glColor3f(1, 1, 1);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(1, 0, 0);
		gl.glVertex3f(0, 1, 0);
		gl.glVertex3f(1, 1, 0);
		gl.glVertex3f(0, 0, 1);
		gl.glVertex3f(1, 0, 1);
		gl.glVertex3f(0, 1, 1);
		gl.glVertex3f(1, 1, 1);
		
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(0, 1, 0);
		gl.glVertex3f(1, 0, 0);
		gl.glVertex3f(1, 1, 0);
		gl.glVertex3f(0, 0, 1);
		gl.glVertex3f(0, 1, 1);
		gl.glVertex3f(1, 0, 1);
		gl.glVertex3f(1, 1, 1);
		
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(0, 0, 1);
		gl.glVertex3f(1, 0, 0);
		gl.glVertex3f(1, 0, 1);
		gl.glVertex3f(0, 1, 0);
		gl.glVertex3f(0, 1, 1);
		gl.glVertex3f(1, 1, 0);
		gl.glVertex3f(1, 1, 1);

		gl.glColor3f(1, 0, 0);
		gl.glVertex3f(10, 0, 0);
		gl.glVertex3f(11, 0, 0);
		gl.glVertex3f(10, 1, 0);
		gl.glVertex3f(11, 1, 0);
		gl.glVertex3f(10, 0, 1);
		gl.glVertex3f(11, 0, 1);
		gl.glVertex3f(10, 1, 1);
		gl.glVertex3f(11, 1, 1);
		
		gl.glVertex3f(10, 0, 0);
		gl.glVertex3f(10, 1, 0);
		gl.glVertex3f(11, 0, 0);
		gl.glVertex3f(11, 1, 0);
		gl.glVertex3f(10, 0, 1);
		gl.glVertex3f(10, 1, 1);
		gl.glVertex3f(11, 0, 1);
		gl.glVertex3f(11, 1, 1);	
		
		gl.glVertex3f(10, 0, 0);
		gl.glVertex3f(10, 0, 1);
		gl.glVertex3f(11, 0, 0);
		gl.glVertex3f(11, 0, 1);
		gl.glVertex3f(10, 1, 0);
		gl.glVertex3f(10, 1, 1);
		gl.glVertex3f(11, 1, 0);
		gl.glVertex3f(11, 1, 1);

		gl.glColor3f(0, 1, 0);
		gl.glVertex3f(0, 10, 0);
		gl.glVertex3f(1, 10, 0);
		gl.glVertex3f(0, 11, 0);
		gl.glVertex3f(1, 11, 0);
		gl.glVertex3f(0, 10, 1);
		gl.glVertex3f(1, 10, 1);
		gl.glVertex3f(0, 11, 1);
		gl.glVertex3f(1, 11, 1);
		
		gl.glVertex3f(0, 10, 0);
		gl.glVertex3f(0, 11, 0);
		gl.glVertex3f(1, 10, 0);
		gl.glVertex3f(1, 11, 0);
		gl.glVertex3f(0, 10, 1);
		gl.glVertex3f(0, 11, 1);
		gl.glVertex3f(1, 10, 1);
		gl.glVertex3f(1, 11, 1);	
		
		gl.glVertex3f(0, 10, 0);
		gl.glVertex3f(0, 10, 1);
		gl.glVertex3f(1, 10, 0);
		gl.glVertex3f(1, 10, 1);
		gl.glVertex3f(0, 11, 0);
		gl.glVertex3f(0, 11, 1);
		gl.glVertex3f(1, 11, 0);
		gl.glVertex3f(1, 11, 1);

		gl.glColor3f(0, 0, 1);
		gl.glVertex3f(0, 0, 10);
		gl.glVertex3f(1, 0, 10);
		gl.glVertex3f(0, 1, 10);
		gl.glVertex3f(1, 1, 10);
		gl.glVertex3f(0, 0, 11);
		gl.glVertex3f(1, 0, 11);
		gl.glVertex3f(0, 1, 11);
		gl.glVertex3f(1, 1, 11);
		
		gl.glVertex3f(0, 0, 10);
		gl.glVertex3f(0, 1, 10);
		gl.glVertex3f(1, 0, 10);
		gl.glVertex3f(1, 1, 10);
		gl.glVertex3f(0, 0, 11);
		gl.glVertex3f(0, 1, 11);
		gl.glVertex3f(1, 0, 11);
		gl.glVertex3f(1, 1, 11);
		
		gl.glVertex3f(0, 0, 10);
		gl.glVertex3f(0, 0, 11);
		gl.glVertex3f(1, 0, 10);
		gl.glVertex3f(1, 0, 11);
		gl.glVertex3f(0, 1, 10);
		gl.glVertex3f(0, 1, 11);
		gl.glVertex3f(1, 1, 10);
		gl.glVertex3f(1, 1, 11);

		gl.glEnd();
	}
	
	private void drawCutPlane(GL2 gl) {
		gl.glFrontFace(GL.GL_CW);		
		gl.glEnable(GL.GL_TEXTURE_2D);
		
		ByteBuffer buf = getCutPlaneBytes();
		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, CUT_PLANE_SIZE, CUT_PLANE_SIZE, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, buf);
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glColor3f(1,1,1);  // Color should be white for textures to work
		
		Vector3D c = controller.settings.cutPlaneCenter;
		Vector3D u = controller.settings.cutPlaneU;
		Vector3D v = controller.settings.cutPlaneV;
		float s = (float)controller.settings.cutPlaneSize;
		// +X is right
		// +Y is up
		// -Z is forward
		gl.glTexCoord2f(0, 0);
		gl.glVertex3f(c.x - u.x*s - v.x*s, c.y - u.y*s - v.y*s, c.z - u.z*s - v.z*s);
		
		gl.glTexCoord2f(1, 1);
		gl.glVertex3f(c.x + u.x*s + v.x*s, c.y + u.y*s + v.y*s, c.z + u.z*s + v.z*s);
		
		gl.glTexCoord2f(1, 0);
		gl.glVertex3f(c.x + u.x*s - v.x*s, c.y + u.y*s - v.y*s, c.z + u.z*s - v.z*s);
		
		gl.glTexCoord2f(0, 0);
		gl.glVertex3f(c.x - u.x*s - v.x*s, c.y - u.y*s - v.y*s, c.z - u.z*s - v.z*s);
		
		gl.glTexCoord2f(0, 1);
		gl.glVertex3f(c.x - u.x*s + v.x*s, c.y - u.y*s + v.y*s, c.z - u.z*s + v.z*s);
		
		gl.glTexCoord2f(1, 1);
		gl.glVertex3f(c.x + u.x*s + v.x*s, c.y + u.y*s + v.y*s, c.z + u.z*s + v.z*s);
		
		gl.glEnd();
		
		gl.glDisable(GL.GL_TEXTURE_2D);
	}
	
	private ByteBuffer getCutPlaneBytes() {
		if (cutPlaneBufferValid)
			return cutPlaneBuffer;
		
		Vector3D point = new Vector3D();
		Vector3D u = controller.settings.cutPlaneU;
		Vector3D v = controller.settings.cutPlaneV;
		Vector3D c = controller.settings.cutPlaneCenter;
		float s = (float)(controller.settings.cutPlaneSize*(2.0/CUT_PLANE_SIZE));
		float offset = s*(CUT_PLANE_SIZE/2) + (float)(controller.settings.cutPlaneSize*(1.0/CUT_PLANE_SIZE));
		
		for (int ui=0; ui<CUT_PLANE_SIZE; ui++)
			for (int vi=0; vi<CUT_PLANE_SIZE; vi++)
			{
				float du = ui*s - offset;
				float dv = vi*s - offset;
				float x = du*u.x + dv*v.x + c.x;
				float y = du*u.y + dv*v.y + c.y;
				float z = du*u.z + dv*v.z + c.z;
				
				float val = 100.0f - x*x - y*y - z*z;
				byte b = 0;
				if (val > 0)
					b = (byte)(val*2.55);
					
				cutPlaneArray[0 + 4*ui + 4*CUT_PLANE_SIZE*vi] = b;  // Red
				cutPlaneArray[1 + 4*ui + 4*CUT_PLANE_SIZE*vi] = (byte)30;  // Green
				cutPlaneArray[2 + 4*ui + 4*CUT_PLANE_SIZE*vi] = b;  // Blue
				cutPlaneArray[3 + 4*ui + 4*CUT_PLANE_SIZE*vi] = (byte)0;
			}
		cutPlaneBufferValid = true;
		return cutPlaneBuffer;
	}

}
