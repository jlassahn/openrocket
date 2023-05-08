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

	public AirflowView(AirflowController controllerIn) {
		log.info("AirflowView constructor");
		controller = controllerIn;
		
		controller.addRefetchListener(this);
	}
	
	@Override
	public void onControllerChange() {
		log.info("controller refetch");
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
		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, 16, 16, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, buf);
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

		byte[] picture = new byte[16*16*4];
		for (int x=0; x<16; x++)
			for (int y=0; y<16; y++)
			{
				picture[0 + 4*x + 4*16*y] = (byte)((y&1)*255);  // Red
				picture[1 + 4*x + 4*16*y] = (byte)0;  // Green
				picture[2 + 4*x + 4*16*y] = (byte)((x&1)*255);  // Blue
				picture[3 + 4*x + 4*16*y] = (byte)0;
			}
		ByteBuffer buf = ByteBuffer.wrap(picture);
		return buf;
	}

}
