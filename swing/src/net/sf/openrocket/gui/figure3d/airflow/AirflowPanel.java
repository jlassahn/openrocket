package net.sf.openrocket.gui.figure3d.airflow;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;

public class AirflowPanel extends JPanel implements GLEventListener, MouseInputListener, MouseWheelListener {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AirflowPanel.class);

	private GLCanvas canvas;
	private float ratio = 1.0f;
	private int[] textureIds = new int[1];
	
	public AirflowPanel(AirflowController controller) {
		log.info("AirflowPanel constructor");
		this.setLayout(new BorderLayout());
		
		log.debug("Setting up GL capabilities...");
		final GLProfile glprofile = GLProfile.get(GLProfile.GL2);
		final GLCapabilities glcaps = new GLCapabilities(glprofile);

		canvas = new GLCanvas(glcaps);
		canvas.addGLEventListener(this);
		this.add(canvas, BorderLayout.CENTER);

		canvas.addMouseWheelListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addMouseListener(this);

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		log.info("display handler");
		
		
		GL2 gl = drawable.getGL().getGL2();
		GLU glu = new GLU();
		gl.glClearColor(1,1,0, 1);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glDisable(GLLightingFunc.GL_LIGHTING);

		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		//gl.glLoadMatrixf();
		glu.gluPerspective(
				10.0, //vertical FoV degrees
				ratio,
				0.1, 50); //front and back clips
		//glu.gluPerspective(p.getFov() * (180.0 / Math.PI), ratio, 0.1f, 50f);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();
		// can use gl.glLoadMatrix(matrix); //float[16] matrix

		//gl.glEnable(GL.GL_CULL_FACE);
		//gl.glCullFace(GL.GL_BACK);
		gl.glFrontFace(GL.GL_CCW);
		gl.glDepthMask(false);
		//gl.glFrontFace(GL.GL_CW);

		
		
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glBindTexture(GL.GL_TEXTURE_2D, textureIds[0]);
		
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
		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, 16, 16, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, buf);
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		
		gl.glBegin(GL.GL_TRIANGLES);
		// +X is up
		// +Y is right
		// -Z is forward
		gl.glColor3f(1,1,1);  // Color should be white for textures to work
		gl.glTexCoord2f(0, 0);
		gl.glVertex3f(0, 0, -20);
		
		gl.glColor3f(1,1,1);
		gl.glTexCoord2f(1, 1);
		gl.glVertex3f(1, 1, -20);
		
		gl.glColor3f(1,1,1);
		gl.glTexCoord2f(1, 0);
		gl.glVertex3f(1, 0, -20);
		gl.glEnd();
		
		
		gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glColor3f(1,0,0);
		gl.glVertex3f(0, 0, -15);
		gl.glColor3f(0,1,0);
		gl.glVertex3f(1, 1, -15);
		gl.glColor3f(0,0,1);
		gl.glVertex3f(1, 0, -15);
		gl.glEnd();
		
		
		
				
		int error = gl.glGetError();
		log.info("error = "+error);
		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		log.info("dispose handler");
		GL2 gl = drawable.getGL().getGL2();
		IntBuffer buf = IntBuffer.wrap(textureIds);
		gl.glDeleteTextures(1, buf);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		log.info("init handler");
		GL2 gl = drawable.getGL().getGL2();

		IntBuffer buf = IntBuffer.wrap(textureIds);
		gl.glGenTextures(1,buf);
		log.info("texture id = "+textureIds[0]);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		log.info("reshape handler");
		ratio = (float)w/(float)h;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse clicked");
		canvas.repaint(); // FIXME do this on change
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse released");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse entered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse exited");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse dragged");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse moved");
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse wheel");
	}
}
